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
package cn.loveapple.android.utils;

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
	public static void setVisibilityList(Activity target, View[] visibleList, View[] invisibleList, View[] goneList){
		if(ArrayUtils.isNotEmpty(visibleList)){
			for (View v : visibleList) {
				if(v == null){
					continue;
				}
				v.setVisibility(View.VISIBLE);
			}
		}
		if(ArrayUtils.isNotEmpty(invisibleList)){
			for (View v : invisibleList) {
				if(v == null){
					continue;
				}
				v.setVisibility(View.INVISIBLE);
			}
		}
		if(ArrayUtils.isNotEmpty(goneList)){
			for (View v : goneList) {
				if(v == null){
					continue;
				}
				v.setVisibility(View.GONE);
			}
		}
	}
	
	/**
	 * 対象{@linkplain Activity}から、非表示({@linkplain View#GONE})の{@linkplain View}を制御する。
	 * 
	 * @see #setVisibilityList(Activity, int[], int[], int[])
	 * @param target　対象{@linkplain Activity}
	 * @param gonList
	 */
	public static void setGoneList(Activity target, View... goneList){
		setVisibilityList(target, null, null, goneList);
	}
	
	/**
	 * 対象{@linkplain Activity}から、表示({@linkplain View#VISIBLE})の{@linkplain View}を制御する。
	 * @see #setVisibilityList(Activity, int[], int[], int[])
	 * @param target
	 * @param visibleList
	 */
	public static void setVisibleList(Activity target, View... visibleList){
		setVisibilityList(target, visibleList, null, null);
	}
	
	/**
	 * 対象{@linkplain Activity}から、非表示({@linkplain View#INVISIBLE})の{@linkplain View}を制御する。
	 * @param target
	 * @param invisibleList
	 */
	public static void setInvisibleList(Activity target, View... invisibleList){
		setVisibilityList(target, null, invisibleList, null);
	}
	
}
