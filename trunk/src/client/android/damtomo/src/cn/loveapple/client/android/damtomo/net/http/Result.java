/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.loveapple.client.android.damtomo.net.http;

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
