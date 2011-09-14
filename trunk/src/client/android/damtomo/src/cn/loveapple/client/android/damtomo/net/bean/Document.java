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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Root(name = "document")
public class Document<D,L> {

	/**
	 * XMLタイプ
	 */
	@Attribute
	private String type;
	/**
	 * レスポンス結果
	 */
	@Element
	private Result result;
		
	/**
	 * レスポンスデータ
	 */
	@Element(name="data", required=false)
	private Data<D> data;
	
	/**
	 * リスト
	 */
	@ElementList(name="list", required=false)
	private DataList<L> list;
	
	/**
	 * @param result
	 * @param data
	 */
	public Document(Result result, Data<D> data, DataList<L> list) {
		super();
		this.result = result;
		this.data = data;
		this.list = list;
	}
	public Document() {
	}
	/**
	 * XMLタイプを取得します。
	 * @return XMLタイプ
	 */
	public String getType() {
	    return type;
	}
	/**
	 * XMLタイプを設定します。
	 * @param type XMLタイプ
	 */
	public void setType(String type) {
	    this.type = type;
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
	public Data<D> getData() {
	    return data;
	}
	/**
	 * レスポンスデータを設定します。
	 * @param data レスポンスデータ
	 */
	public void setData(Data<D> data) {
	    this.data = data;
	}

	public String getFormattedName(){
		return result.getStatusCode() + " -- " + result.getStatus() + " : " +result.getMessage();
	}
	/**
	 * リストを取得します。
	 * @return リスト
	 */
	public DataList<L> getList() {
	    return list;
	}
	/**
	 * リストを設定します。
	 * @param list リスト
	 */
	public void setList(DataList<L> list) {
	    this.list = list;
	}
}
