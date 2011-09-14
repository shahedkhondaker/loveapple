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
import org.simpleframework.xml.Root;

/**
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Root(name = "page")
public class Page {
	/**
	 *  ページ総
	 */
	@Attribute(name="count")
	private Integer pageCount;
	/**
	 * 総データ件数
	 */
	@Attribute(name="count")
	private Integer totalCount;
	/**
	 * 前ページ存在フラグ
	 */
	@Attribute(name="count", required=false)
	private String hasPrev;
	/**
	 * 次ページ存在フラグ
	 */
	@Attribute(name="count", required=false)
	private String hasNext;
	/**
	 * ページ総を取得します。
	 * @return ページ総
	 */
	public Integer getPageCount() {
	    return pageCount;
	}
	/**
	 * ページ総を設定します。
	 * @param pageCount ページ総
	 */
	public void setPageCount(Integer pageCount) {
	    this.pageCount = pageCount;
	}
	/**
	 * 総データ件数を取得します。
	 * @return 総データ件数
	 */
	public Integer getTotalCount() {
	    return totalCount;
	}
	/**
	 * 総データ件数を設定します。
	 * @param totalCount 総データ件数
	 */
	public void setTotalCount(Integer totalCount) {
	    this.totalCount = totalCount;
	}
	/**
	 * 前ページ存在フラグを取得します。
	 * @return 前ページ存在フラグ
	 */
	public String getHasPrev() {
	    return hasPrev;
	}
	/**
	 * 前ページ存在フラグを設定します。
	 * @param hasPrev 前ページ存在フラグ
	 */
	public void setHasPrev(String hasPrev) {
	    this.hasPrev = hasPrev;
	}
	/**
	 * 次ページ存在フラグを取得します。
	 * @return 次ページ存在フラグ
	 */
	public String getHasNext() {
	    return hasNext;
	}
	/**
	 * 次ページ存在フラグを設定します。
	 * @param hasNext 次ページ存在フラグ
	 */
	public void setHasNext(String hasNext) {
	    this.hasNext = hasNext;
	}
}
