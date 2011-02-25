/*
 * $HeadURL: https://k-team.googlecode.com/svn/trunk/server/service/src/main/java/jp/co/active/kteam/interceptor/front/core/ResponseInterceptor.java $
 * $Author: hao0323 $
 * $Revision: 97 $
 * $Date: 2010-10-19 10:11:57 +0900 (火, 19 10 2010) $
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
package cn.loveapple.service.interceptor.front.core;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.derby.iapi.util.ReuseFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author $author:$
 * @version $Revision: 97 $
 * @date $Date: 2010-10-19 10:11:57 +0900 (火, 19 10 2010) $
 * @id $Id: ResponseInterceptor.java 97 2010-10-19 01:11:57Z hao0323 $
 *
 */
public class ResponseInterceptor extends HandlerInterceptorAdapter {

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(ResponseInterceptor.class);
	
	/**
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Request URI:" + request.getRequestURI());
			StringBuilder paramStr = new StringBuilder(1024);
			
			for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
				String name = (String) e.nextElement();			
				paramStr.append("[");
				paramStr.append(name);
				paramStr.append("=");
				paramStr.append(request.getParameter(name));
				paramStr.append("]");
			}
			log.debug("Form Parameters:" + paramStr);
		}
		return true;
	}
}
