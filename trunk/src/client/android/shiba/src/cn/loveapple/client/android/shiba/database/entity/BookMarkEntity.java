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
package cn.loveapple.client.android.shiba.database.entity;

import java.util.Date;

/** 
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class BookMarkEntity {
	
	public static final String TABLE_NAME = "BOOK_MARK";
	public static final String COLUMN_URL = "URL";
	public static final String COLUMN_TIMESTAMP = "TIMESTAMP";
	public static final String COLUMN_TITLE = "TITLE";

	/**
	 * URL
	 */
	private String url;
	/**
	 * タイムスタンプ
	 */
	private Date timestamp;
	/**
	 * タイトル
	 */
	private String title;
	/**
	 * URLを取得します。
	 * @return URL
	 */
	public String getUrl() {
	    return url;
	}
	/**
	 * URLを設定します。
	 * @param url URL
	 */
	public void setUrl(String url) {
	    this.url = url;
	}
	/**
	 * タイムスタンプを取得します。
	 * @return タイムスタンプ
	 */
	public Date getTimestamp() {
	    return timestamp;
	}
	/**
	 * タイムスタンプを設定します。
	 * @param timestamp タイムスタンプ
	 */
	public void setTimestamp(Date timestamp) {
	    this.timestamp = timestamp;
	}
	/**
	 * タイトルを取得します。
	 * @return タイトル
	 */
	public String getTitle() {
	    return title;
	}
	/**
	 * タイトルを設定します。
	 * @param title タイトル
	 */
	public void setTitle(String title) {
	    this.title = title;
	}
}
