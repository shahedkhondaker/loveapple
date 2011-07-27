package cn.loveapple.client.android.bbt;

import static cn.loveapple.client.android.Constant.*;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.View;
import cn.loveapple.client.android.bbt.R.color;
import cn.loveapple.client.android.database.entity.TemperatureEntity;

/**
 * GraphView creates a scaled line or bar graph with x and y axis labels. 
 * @author Arno den Hond
 *
 */
public class TemperatureGraphView extends View {

	public final static int DISPLAY_DIRECTION_H = 1;
	public final static int DISPLAY_DIRECTION_W = 2;

	private Paint paint;
	/**
	 * 体温の配列
	 */
	private TemperatureEntity[] temperatures;
	private String title;
	
	
	public TemperatureGraphView(Context context, String title) {
		super(context);
		
		if (title == null){
			title = "";
		} else {
			this.title = title;
		}
		this.temperatures = new TemperatureEntity[]{};
		
		paint = new Paint();
	}
	
	

	@Override
	protected void onDraw(Canvas canvas) {
		float border = 20;
		float horstart = border * 2;
		float height = getHeight();
		float width = getWidth() - 1;
		double max = getMax();
		double min = getMin();
		double diff = max - min;
		float graphheight = height - (3 * border);
		float graphwidth = width - (2 * border);
		
		setBackgroundColor(Color.WHITE);
		
		// 日付軸を描画
		paint.setTextAlign(Align.LEFT);
		for (int i = 0; i < 31; i++) {
			paint.setColor(color.line);
			float y = ((graphheight / 31f) * i) + border;
			canvas.drawLine(horstart, y, width, y, paint);
			paint.setColor(Color.BLACK);
			canvas.drawText(String.valueOf(i), 0, y+1f, paint);
		}
		
		// 温度軸を描画
		int hors = 28;
		for (int i = 0; i < 28; i++) {
			paint.setColor(Color.DKGRAY);
			float x = (float) (((graphwidth / hors) * i) + horstart);
			canvas.drawLine(x, height - border, x, border, paint);
			paint.setTextAlign(Align.CENTER);
			if (i==hors)
				paint.setTextAlign(Align.RIGHT);
			if (i==0)
				paint.setTextAlign(Align.LEFT);
			paint.setColor(Color.BLACK);
			if(i%5 == 0){
				canvas.drawText(String.valueOf(i), x, border, paint);
			}
		}

		// タイトルを描画
		paint.setTextAlign(Align.CENTER);
		canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);

		// 線チャートを描画
		if (max != min) {
			paint.setColor(Color.LTGRAY);
			
			float datalength = temperatures.length;
			float colwidth = (width - (2 * border)) / datalength;
			float halfcol = colwidth / 2;
			float lasth = 0;
			for (int i = 0; i < temperatures.length; i++) {
				float val = (float) (temperatures[i].getTemperature() - min);
				float rat = (float) (val / diff);
				float h = graphheight * rat;
				if (i > 0)
					canvas.drawLine(((i - 1) * colwidth) + (horstart + 1) + halfcol, (border - lasth) + graphheight, (i * colwidth) + (horstart + 1) + halfcol, (border - h) + graphheight, paint);
				lasth = h;
			}
			
		}
	}

	private double getMax() {
		double largest = Double.MIN_VALUE;
		for (TemperatureEntity temperature : temperatures) {
			if(temperature.getTemperature().doubleValue() > largest){
				largest = temperature.getTemperature().doubleValue();
			}
		}
		return largest;
	}

	private double getMin() {
		double smallest = Double.MAX_VALUE;
		for (TemperatureEntity temperature : temperatures) {
			if (temperature.getTemperature().doubleValue() < smallest)
				smallest = temperature.getTemperature().doubleValue();
		}
		return smallest;
	}



	/**
	 * 体温の配列を設定します。
	 * @param temperatures 体温の配列
	 */
	public void setTemperatures(TemperatureEntity[] temperatures) {
	    this.temperatures = temperatures;
	}

}
