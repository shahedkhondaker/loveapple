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

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

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
