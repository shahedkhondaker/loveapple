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
package cn.loveapple.client.android.bbt.listener;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;
import cn.loveapple.client.android.bbt.TemperatureGraphView;
import cn.loveapple.client.android.bbt.R.string;
import cn.loveapple.client.android.bbt.TemperatureGraphView.TemperaturePointsBean;
import cn.loveapple.client.android.database.entity.TemperatureEntity;
import cn.loveapple.client.android.util.StringUtils;

/**
 * オプションメニューを表示するアクティビティ
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 */
public class ChartViewOnTouchListener implements OnTouchListener {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		// on touch 押された場合呼び出される
		TemperatureGraphView graph = (TemperatureGraphView) view;
		TemperaturePointsBean points = graph.getPoints();
		TemperatureEntity entity = points.getTemperature(event.getX(), event.getY());
		if(entity != null){
			Toast.makeText(
					view.getContext(),
					view.getContext().getText(string.temperature) + ":" + StringUtils.temperatureViewStr(entity.getTemperature()),
					Toast.LENGTH_LONG).show();
		}
		return false;
	}
}
