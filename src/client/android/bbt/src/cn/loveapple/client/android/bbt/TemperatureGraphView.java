/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.cn
 *
 * All copyright notices regarding loveapple and loveapple CoreLib
 * MUST remain intact in the scripts, documents and source code.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at loveapple
 *
 * @author: loveapple
 */
package cn.loveapple.client.android.bbt;

import java.util.Calendar;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.View;
import cn.loveapple.client.android.bbt.R.color;
import cn.loveapple.client.android.database.entity.TemperatureEntity;
import cn.loveapple.client.android.util.DateUtil;

/**
 * 基礎体温のチャートを表示するビュー
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 */
public class TemperatureGraphView extends View {

//	public final static int DISPLAY_DIRECTION_H = 1;
//	public final static int DISPLAY_DIRECTION_W = 2;
	/**
	 * 最後の{@linkplain TemperatureEntity 温度}まで表示されていること
	 */
	private boolean isLast = false;
	/**
	 * 最初の{@linkplain TemperatureEntity 温度}まで表示されていること
	 */
	private boolean isFirst = false;
	/**
	 * デフォルト表示最低温度
	 */
	public final static float VIEW_MIN_LIMIT_TEMPERATURE = 36.00f;
	/**
	 * デフォルト表示最高温度
	 */
	public final static float VIEW_MAX_LIMIT_TEMPERATURE = 40.00f;

	/**
	 * 描画ペイント
	 */
	private Paint paint;
	/**
	 * 体温の配列
	 */
	private TemperatureEntity[] temperatures;
	/**
	 * タイトル
	 */
	private String title;

	/**
	 * セル高さ
	 */
	float sellHeight;
	/**
	 * セル幅
	 */
	float sellWidth;
	
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

	/**
	 * メインの描画処理を行う。
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		/**
		 * 基準のボーダ
		 */
		float border = 20;
		/**
		 * グラフ左の開始ポイント
		 */
		float horstart = border * 4;
		/**
		 * グラフTOPの開始ポイント
		 */
		float graphTop = 5 * border;
		/**
		 * ディスプレの高さ
		 */
		float height = getHeight();
		/**
		 * ディスプレイの幅
		 */
		float width = getWidth();
		/**
		 * 最高温度
		 */
		double max = maxViewTemperature();
		/**
		 * 最低温度
		 */
		double min = minViewTemperature();
		/**
		 * グラフの高さ
		 */
		float graphheight = height - graphTop- border;
		/**
		 * グラフの幅
		 */
		float graphwidth = width - horstart - border;
		/**
		 * 温度の表示精度
		 */
		int hors = (int) ((max-min)/0.1)+1;
		/**
		 * セル高さ
		 */
		sellHeight = graphheight / 31f;
		/**
		 * セル幅
		 */
		sellWidth = graphwidth / hors;
		
		
		setBackgroundColor(Color.WHITE);

		Calendar firstDate = getFirstDate();
		
		// 日付軸を描画
		paint.setTextAlign(Align.CENTER);
		for (int i = 0; i < 31; i++) {
			firstDate.set(Calendar.DAY_OF_MONTH, firstDate.get(Calendar.DAY_OF_MONTH) + 1);
			paint.setColor(color.line);
			float y = (sellHeight * i) + graphTop;
			canvas.drawLine(horstart, y, width-border-sellWidth, y, paint);
			paint.setColor(Color.BLACK);
			canvas.drawText(
					String.format(getContext().getText(R.string.graphDateString).toString(), firstDate.getTime()),
					horstart-border, y+2f, paint);
		}
		
		// 温度軸を描画
		for (int i = 0; i < hors; i++) {
			paint.setColor(color.line);
			float x = (float) ((sellWidth * i) + horstart);
			canvas.drawLine(x, graphTop, x, height - border-sellHeight, paint);
			paint.setTextAlign(Align.CENTER);
			if (i==hors)
				paint.setTextAlign(Align.RIGHT);
			if (i==0)
				paint.setTextAlign(Align.LEFT);
			paint.setColor(Color.BLACK);
			if(i%5 == 0){
				canvas.drawText(String.valueOf(i), x, graphTop, paint);
			}
		}

