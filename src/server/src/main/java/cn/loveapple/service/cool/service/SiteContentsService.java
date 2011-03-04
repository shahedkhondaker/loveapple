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
	 * 指定される言語のカテゴリを検索する
	 * 
	 * @param lang
	 * @param start
	 * @param size
	 * @return
	 */
	public List<SiteContentsCategoryModel> findSiteContentsCategory(String lang, int start, int size);
	
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
