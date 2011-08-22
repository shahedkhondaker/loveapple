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

/**
 * 
 * データベースwebviewCache.dbのCacheテーブルのエンティティ
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class CacheEntity {

	public static final String TABLE_NAME = "cache";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ETAG = "etag";
	public static final String COLUMN_LAST_MODIFY = "lastmodify";
	public static final String COLUMN_URL = "URL";
	public static final String COLUMN_FILE_PATH = "filePath";
	public static final String COLUMN_EXPIRES = "expires";
	public static final String COLUMN_EXPIRES_STRING = "expiresstring";
	public static final String COLUMN_MIMETYPE = "mimetype";
	public static final String COLUMN_ENCODING = "encoding";
	public static final String COLUMN_HTTP_STATUS = "httpstatus";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_CONTENT_LENGTH = "contentlength";
	public static final String COLUMN_CONTENT_DISPOSITION = "contentdisposition";
	public static final String COLUMN_CROSSD_OMAIN = "crossdomain";
	
	private Long id; 
	private String url;
	private String filePath;
	private String lastModify;
	private String etag;
	private Long expires;
	private String expiresString;
	private String mimetype;
	private String encoding;
	private Integer httpStatus;
	private String location;
	private Integer contentLength;
	private String contentDisposition;
	private String crossDomain;
	/**
	 * idを取得します。
	 * @return id
	 */
	public Long getId() {
	    return id;
	}
	/**
	 * idを設定します。
	 * @param id id
	 */
	public void setId(Long id) {
	    this.id = id;
	}
	/**
	 * urlを取得します。
	 * @return url
	 */
	public String getUrl() {
	    return url;
	}
	/**
	 * urlを設定します。
	 * @param url url
	 */
	public void setUrl(String url) {
	    this.url = url;
	}
	/**
	 * filePathを取得します。
	 * @return filePath
	 */
	public String getFilePath() {
	    return filePath;
	}
	/**
	 * filePathを設定します。
	 * @param filePath filePath
	 */
	public void setFilePath(String filePath) {
	    this.filePath = filePath;
	}
	/**
	 * lastModifyを取得します。
	 * @return lastModify
	 */
	public String getLastModify() {
	    return lastModify;
	}
	/**
	 * lastModifyを設定します。
	 * @param lastModify lastModify
	 */
	public void setLastModify(String lastModify) {
	    this.lastModify = lastModify;
	}
	/**
	 * etagを取得します。
	 * @return etag
	 */
	public String getEtag() {
	    return etag;
	}
	/**
	 * etagを設定します。
	 * @param etag etag
	 */
	public void setEtag(String etag) {
	    this.etag = etag;
	}
	/**
	 * expiresを取得します。
	 * @return expires
	 */
	public Long getExpires() {
	    return expires;
	}
	/**
	 * expiresを設定します。
	 * @param expires expires
	 */
	public void setExpires(Long expires) {
	    this.expires = expires;
	}
	/**
	 * expiresStringを取得します。
	 * @return expiresString
	 */
	public String getExpiresString() {
	    return expiresString;
	}
	/**
	 * expiresStringを設定します。
	 * @param expiresString expiresString
	 */
	public void setExpiresString(String expiresString) {
	    this.expiresString = expiresString;
	}
	/**
	 * mimetypeを取得します。
	 * @return mimetype
	 */
	public String getMimetype() {
	    return mimetype;
	}
	/**
	 * mimetypeを設定します。
	 * @param mimetype mimetype
	 */
	public void setMimetype(String mimetype) {
	    this.mimetype = mimetype;
	}
	/**
	 * encodingを取得します。
	 * @return encoding
	 */
	public String getEncoding() {
	    return encoding;
	}
	/**
	 * encodingを設定します。
	 * @param encoding encoding
	 */
	public void setEncoding(String encoding) {
	    this.encoding = encoding;
	}
	/**
	 * httpStatusを取得します。
	 * @return httpStatus
	 */
	public Integer getHttpStatus() {
	    return httpStatus;
	}
	/**
	 * httpStatusを設定します。
	 * @param httpStatus httpStatus
	 */
	public void setHttpStatus(Integer httpStatus) {
	    this.httpStatus = httpStatus;
	}
	/**
	 * locationを取得します。
	 * @return location
	 */
	public String getLocation() {
	    return location;
	}
	/**
	 * locationを設定します。
	 * @param location location
	 */
	public void setLocation(String location) {
	    this.location = location;
	}
	/**
	 * contentLengthを取得します。
	 * @return contentLength
	 */
	public Integer getContentLength() {
	    return contentLength;
	}
	/**
	 * contentLengthを設定します。
	 * @param contentLength contentLength
	 */
	public void setContentLength(Integer contentLength) {
	    this.contentLength = contentLength;
	}
	/**
	 * contentDispositionを取得します。
	 * @return contentDisposition
	 */
	public String getContentDisposition() {
	    return contentDisposition;
	}
	/**
	 * contentDispositionを設定します。
	 * @param contentDisposition contentDisposition
	 */
	public void setContentDisposition(String contentDisposition) {
	    this.contentDisposition = contentDisposition;
	}
	/**
	 * crossDomainを取得します。
	 * @return crossDomain
	 */
	public String getCrossDomain() {
	    return crossDomain;
	}
	/**
	 * crossDomainを設定します。
	 * @param crossDomain crossDomain
	 */
	public void setCrossDomain(String crossDomain) {
	    this.crossDomain = crossDomain;
	}
}