		// タイトルを描画
		paint.setTextAlign(Align.CENTER);
		canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);
		canvas.drawText(String.valueOf(min), horstart + border, 3*border, paint);
		canvas.drawText(String.valueOf(max), graphwidth + horstart, 3*border, paint);

		// 線チャートを描画
		if (max != min) {
			paint.setColor(Color.BLUE);
			
			for (int i = 0; i < temperatures.length; i++) {
				
				if(i > 0){
					float x1 = temperatures[(i-1)].getTemperature() == null ? 0 : (float)((sellWidth * ((temperatures[(i-1)].getTemperature() - min)/0.1f)) + horstart);
					float y1 = ((sellHeight * (i-1)) + graphTop);
					float x2 = temperatures[(i)].getTemperature() == null ? 0 : (float)((sellWidth * ((temperatures[(i)].getTemperature() - min)/0.1f)) + horstart);
					float y2 = ((sellHeight * i) + graphTop);
					
					paint.setTextAlign(Align.RIGHT);
					if(x1 != 0){
						canvas.drawText(temperatures[(i-1)].getTemperature().toString(), x1, y1, paint);
					}
					if(i == temperatures.length -1 ){
						if(x2 != 0){
							canvas.drawText(temperatures[(i)].getTemperature().toString(), x2, y2, paint);
						}
					}
					if(x1 == 0){
						canvas.drawLine(x2, y2, x2, y2, paint);
					}else if(x2 == 0){
						canvas.drawLine(x1, y1, x1, y1, paint);
					}else{
						canvas.drawLine(x1, y1, x2, y2, paint);
					}
				}
			}
			
		}
	}

	private double getMax() {
		double largest = Double.MIN_VALUE;
		for (TemperatureEntity temperature : temperatures) {
			if(temperature.getTemperature() != null
					&& temperature.getTemperature().doubleValue() > largest){
				largest = temperature.getTemperature().doubleValue();
			}
		}
		return largest;
	}

	private double getMin() {
		double smallest = Double.MAX_VALUE;
		for (TemperatureEntity temperature : temperatures) {
			if (temperature.getTemperature() != null 
					&& temperature.getTemperature().doubleValue() < smallest)
				smallest = temperature.getTemperature().doubleValue();
		}
		return smallest;
	}
	
	private float maxViewTemperature(){
		double max = getMax();
		double min = getMin();
		if(max - min < 4){
			if(max < VIEW_MAX_LIMIT_TEMPERATURE){
				return VIEW_MAX_LIMIT_TEMPERATURE;
			}
		}
		return (float)max;
	}
	private float minViewTemperature(){
		double max = getMax();
		double min = getMin();
		if(max - min < 4){
			if(VIEW_MIN_LIMIT_TEMPERATURE < min){
				return VIEW_MIN_LIMIT_TEMPERATURE;
			}
		}
		return (float)min;
	}



	/**
	 * 最後の{@linkplain TemperatureEntity 温度}まで表示されていることを取得します。
	 * @return 最後の{@linkplain TemperatureEntity 温度}まで表示されていること
	 */
	public boolean isLast() {
	    return isLast;
	}

	/**
	 * 最後の{@linkplain TemperatureEntity 温度}まで表示されていることを設定します。
	 * @param isLast 最後の{@linkplain TemperatureEntity 温度}まで表示されていること
	 */
	public void setLast(boolean isLast) {
	    this.isLast = isLast;
	}

	/**
	 * 最初の{@linkplain TemperatureEntity 温度}まで表示されていることを取得します。
	 * @return 最初の{@linkplain TemperatureEntity 温度}まで表示されていること
	 */
	public boolean isFirst() {
	    return isFirst;
	}

	/**
	 * 最初の{@linkplain TemperatureEntity 温度}まで表示されていることを設定します。
	 * @param isFirst 最初の{@linkplain TemperatureEntity 温度}まで表示されていること
	 */
	public void setFirst(boolean isFirst) {
	    this.isFirst = isFirst;
	}

	/**
	 * paintを取得します。
	 * @return paint
	 */
	public Paint getPaint() {
	    return paint;
	}



	/**
	 * 体温の配列を設定します。
	 * @param temperatures 体温の配列
	 */
	public void setTemperatures(Map<String, TemperatureEntity> temperatures) {
		if(CollectionUtils.isEmpty(temperatures)){
			this.temperatures = new TemperatureEntity[]{};
		}
	    this.temperatures = temperatures.values().toArray(new TemperatureEntity[temperatures.size()]);
	}
	/**
	 * 体温の配列を設定します。
	 * @param temperatures 体温の配列
	 */
	public void setTemperatures(TemperatureEntity[] temperatures) {
	    this.temperatures = temperatures;
	}


	private Calendar getFirstDate(){
		TemperatureEntity first = temperatures[0];
		Calendar firstDate = Calendar.getInstance();
		firstDate.setTime(DateUtil.paseDate(first.getDate(), DateUtil.DATE_PTTERN_YYYYMMDD));
		return firstDate;
	}

	/**
	 * 体温の配列を取得します。
	 * @return 体温の配列
	 */
	public TemperatureEntity[] getTemperatures() {
	    return temperatures;
	}

	/**
	 * セル高さを取得します。
	 * @return セル高さ
	 */
	public float getSellHeight() {
	    return sellHeight;
	}

	/**
	 * セル幅を取得します。
	 * @return セル幅
	 */
	public float getSellWidth() {
	    return sellWidth;
	}
}
