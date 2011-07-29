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
 * 基礎体温のチャートを表示するビュー
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
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
		float horstart = border * 4;
		float height = getHeight();
		float width = getWidth() - border;
		double max = getMax();
		double min = getMin();
		double diff = max - min;
		float graphTop = 5 * border;
		float graphheight = height - graphTop- border;
		float graphwidth = width - (horstart);
		
		setBackgroundColor(Color.WHITE);
		
		// 日付軸を描画
		paint.setTextAlign(Align.CENTER);
		for (int i = 0; i < 31; i++) {
			paint.setColor(color.line);
			float y = ((graphheight / 31f) * i) + graphTop;
			canvas.drawLine(horstart, y, width, y, paint);
			paint.setColor(Color.BLACK);
			canvas.drawText(String.valueOf(i), 0, y+1f, paint);
		}
		
		// 温度軸を描画
		int hors = 40;
		for (int i = 0; i < hors; i++) {
			paint.setColor(Color.DKGRAY);
			float x = (float) (((graphwidth-graphTop / 100) * i) + horstart);
			canvas.drawLine(x, graphTop, x, height, paint);
			paint.setTextAlign(Align.CENTER);
			if (i==hors)
				paint.setTextAlign(Align.RIGHT);
			if (i==0)
				paint.setTextAlign(Align.LEFT);
			paint.setColor(Color.BLACK);
			if(i%5 == 0){
				//canvas.drawText(String.valueOf(i*0.1 + 35.0), x, border, paint);
				canvas.drawText(String.valueOf(x), x-20, graphTop, paint);
			}
		}

		// タイトルを描画
		paint.setTextAlign(Align.CENTER);
		canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);

		// 線チャートを描画
		if (max != min) {
			paint.setColor(Color.BLUE);
			
			float datalength = temperatures.length;
			float colwidth = (width - (2 * border)) / datalength;
			float halfcol = colwidth / 2;
			float lasth = 0;
			for (int i = 0; i < temperatures.length; i++) {
				
				if(i > 0){
					canvas.drawLine(
							(float)(((graphheight / 31f) * ((temperatures[(i-1)].getTemperature() - 35f)/0.1f)) + border),
							(((graphwidth / hors) * (i-1)) + horstart), 
							(float)(((graphheight / 31f) * ((temperatures[(i)].getTemperature() - 35f)/0.1f)) + border),
							(((graphwidth / hors) * i) + horstart),
							paint);		
				}
				
				/*float val = (float) (temperatures[i].getTemperature() - min);
				float rat = (float) (val / diff);
				float h = graphheight * rat;
				if (i > 0)
					canvas.drawLine(
							((i - 1) * colwidth) + (horstart + 1) + halfcol,
							(border - lasth) + graphheight, 
							(i * colwidth) + (horstart + 1) + halfcol,
							(border - h) + graphheight,
							paint);
				lasth = h;*/
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
