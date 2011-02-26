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
package cn.loveapple.service.cool.model;

import static cn.loveapple.service.cool.model.LoveappleModel.*;

import java.util.Locale;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@SuppressWarnings("serial")
@Model(kind=FIXED_MAIL_MODEL)
public class FixedMailModel implements LoveappleModel {

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;

	/**
	 * メールの一意コード
	 */
	@Attribute(unindexed=false)
	private String mailCode;
	
	/**
	 * 送信(返信)元アドレス
	 */
	@Attribute
	private String fromAddress;

	/**
	 * 位置情報
	 */
	@Attribute(lob=true)
	private Locale locale;

	/**
	 * タイトル
	 */
	@Attribute
	private String subject;

	/**
	 * 内容
	 */
	@Attribute
	private String body;

	/**
	 * エンコード
	 */
	@Attribute
	private String encode;

	/**
	 * キーを取得します。
	 * @return キー
	 */
	public Key getKey() {
	    return key;
	}

	/**
	 * キーを設定します。
	 * @param key キー
	 */
	public void setKey(Key key) {
	    this.key = key;
	}

	/**
	 * メールの一意コードを取得します。
	 * @return メールの一意コード
	 */
	public String getMailCode() {
	    return mailCode;
	}

	/**
	 * メールの一意コードを設定します。
	 * @param mailCode メールの一意コード
	 */
	public void setMailCode(String mailCode) {
	    this.mailCode = mailCode;
	}

	/**
	 * 送信(返信)元アドレスを取得します。
	 * @return 送信(返信)元アドレス
	 */
	public String getFromAddress() {
	    return fromAddress;
	}

	/**
	 * 送信(返信)元アドレスを設定します。
	 * @param fromAddress 送信(返信)元アドレス
	 */
	public void setFromAddress(String fromAddress) {
	    this.fromAddress = fromAddress;
	}

	/**
	 * 位置情報を取得します。
	 * @return 位置情報
	 */
	public Locale getLocale() {
	    return locale;
	}

	/**
	 * 位置情報を設定します。
	 * @param locale 位置情報
	 */
	public void setLocale(Locale locale) {
	    this.locale = locale;
	}

	/**
	 * タイトルを取得します。
	 * @return タイトル
	 */
	public String getSubject() {
	    return subject;
	}

	/**
	 * タイトルを設定します。
	 * @param subject タイトル
	 */
	public void setSubject(String subject) {
	    this.subject = subject;
	}

	/**
	 * 内容を取得します。
	 * @return 内容
	 */
	public String getBody() {
	    return body;
	}

	/**
	 * 内容を設定します。
	 * @param body 内容
	 */
	public void setBody(String body) {
	    this.body = body;
	}

	/**
	 * エンコードを取得します。
	 * @return エンコード
	 */
	public String getEncode() {
	    return encode;
	}

	/**
	 * エンコードを設定します。
	 * @param encode エンコード
	 */
	public void setEncode(String encode) {
	    this.encode = encode;
	}
}
