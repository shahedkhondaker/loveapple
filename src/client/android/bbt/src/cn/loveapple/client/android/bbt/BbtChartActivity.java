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

import cn.loveapple.client.android.bbt.R.string;
import cn.loveapple.client.android.bbt.listener.ChartViewOnTouchListener;
import cn.loveapple.client.android.database.entity.TemperatureEntity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class BbtChartActivity extends BaseActivity implements OnClickListener {
	
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
        TemperatureGraphView graphView = new TemperatureGraphView(this, title.toString());
		TemperatureEntity t1 = new TemperatureEntity();
		t1.setTemperature(36.10);
		TemperatureEntity t2 = new TemperatureEntity();
		t2.setTemperature(37.10);
		TemperatureEntity t3 = new TemperatureEntity();
		t3.setTemperature(36.33);
		graphView.setTemperatures(new TemperatureEntity[]{t1, t2, t3});
		
		
		graphView.setOnTouchListener(new View.OnTouchListener() {

			private float downX;
			private float downY;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					Toast.makeText(v.getContext(), "down", Toast.LENGTH_LONG).show();
					downX = event.getX();
					downY = event.getY();
				}
				if(event.getAction() == MotionEvent.ACTION_UP){
					Toast.makeText(
							v.getContext(),
							"up" +
							"  =>downX:" + downX +
							"  =>downY:" + downY +
							"  x/y" + event.getX() + "/" + event.getY(),
							
							Toast.LENGTH_LONG).show();
					
				}
				Toast.makeText(
						v.getContext(),
						event.getAction() +
						"  =>downX:" + downX +
						"  =>downY:" + downY +
						"  x/y" + event.getX() + "/" + event.getY(),
						
						Toast.LENGTH_LONG).show();
				if(100 <= Math.abs(downY - event.getY())){
					Toast.makeText(v.getContext(), "!!", Toast.LENGTH_LONG).show();
					return false;
					
				}
				return false;
			}
		});
		
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
    protected void init(){
    	super.init();
    }

}
