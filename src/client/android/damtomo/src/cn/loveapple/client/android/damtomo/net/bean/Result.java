/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
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
package cn.loveapple.client.android.damtomo.net.bean;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Root(name = "result")
public class Result {
	
	@Element
	private String status;

	@Element
	private String statusCode;
	
	@Element(required=false)
	private String message;

	public Result() {
	}

	public Result(String status, String statusCode) {
		super();
		this.status = status;
		this.statusCode = statusCode;
	}

	public String getFormattedName() {
		return this.getStatus() + " (" + this.getStatusCode() + ")";
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public String getStatus() {
	    return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(String status) {
	    this.status = status;
	}

	/**
	 * statusCodeを取得します。
	 * @return statusCode
	 */
	public String getStatusCode() {
	    return statusCode;
	}

	/**
	 * statusCodeを設定します。
	 * @param statusCode statusCode
	 */
	public void setStatusCode(String statusCode) {
	    this.statusCode = statusCode;
	}

	/**
	 * messageを取得します。
	 * @return message
	 */
	public String getMessage() {
	    return message;
	}

	/**
	 * messageを設定します。
	 * @param message message
	 */
	public void setMessage(String message) {
	    this.message = message;
	}
	
}
