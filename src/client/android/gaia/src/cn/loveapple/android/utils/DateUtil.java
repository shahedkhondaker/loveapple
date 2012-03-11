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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class DateUtil extends DateUtils{
	/**
	 * 日時文字列パターン：YYYYMMDD
	 */
	public static final String DATE_PTTERN_YYYYMMDD = "yyyyMMdd";

	/**
	 * 文字列を時間に変換する。
	 * 
	 * @see SimpleDateFormat 変換処理を行う
	 * @param source 元の文字列
	 * @param pattern 時間パターン
	 * @return 変換できない場合、<code>null</code>を戻す。成功した場合、変換結果を戻す
	 */
	public static Date paseDate(String source, String pattern){
		if(source == null || pattern == null){
			return null;
		}
		try{
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(source);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 日時文字列が正しいかどうかをチェックする。
	 * 
	 * @see #paseDate(String, String)
	 * @param source 元の文字列
	 * @param pattern 時間パターン
	 * @return 判定結果を戻す。
	 */
	public static boolean isDateStr(String source, String pattern){
		return paseDate(source, pattern) != null;
	}
	
	/**
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static String toDateString(Date source, String pattern){
		if(source == null || pattern == null){
			return null;
		}
		try{
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.format(source);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @see #DATE_PTTERN_YYYYMMDD
	 * @see #toDateString(Date, String)
	 * @param source
	 * @return
	 */
	public static String toDateString(Date source){
		if(source == null){
			return null;
		}
		return toDateString(source, DATE_PTTERN_YYYYMMDD);
	}
	
	/**
	 * @see #DATE_PTTERN_YYYYMMDD
	 * @see #toDateString(Date, String)
	 * @return
	 */
	public static String toDateString(){
		return toDateString(new Date(), DATE_PTTERN_YYYYMMDD);
	}
}
