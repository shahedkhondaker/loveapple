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
import java.util.TimeZone;

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
@Model(kind=SITE_MODEL)
public class SiteModel implements LoveappleModel {

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;

	/**
	 * 一意名
	 */
	@Attribute(unindexed=false)
	private String unixName;
	
	/**
	 * サイト名
	 */
	@Attribute
	private String name;
	
	/**
	 * 言語
	 */
	@Attribute(unindexed=false)
	private String language;
	
	/**
	 * サイトタイムゾーン
	 */
	@Attribute(lob = true)
	private TimeZone timeZone;

	/**
	 * カテゴリ数
	 */
	@Attribute
	private Integer categoryCount;
	
	/**
	 * タグ数
	 */
	@Attribute
	private Integer tagCount;
	
	/**
	 * 説明
	 */
	@Attribute
	private String description;

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
	 * オーナID
	 */
	@Attribute(lob = true)
	private Key[] ownerId;

	/**
	 * オーナ名
	 */
	@Attribute(lob = true)
	private String[] ownerName;
	
	/**
	 * オーナメール
	 */
	@Attribute(lob = true)
	private String[] ownerMail;

	/**
	 * サイトURL
	 */
	@Attribute
	private String siteUrl;
	
	/**
	 * ロゴパス
	 */
	@Attribute
	private String logo;
	
	/**
	 * Google Analytics Web Property ID
	 */
	@Attribute
	private String googleAnalyticsId;
	
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
	 * 一意名を取得します。
	 * @return 一意名
	 */
	public String getUnixName() {
	    return unixName;
	}

	/**
	 * 一意名を設定します。
	 * @param unixName 一意名
	 */
	public void setUnixName(String unixName) {
	    this.unixName = unixName;
	}

	/**
	 * サイト名を取得します。
	 * @return サイト名
	 */
	public String getName() {
	    return name;
	}

	/**
	 * サイト名を設定します。
	 * @param name サイト名
	 */
	public void setName(String name) {
	    this.name = name;
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
	 * サイトタイムゾーンを取得します。
	 * @return サイトタイムゾーン
	 */
	public TimeZone getTimeZone() {
	    return timeZone;
	}

	/**
	 * サイトタイムゾーンを設定します。
	 * @param timeZone サイトタイムゾーン
	 */
	public void setTimeZone(TimeZone timeZone) {
	    this.timeZone = timeZone;
	}

	/**
	 * カテゴリ数を取得します。
	 * @return カテゴリ数
	 */
	public Integer getCategoryCount() {
	    return categoryCount;
	}

	/**
	 * カテゴリ数を設定します。
	 * @param categoryCount カテゴリ数
	 */
	public void setCategoryCount(Integer categoryCount) {
	    this.categoryCount = categoryCount;
	}

	/**
	 * タグ数を取得します。
	 * @return タグ数
	 */
	public Integer getTagCount() {
	    return tagCount;
	}

	/**
	 * タグ数を設定します。
	 * @param tagCount タグ数
	 */
	public void setTagCount(Integer tagCount) {
	    this.tagCount = tagCount;
	}

	/**
	 * 説明を取得します。
	 * @return 説明
	 */
	public String getDescription() {
	    return description;
	}

	/**
	 * 説明を設定します。
	 * @param description 説明
	 */
	public void setDescription(String description) {
	    this.description = description;
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

	/**
	 * オーナIDを取得します。
	 * @return オーナID
	 */
	public Key[] getOwnerId() {
	    return ownerId;
	}

	/**
	 * オーナIDを設定します。
	 * @param ownerId オーナID
	 */
	public void setOwnerId(Key[] ownerId) {
	    this.ownerId = ownerId;
	}

	/**
	 * オーナ名を取得します。
	 * @return オーナ名
	 */
	public String[] getOwnerName() {
	    return ownerName;
	}

	/**
	 * オーナ名を設定します。
	 * @param ownerName オーナ名
	 */
	public void setOwnerName(String[] ownerName) {
	    this.ownerName = ownerName;
	}

	/**
	 * オーナメールを取得します。
	 * @return オーナメール
	 */
	public String[] getOwnerMail() {
	    return ownerMail;
	}

	/**
	 * オーナメールを設定します。
	 * @param ownerMail オーナメール
	 */
	public void setOwnerMail(String[] ownerMail) {
	    this.ownerMail = ownerMail;
	}

	/**
	 * サイトURLを取得します。
	 * @return サイトURL
	 */
	public String getSiteUrl() {
	    return siteUrl;
	}

	/**
	 * サイトURLを設定します。
	 * @param siteUrl サイトURL
	 */
	public void setSiteUrl(String siteUrl) {
	    this.siteUrl = siteUrl;
	}

	/**
	 * ロゴパスを取得します。
	 * @return ロゴパス
	 */
	public String getLogo() {
	    return logo;
	}

	/**
	 * ロゴパスを設定します。
	 * @param logo ロゴパス
	 */
	public void setLogo(String logo) {
	    this.logo = logo;
	}

	/**
	 * Google Analytics Web Property IDを取得します。
	 * @return Google Analytics Web Property ID
	 */
	public String getGoogleAnalyticsId() {
	    return googleAnalyticsId;
	}

	/**
	 * Google Analytics Web Property IDを設定します。
	 * @param googleAnalyticsId Google Analytics Web Property ID
	 */
	public void setGoogleAnalyticsId(String googleAnalyticsId) {
	    this.googleAnalyticsId = googleAnalyticsId;
	}

}
