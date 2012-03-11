/*
 * $HeadURL: https://loveapple.googlecode.com/svn/trunk/src/client/android/gaia/src/cn/loveapple/android/utils/StringUtils.java $
 * $Author: hao0323@gmail.com $
 * $Revision: 373 $
 * $Date: 2012-03-11 11:25:00 +0900 (日, 11 3 2012) $
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
package cn.loveapple.client.android.utils;

import static cn.loveapple.client.android.Constant.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 
 * @author $Author: hao0323@gmail.com $
 * @version $Revision: 373 $
 * @date $Date: 2012-03-11 11:25:00 +0900 (日, 11 3 2012) $
 * @id $Id: StringUtils.java 373 2012-03-11 02:25:00Z hao0323@gmail.com $
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {
	/**
	 * 体温表示フォーマット
	 */
	private static final DecimalFormat TEMPERATURE_VIEW_FORMAT;
	static {
		TEMPERATURE_VIEW_FORMAT = (DecimalFormat) NumberFormat.getInstance();
		TEMPERATURE_VIEW_FORMAT.applyPattern("#.00");
	}
	/**
	 * 温度表示用の文字列を戻す
	 * 
	 * @param src
	 * @return
	 */
	public static String temperatureViewStr(double src){
		return TEMPERATURE_VIEW_FORMAT.format(src);
	}
	/**
	 * 
	 * @param src
	 * @return
	 */
	public static String convertClassName2ResourceName(String src){
		if(isBlank(src)){
			return src;
		}
		StringBuilder result = new StringBuilder(src.length() + 6);
		char[] chars = src.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if('A' <= c && c <= 'Z'){
				if(0 < i){
					result.append('_');
				}
				result.append((char)(c + 32));
			}else{
				result.append(c);
			}
		}
		return result.toString();
	}
	
	public static String getUrlWhitchHttpSchema(String url){
		if(isEmpty(url)){
			return url;
		}
		if(url.startsWith(SCHEMA_HTTP) || url.startsWith(SCHEMA_HTTPS)){
			return url;
		}
		return SCHEMA_HTTP + url;
	}
	
	public static String getHost(String url){
		if(isEmpty(url)){
			return url;
		}
		String host = null;
		if(url.startsWith(SCHEMA_HTTP)){
			host = url.substring(SCHEMA_HTTP.length());
		}else if(url.startsWith(SCHEMA_HTTPS)){
			host = url.substring(SCHEMA_HTTPS.length());
		}else{
			host = url;
		}
		int endPoint = host.indexOf('/');
		if(endPoint < 0){
			return host;
		}
		return host.substring(0, endPoint);
	}
}
