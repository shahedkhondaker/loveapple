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

import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.loveapple.client.android.bbt.R.string;
import cn.loveapple.client.android.bbt.listener.ChartViewOnTouchListener;
import cn.loveapple.client.android.database.entity.TemperatureEntity;

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
		
		graphView.setTemperatures(temperatureMap);
		
		
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
//			Toast.makeText(this, "down", Toast.LENGTH_LONG).show();
			downX = event.getX();
			downY = event.getY();
		}
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			float diff = Math.abs(downY - event.getY());
			if( diff >= graphView.getSellHeight()){
				int count = (int) (diff / graphView.getSellHeight());
				for(int i = 0; i < count; i++){
					temperatureEntity = (TemperatureEntity[]) ArrayUtils.remove(temperatureEntity, i);
				}
			}

			//TODO graphView.setTemperatures(temperatureEntity);
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
}
