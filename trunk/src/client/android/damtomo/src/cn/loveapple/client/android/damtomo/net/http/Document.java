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
 * @author Roy Clarkson
 */
@Root(name = "document")
public class Document {

	/**
	 * レスポンス結果
	 */
	@Element
	private Result result;
	
	/**
	 * レスポンスデータ
	 */
	@Element
	private Data data;
	/**
	 * @param result
	 * @param data
	 */
	public Document(Result result, Data data) {
		super();
		this.result = result;
		this.data = data;
	}
	public Document() {
	}
	/**
	 * レスポンス結果を取得します。
	 * @return レスポンス結果
	 */
	public Result getResult() {
	    return result;
	}
	/**
	 * レスポンス結果を設定します。
	 * @param result レスポンス結果
	 */
	public void setResult(Result result) {
	    this.result = result;
	}
	/**
	 * レスポンスデータを取得します。
	 * @return レスポンスデータ
	 */
	public Data getData() {
	    return data;
	}
	/**
	 * レスポンスデータを設定します。
	 * @param data レスポンスデータ
	 */
	public void setData(Data data) {
	    this.data = data;
	}

	public String getFormattedName(){
		return result.getStatusCode() + " -- " + result.getStatus();
	}
}
