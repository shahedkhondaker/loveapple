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
package cn.loveapple.service.cool.model;

import static cn.loveapple.service.cool.model.LoveappleModel.*;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@SuppressWarnings("serial")
@Model(kind=SITE_CONTENTS_TAG_MODEL)
public class SiteContentsTagModel implements LoveappleModel {

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;

	/**
	 * タグ名
	 */
	@Attribute(unindexed=false)
	private String name;
	

	/**
	 * 件数
	 */
	@Attribute
	private Integer count;

	/**
	 * 言語
	 */
	@Attribute(unindexed = false)
	private String language;
	
	/**
	 * 作成者ID
	 */
	@Attribute(unindexed = false)
	private Key creatorId;

	/**
	 * 作成者名(ペーンネーム)
	 */
	@Attribute
	private String creatorName;
	
	/**
	 * 作成者メール
	 */
	@Attribute
	private String creatorMail;


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
	 * タグ名を取得します。
	 * @return タグ名
	 */
	public String getName() {
	    return name;
	}


	/**
	 * タグ名を設定します。
	 * @param name タグ名
	 */
	public void setName(String name) {
	    this.name = name;
	}


	/**
	 * 件数を取得します。
	 * @return 件数
	 */
	public Integer getCount() {
	    return count;
	}


	/**
	 * 件数を設定します。
	 * @param count 件数
	 */
	public void setCount(Integer count) {
	    this.count = count;
	}


	/**
	 * 言語を取得します。
	 * @return 言語
	 */
	public String getLanguage() {
	    return language;
	}


	/**
	 * 言語を設定します。
	 * @param language 言語
	 */
	public void setLanguage(String language) {
	    this.language = language;
	}


	/**
	 * 作成者IDを取得します。
	 * @return 作成者ID
	 */
	public Key getCreatorId() {
	    return creatorId;
	}


	/**
	 * 作成者IDを設定します。
	 * @param creatorId 作成者ID
	 */
	public void setCreatorId(Key creatorId) {
	    this.creatorId = creatorId;
	}


	/**
	 * 作成者名(ペーンネーム)を取得します。
	 * @return 作成者名(ペーンネーム)
	 */
	public String getCreatorName() {
	    return creatorName;
	}


	/**
	 * 作成者名(ペーンネーム)を設定します。
	 * @param creatorName 作成者名(ペーンネーム)
	 */
	public void setCreatorName(String creatorName) {
	    this.creatorName = creatorName;
	}


	/**
	 * 作成者メールを取得します。
	 * @return 作成者メール
	 */
	public String getCreatorMail() {
	    return creatorMail;
	}


	/**
	 * 作成者メールを設定します。
	 * @param creatorMail 作成者メール
	 */
	public void setCreatorMail(String creatorMail) {
	    this.creatorMail = creatorMail;
	}
}
