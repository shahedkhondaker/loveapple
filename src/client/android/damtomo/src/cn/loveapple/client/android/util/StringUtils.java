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

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
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
}
