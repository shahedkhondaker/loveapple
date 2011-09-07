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

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * @author Roy Clarkson
 */
@Root(name = "document")
public class StateList {

	@ElementList(inline = true)
	private Document2tn0 document;

	public StateList() {
	}

	public StateList(Document2tn0 document) {
		super();
		this.document = document;
	}

	/**
	 * documentを取得します。
	 * @return document
	 */
	public Document2tn0 getDocument() {
	    return document;
	}

	/**
	 * documentを設定します。
	 * @param document document
	 */
	public void setDocument(Document2tn0 document) {
	    this.document = document;
	}

	
}
