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
package cn.loveapple.service.controller.contents.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * 
 * 
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@SuppressWarnings("serial")
public class SiteForm implements Serializable {

	/**
	 * サイト名
	 */
	@NotEmpty
	@Size(max = 100)
	private String name;
	
	/**
	 * UNIX名
	 */
	@Size(max = 50, min = 2)
	@NotEmpty
	private String unixName;
	
	/**
	 * 言語
	 */
	@NotEmpty
	@Size(max = 3)
	private String language;
	
	/**
	 * サイトタイムゾーン
	 */
	@NotEmpty
	@Size(max = 3)
	private String timeZone;
	
	/**
	 * 紹介
	 */
	@Size(max = 1000)
	private String detail;

	/**
	 * オーナ名
	 */
	@NotNull
	private String[] ownerName;
	

	/**
	 * サイトURL
	 */
	@NotEmpty
	@Size(max = 256)
	private String siteUrl;
	
	/**
	 * ロゴパス
	 */
	@Size(max = 256)
	private String logo;
	
	/**
	 * Google Analytics Web Property ID
	 */
	@Size(max = 100)
	private String googleAnalyticsId;

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
	 * UNIX名を取得します。
	 * @return UNIX名
	 */
	public String getUnixName() {
	    return unixName;
	}

	/**
	 * UNIX名を設定します。
	 * @param unixName UNIX名
	 */
	public void setUnixName(String unixName) {
	    this.unixName = unixName;
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
	public String getTimeZone() {
	    return timeZone;
	}

	/**
	 * サイトタイムゾーンを設定します。
	 * @param timeZone サイトタイムゾーン
	 */
	public void setTimeZone(String timeZone) {
	    this.timeZone = timeZone;
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
