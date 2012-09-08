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

import static cn.loveapple.client.android.Constant.LOG_TAG;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

import android.util.Log;

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
	public static Date parseDate(String source, String pattern){
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
	 * 文字列を時間に変換する。
	 * 
	 * @see SimpleDateFormat 変換処理を行う
	 * @param source 元の文字列
	 * @param pattern 時間パターン
	 * @param timeZone タイムゾーン
	 * @return 変換できない場合、<code>null</code>を戻す。成功した場合、変換結果を戻す
	 */
	public static Date parseDate(String source, String pattern, String timeZone){
		if(source == null || pattern == null){
			return null;
		}
		try{
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			if(StringUtils.isNotEmpty(timeZone)){
				format.setTimeZone(TimeZone.getTimeZone(timeZone.trim()));
			}
			return format.parse(source);
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 日時文字列が正しいかどうかをチェックする。
	 * 
	 * @see #parseDate(String, String)
	 * @param source 元の文字列
	 * @param pattern 時間パターン
	 * @return 判定結果を戻す。
	 */
	public static boolean isDateStr(String source, String pattern){
		return parseDate(source, pattern) != null;
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

	/**
	 * 試験期間中かどうかを判定する
	 * 
	 * @return 判定結果
	 */
	public static boolean isTestTimeOver(){
		boolean istTestVersion = false;//
		
		if(!istTestVersion){
			return false;
		}
		Calendar now = Calendar.getInstance();
		
		final int TEST_FIX_YEAR = 2012;
		final int TEST_FIX_MONTH = 6;
		final int TEST_FIX_DATE = 20;
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int date = now.get(Calendar.DAY_OF_MONTH);
		
		Log.d(LOG_TAG, TEST_FIX_YEAR+"/" +TEST_FIX_MONTH+"/" + TEST_FIX_DATE + " --> " + year+"/"+month+"/"+date);
		
		if(TEST_FIX_YEAR < year){
			return true;
		} else if(TEST_FIX_YEAR == year){
			if(TEST_FIX_MONTH < month){
				return true;
			}else if(TEST_FIX_MONTH == month){
				return TEST_FIX_DATE < date;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
