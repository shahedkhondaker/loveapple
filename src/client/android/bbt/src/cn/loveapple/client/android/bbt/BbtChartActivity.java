/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.sourceforge.jp
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

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.CollectionUtils;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.loveapple.client.android.bbt.R.string;
import cn.loveapple.client.android.bbt.listener.ChartViewOnTouchListener;
import cn.loveapple.client.android.database.entity.TemperatureEntity;
import cn.loveapple.client.android.util.DateUtil;
import cn.loveapple.client.android.util.StringUtils;

/**
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class BbtChartActivity extends BaseActivity implements OnClickListener {
	private TemperatureGraphView graphView;
	private TemperatureEntity[] temperatureEntity;
	private Map<String, TemperatureEntity> temperatureMap;
	private static final int DEFAULT_VIEW_COUNT = 15;
	/**
	 * 
	 * {@inheritDoc}
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        StringBuilder title = new StringBuilder();
        title.append(getText(string.temperature));
        title.append(getText(string.graph));
        graphView = new TemperatureGraphView(this, title.toString());
        
		temperatureMap = dao.findTemperatureMap();
		
		graphView.setTemperatures(createViewTempList(null, DEFAULT_VIEW_COUNT));
		
		
		graphView.setOnTouchListener(new ChartViewOnTouchListener());
		
		setContentView(graphView);
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
	public void onClick(View arg0) {
		

	}
    

    /**
     * 
     * {@inheritDoc}
     */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			downX = event.getX();
			downY = event.getY();
		}
		// グラフをドラックした場合の表示制御
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			float diff = Math.abs(downY - event.getY());
			if( diff >= graphView.getSellHeight()){
				int count = (int) (diff / graphView.getSellHeight());
				for(int i = 0; i < count; i++){
					// 下にドラックして、より古い情報を表示させる制御
					if(!graphView.isFirst() && downY < event.getY()){
						temperatureEntity = (TemperatureEntity[]) ArrayUtils.remove(temperatureEntity, i);
					}
						
					// 上にドラックして、より新しい情報を表示させる制御
					if(!graphView.isLast() && event.getY() < downY){
						
					}
					
				}
				
				
			}

			graphView.setTemperatures(temperatureEntity);
		}

		setContentView(graphView);
		return super.onTouchEvent(event);
	}
    
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void init(){
    	super.init();
    }
    
    /**
     * 画面に表示する温度リストを生成。<br>
     * ・{@linkplain #temperatureMap}の件数が表示件数より小さい場合、全件を戻す。<br>
     * ・表示開始日付を指定する場合、指定日付からの表示件数分のデータを戻す。<br>
     * ・表示開始日付を指定しない場合、最後の表示件数分のデータを戻す。
     * 
     * @param startDate 表示開始日付
     * @param count 画面に表示する温度データの件数。最大31とすること。
     * @return
     */
    private TemperatureEntity[] createViewTempList(String startDate, int count){
    	if(31 < count){
    		count = 31;
    	}
    	if(CollectionUtils.isEmpty(temperatureMap)){
    		return new TemperatureEntity[]{};
    	}
    	if(temperatureMap.size() <= count){
    		return temperatureMap.values().toArray(new TemperatureEntity[temperatureMap.size()]);
    	}
		TemperatureEntity[] result = new TemperatureEntity[count];
    	// 表示開始日付を指定しない場合
    	if(StringUtils.isEmpty(startDate)){
    		TemperatureEntity[] temperatures = temperatureMap.values().toArray(new TemperatureEntity[temperatureMap.size()]);
    		int deleteSize = temperatureMap.size() - count;
    		for (int i = deleteSize,j=0; i <temperatureMap.size(); i++,j++){
    			result[j] = temperatures[i];
    		}
    	}
    	// 表示開始日付を指定する場合
    	else{
    		Calendar date = Calendar.getInstance();
    		date.setTime(DateUtil.paseDate(startDate, DateUtil.DATE_PTTERN_YYYYMMDD));
    		for(int i = 0; i < count; i++,date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH) + 1)){
    			result[i] = temperatureMap.get(DateUtil.toDateString(date.getTime()));
    		}
    	}
		return result;
    }
}
