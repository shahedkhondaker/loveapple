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
	private TemperatureGraphView graphView;
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
        
		TemperatureEntity t1 = new TemperatureEntity();
		t1.setTemperature(36.10);
		TemperatureEntity t2 = new TemperatureEntity();
		t2.setTemperature(37.10);
		TemperatureEntity t3 = new TemperatureEntity();
		t3.setTemperature(36.33);
		TemperatureEntity t4 = new TemperatureEntity();
		TemperatureEntity t5 = new TemperatureEntity();
		TemperatureEntity t6 = new TemperatureEntity();
		TemperatureEntity t7 = new TemperatureEntity();
		TemperatureEntity t8 = new TemperatureEntity();
		TemperatureEntity t9 = new TemperatureEntity();
		TemperatureEntity t10 = new TemperatureEntity();
		TemperatureEntity t11 = new TemperatureEntity();
		TemperatureEntity t12 = new TemperatureEntity();
		TemperatureEntity t13 = new TemperatureEntity();
		TemperatureEntity t14 = new TemperatureEntity();
		TemperatureEntity t15 = new TemperatureEntity();
		TemperatureEntity t16 = new TemperatureEntity();
		TemperatureEntity t17 = new TemperatureEntity();
		TemperatureEntity t18 = new TemperatureEntity();
		TemperatureEntity t19 = new TemperatureEntity();
		TemperatureEntity t20 = new TemperatureEntity();
		TemperatureEntity t21 = new TemperatureEntity();
		TemperatureEntity t22 = new TemperatureEntity();
		TemperatureEntity t23 = new TemperatureEntity();
		TemperatureEntity t24 = new TemperatureEntity();
		TemperatureEntity t25 = new TemperatureEntity();
		TemperatureEntity t26 = new TemperatureEntity();
		TemperatureEntity t27 = new TemperatureEntity();
		TemperatureEntity t28 = new TemperatureEntity();
		TemperatureEntity t29 = new TemperatureEntity();
		TemperatureEntity t30 = new TemperatureEntity();
		TemperatureEntity t31 = new TemperatureEntity();
		TemperatureEntity t32 = new TemperatureEntity();
		TemperatureEntity t33 = new TemperatureEntity();
		TemperatureEntity t34 = new TemperatureEntity();
		TemperatureEntity t35 = new TemperatureEntity();
		TemperatureEntity t36 = new TemperatureEntity();
		TemperatureEntity t37 = new TemperatureEntity();
		t4.setTemperature(36.34);
		t5.setTemperature(36.34);
		t6.setTemperature(36.35);
		t7.setTemperature(36.35);
		t8.setTemperature(36.36);
		t9.setTemperature(36.36);
		t10.setTemperature(36.37);
		t11.setTemperature(36.37);
		t12.setTemperature(36.38);
		t13.setTemperature(36.38);
		t14.setTemperature(36.39);
		t15.setTemperature(36.39);
		t16.setTemperature(36.40);
		t17.setTemperature(36.40);
		t18.setTemperature(36.41);
		t19.setTemperature(36.41);
		t20.setTemperature(36.42);
		t21.setTemperature(36.42);
		t22.setTemperature(36.43);
		t23.setTemperature(36.43);
		t24.setTemperature(36.44);
		t25.setTemperature(36.44);
		t26.setTemperature(36.45);
		t27.setTemperature(36.45);
		t28.setTemperature(36.46);
		t29.setTemperature(36.46);
		t30.setTemperature(36.47);
		t31.setTemperature(36.47);
		t32.setTemperature(36.48);
		t33.setTemperature(36.48);
		t34.setTemperature(36.49);
		t35.setTemperature(36.49);
		t36.setTemperature(36.50);
		t37.setTemperature(36.50);

		graphView.setTemperatures(new TemperatureEntity[]{t1, t2, t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31,t32,t33,t34,t35,t36,t37});
		
		
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
			Toast.makeText(this, "down", Toast.LENGTH_LONG).show();
			downX = event.getX();
			downY = event.getY();
		}
		if(event.getAction() == MotionEvent.ACTION_UP && 100 <= Math.abs(downY - event.getY())){
			Toast.makeText(this, "!!", Toast.LENGTH_LONG).show();
			graphView.setTemperatures(new TemperatureEntity[]{});
			setContentView(graphView);
			return super.onTouchEvent(event);
		}
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
