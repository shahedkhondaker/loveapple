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

import java.util.Date;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.plot.PiePlot;
import org.afree.data.general.DefaultPieDataset;
import org.afree.graphics.geom.Font;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import cn.loveapple.client.android.LoveappleHealthDatabaseOpenHelper;
import cn.loveapple.client.android.database.TemperatureDao;
import cn.loveapple.client.android.database.impl.TemperatureDaoImpl;
import cn.loveapple.client.android.util.DateUtil;

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
        
        float[] values = new float[] { 35.79f,36.7f, 42.1f, 36.9f , 36.7f, 39.8f };
		String[] verlabels = new String[] { "43.0", "42.0", "41.0", "40.0", "39.0", "38.0", "37.0", "36.0", "35.0" };
		String[] horlabels = new String[] { "today", "tomorrow", "next week", "next month" };
		GraphView graphView = new GraphView(this, values, "GraphViewDemo",horlabels, verlabels, GraphView.LINE);
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
