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
package cn.loveapple.client.android.util;

import org.apache.commons.lang.ArrayUtils;

import android.app.Activity;
import android.view.View;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class ComponentUtil {
	/**
	 * 対象{@linkplain Activity}から、表示/非表示の{@linkplain View}を制御する。
	 * 
	 * @param target　対象{@linkplain Activity}
	 * @param visibleList
	 * @param invisibleList
	 * @param gonList
	 */
	public void setVisibilityList(Activity target, int[] visibleList, int[] invisibleList, int[] gonList){
		if(ArrayUtils.isNotEmpty(visibleList)){
			for (int id : visibleList) {
				target.findViewById(id).setVisibility(View.VISIBLE);
			}
		}
		if(ArrayUtils.isNotEmpty(invisibleList)){
			for (int id : invisibleList) {
				target.findViewById(id).setVisibility(View.INVISIBLE);
			}
		}
		if(ArrayUtils.isNotEmpty(gonList)){
			for (int id : gonList) {
				target.findViewById(id).setVisibility(View.GONE);
			}
		}
	}
	
	/**
	 * 対象{@linkplain Activity}から、非表示({@linkplain View#GONE})の{@linkplain View}を制御する。
	 * 
	 * @param target　対象{@linkplain Activity}
	 * @param gonList
	 */
	public void setVisibilityList(Activity target, int... gonList){
		setVisibilityList(target, null, null, gonList);
	}
}
