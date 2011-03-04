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
package cn.loveapple.service.cool.service;

import java.util.List;

import cn.loveapple.service.cool.model.SiteContentsCategoryModel;
import cn.loveapple.service.cool.model.SiteContentsFileModel;
import cn.loveapple.service.cool.model.SiteContentsModel;
import cn.loveapple.service.cool.model.SiteContentsTagModel;
import cn.loveapple.service.cool.model.SiteModel;

/**
 * サイトコンテンツ操作サービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public interface SiteContentsService {
	/**
	 * カテゴリ情報を登録する。
	 * 
	 * @param category
	 * @return 登録したカテゴリ情報を戻す
	 */
	public SiteContentsCategoryModel insertSiteContentsCategory(SiteContentsCategoryModel category);
	
	/**
	 * カテゴリ情報を更新する。
	 * 
	 * @param category
	 * @return
	 */
	public SiteContentsCategoryModel updateSiteContentsCategory(SiteContentsCategoryModel category);

	/**
	 * サイト情報を登録する。
	 * 
	 * @param site
	 * @return 登録したサイト情報を戻す
	 */
	public SiteModel insertSite(SiteModel site);
	
	/**
	 * サイト情報を更新する。
	 * 
	 * @param site
	 * @return
	 */
	public SiteModel updateSite(SiteModel site);
	
	/**
	 * 件数を取得して、{@linkplain SiteContentsCategoryModel#getUpdateDate() 更新日時}の降順、
	 * {@linkplain SiteContentsCategoryModel#getName() カテゴリ名}の昇順でコンテンツカテゴリを取得する。<br />
	 * 
	 * @see #findSiteContentsCategory(Long, Long, String, String) 検索処理を行うメソッド
	 * @param siteId サイトID
	 * @param creatorId 作者ID
	 * @param creatorMail 作者メール
	 * @param lang 対象言語
	 * @param start 開始ポイント
	 * @param size 取得件数
	 * @return 検索結果
	 */
	public List<SiteContentsCategoryModel> findSiteContentsCategory(Long siteId, Long creatorId, String creatorMail, String lang, int start, int size);
	
	/**
	 * 
	 * {@linkplain SiteContentsCategoryModel#getUpdateDate() 更新日時}の降順、
	 * {@linkplain SiteContentsCategoryModel#getName() カテゴリ名}の昇順でコンテンツカテゴリを取得する。<br />
	 * 作者メール、又は対象言語は、指定された場合だけ、検索条件にすること。
	 * @param siteId サイトID
	 * @param creatorId 作者ID
	 * @param creatorMail 作者メール
	 * @param lang 対象言語
	 * @return 検索結果
	 */
	public List<SiteContentsCategoryModel> findSiteContentsCategory(Long siteId, Long creatorId, String creatorMail, String lang);
	/**
	 * サイトコンテンツ情報を登録する
	 * 
	 * @param contents
	 * @return
	 */
	public SiteContentsModel insertSiteContentsModel(SiteContentsModel contents);
	
	/**
	 * サイトコンテンツ情報を更新する
	 * 
	 * @param contents
	 * @return
	 */
	public SiteContentsModel updateSiteContentsModel(SiteContentsModel contents);
	
	/**
	 * サイトコンテンツタグ情報を登録する。
	 * 
	 * @param tag
	 * @return
	 */
	public SiteContentsTagModel insertSiteContentsTagModel(SiteContentsTagModel tag);
	
	/**
	 * サイトコンテンツタグ情報を更新する。
	 * 
	 * @param tag
	 * @return
	 */
	public SiteContentsTagModel updateSiteContentsTagModel(SiteContentsTagModel tag);
	
	/**
	 * サイトコンテンツ添付ファイル情報を登録する
	 * 
	 * @param file
	 * @return
	 */
	public SiteContentsFileModel insertSiteContentsFileModel(SiteContentsFileModel file);
	
	/**
	 * サイトコンテンツ添付ファイル情報を更新する
	 * 
	 * @param file
	 * @return
	 */
	public SiteContentsFileModel updateSiteContentsFileModel(SiteContentsFileModel file);
}
