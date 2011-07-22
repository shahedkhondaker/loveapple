package cn.loveapple.client.android.bbt;

import org.apache.commons.lang.ArrayUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.Display;
import android.view.View;
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
	private TemperatureEntity[] temperatures;
	private String title;
	
	
	public TemperatureGraphView(Context context, TemperatureEntity[] temperatures, String title) {
		super(context);
		
		if (title == null){
			title = "";
		} else {
			this.title = title;
		}
		if(temperatures == null){
			this.temperatures = new TemperatureEntity[]{};
		}else{
			this.temperatures = temperatures;
		}
		
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
		float graphheight = height - (2 * border);
		float graphwidth = width - (2 * border);

		paint.setTextAlign(Align.LEFT);
		/*int vers = verlabels.length - 1;
		for (int i = 0; i < verlabels.length; i++) {
			paint.setColor(Color.DKGRAY);
			float y = ((graphheight / vers) * i) + border;
			canvas.drawLine(horstart, y, width, y, paint);
			paint.setColor(Color.WHITE);
			canvas.drawText(String.valueOf(verlabels[i]), 0, y, paint);
		}
		int hors = horlabels.length - 1;
		for (int i = 0; i < horlabels.length; i++) {
			paint.setColor(Color.DKGRAY);
			float x = ((graphwidth / hors) * i) + horstart;
			canvas.drawLine(x, height - border, x, border, paint);
			paint.setTextAlign(Align.CENTER);
			if (i==horlabels.length-1)
				paint.setTextAlign(Align.RIGHT);
			if (i==0)
				paint.setTextAlign(Align.LEFT);
			paint.setColor(Color.WHITE);
			canvas.drawText(String.valueOf(horlabels[i]), x, height - 4, paint);
		}

		paint.setTextAlign(Align.CENTER);
		canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);

		if (max != min) {
			paint.setColor(Color.LTGRAY);
			
			float datalength = temperatures.length;
			float colwidth = (width - (2 * border)) / datalength;
			float halfcol = colwidth / 2;
			float lasth = 0;
			for (int i = 0; i < temperatures.length; i++) {
				float val = temperatures[i] - min;
				float rat = val / diff;
				float h = graphheight * rat;
				if (i > 0)
					canvas.drawLine(((i - 1) * colwidth) + (horstart + 1) + halfcol, (border - lasth) + graphheight, (i * colwidth) + (horstart + 1) + halfcol, (border - h) + graphheight, paint);
				lasth = h;
			}
			
		}*/
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

}
