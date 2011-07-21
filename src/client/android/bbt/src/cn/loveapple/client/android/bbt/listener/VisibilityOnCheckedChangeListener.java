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

import static android.view.View.*;
import static cn.loveapple.client.android.util.ComponentUtil.*;
import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 選択結果により、表示/非表示の切り替えを行う。
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 * 
 */
public class VisibilityOnCheckedChangeListener implements
		OnCheckedChangeListener {
	private Activity target;
	private int visibility;
	private View[] visibleList;
	private View[] hiddenList;
	
	/**
	 * @see View#GONE
	 * @param visibleList
	 * @param hiddenList
	 */
	public VisibilityOnCheckedChangeListener(Activity target, View[] visibleList, View[] hiddenList){
		this(target, visibleList, hiddenList, GONE);
	}
	/**
	 * 
	 * @param visibleList
	 * @param hiddenList
	 * @param visibility
	 */
	public VisibilityOnCheckedChangeListener(Activity target, View[] visibleList, View[] hiddenList, int visibility){
		if(visibility != GONE && visibility != INVISIBLE){
			visibility = GONE;
		}
		this.visibility = visibility;
		this.visibleList = visibleList;
		this.hiddenList = hiddenList;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked){
			if(visibility == VISIBLE){
				setVisibilityList(target, visibleList, hiddenList, null);
			}else{
				setVisibilityList(target, visibleList, null, hiddenList);
			}
		}else{
			if(visibility == VISIBLE){
				setVisibilityList(target, hiddenList, visibleList, null);
			}else{
				setVisibilityList(target, hiddenList, null, visibleList);
			}
		}
	}

}
