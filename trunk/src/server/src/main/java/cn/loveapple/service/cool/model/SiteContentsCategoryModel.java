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

import java.util.Date;

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
@Model(kind=SITE_CONTENTS_CATEGORY_MODEL)
public class SiteContentsCategoryModel implements LoveappleModel {

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;

	/**
	 * カテゴリ名
	 */
	@Attribute(unindexed=false)
	private String name;
	

	/**
	 * 位置情報
	 */
	@Attribute(unindexed=false)
	private String language;

	/**
	 * コンテンツ数
	 */
	@Attribute
	private Integer count;

	/**
	 * 紹介
	 */
	@Attribute
	private String detail;

	/**
	 * 更新日時
	 */
	@Attribute
	private Date updateDate;

	/**
	 * 作成日時
	 */
	@Attribute
	private Date insertDate;

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
	 * カテゴリ名を取得します。
	 * @return カテゴリ名
	 */
	public String getName() {
	    return name;
	}

	/**
	 * カテゴリ名を設定します。
	 * @param name カテゴリ名
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * 位置情報を取得します。
	 * @return 位置情報
	 */
	public String getLanguage() {
	    return language;
	}

	/**
	 * 位置情報を設定します。
	 * @param language 位置情報
	 */
	public void setLanguage(String language) {
	    this.language = language;
	}

	/**
	 * コンテンツ数を取得します。
	 * @return コンテンツ数
	 */
	public Integer getCount() {
	    return count;
	}

	/**
	 * コンテンツ数を設定します。
	 * @param count コンテンツ数
	 */
	public void setCount(Integer count) {
	    this.count = count;
	}

	/**
	 * 紹介を取得します。
	 * @return 紹介
	 */
	public String getDetail() {
	    return detail;
	}

	/**
	 * 紹介を設定します。
	 * @param detail 紹介
	 */
	public void setDetail(String detail) {
	    this.detail = detail;
	}

	/**
	 * 更新日時を取得します。
	 * @return 更新日時
	 */
	public Date getUpdateDate() {
	    return updateDate;
	}

	/**
	 * 更新日時を設定します。
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(Date updateDate) {
	    this.updateDate = updateDate;
	}

	/**
	 * 作成日時を取得します。
	 * @return 作成日時
	 */
	public Date getInsertDate() {
	    return insertDate;
	}

	/**
	 * 作成日時を設定します。
	 * @param insertDate 作成日時
	 */
	public void setInsertDate(Date insertDate) {
	    this.insertDate = insertDate;
	}
}
