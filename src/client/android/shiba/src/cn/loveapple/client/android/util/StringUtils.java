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

import static cn.loveapple.client.android.Constant.SCHEMA_HTTPS_STR;
import static cn.loveapple.client.android.Constant.SCHEMA_HTTP_STR;

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
	
	public static String getUrlWhitchSchema(String url){
		if(isEmpty(url)){
			return url;
		}
		if(url.startsWith(SCHEMA_HTTP_STR) || url.startsWith(SCHEMA_HTTPS_STR)){
			return url;
		}
		return SCHEMA_HTTP_STR + url;
	}
	
	public static String getHost(String url){
		if(isEmpty(url)){
			return url;
		}
		String host = null;
		if(url.startsWith(SCHEMA_HTTP_STR)){
			host = url.substring(SCHEMA_HTTP_STR.length());
		}else if(url.startsWith(SCHEMA_HTTPS_STR)){
			host = url.substring(SCHEMA_HTTPS_STR.length());
		}else{
			host = url;
		}
		int endPoint = host.indexOf('/');
		if(endPoint < 0){
			return host;
		}
		return host.substring(0, endPoint);
	}
	
	public static String[] explodeHostAndPort(String url){
		if(isEmpty(url)){
			return null;
		}
		return url.split(":", 2);
	}
}
