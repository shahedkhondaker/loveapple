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
package cn.loveapple.service.cool.service.impl;

import static cn.loveapple.service.util.service.LocaleUtil.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EqualCriterion;
import org.slim3.datastore.FilterCriterion;
import org.springframework.util.CollectionUtils;

import cn.loveapple.service.cool.meta.SiteContentsCategoryModelMeta;
import cn.loveapple.service.cool.model.SiteContentsCategoryModel;
import cn.loveapple.service.cool.model.SiteContentsFileModel;
import cn.loveapple.service.cool.model.SiteContentsModel;
import cn.loveapple.service.cool.model.SiteContentsTagModel;
import cn.loveapple.service.cool.service.SiteContentsService;


/**
 * サイトコンテンツ操作サービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class SiteContentsServiceImpl implements SiteContentsService {

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsCategoryModel insertSiteContentsCategory(
			SiteContentsCategoryModel category) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsCategoryModel updateSiteContentsCategory(
			SiteContentsCategoryModel category) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<SiteContentsCategoryModel> findSiteContentsCategory(
			String creatorMail, String lang, int start, int size) {
		if(!isSupportedLanguage(lang)){
			throw new RuntimeException("not be supported language. " + lang);
		}
		List<SiteContentsCategoryModel> tmp = findSiteContentsCategory(null, lang);
		
		if(CollectionUtils.isEmpty(tmp) || tmp.size() < start){
			return null;
		}
		int startSize = tmp.size() - start;
		int realSize = (tmp.size() - startSize) < size ? (tmp.size() - startSize) : size;
		List<SiteContentsCategoryModel> result = new ArrayList<SiteContentsCategoryModel>(realSize);
		for (int i = startSize; i < realSize; i++) {
			result.add(tmp.get(i));
		}
		return result;
	}
	
	/**
	 * {@linkplain SiteContentsCategoryModel#getUpdateDate() 更新日時}の降順、
	 * {@linkplain SiteContentsCategoryModel#getName() カテゴリ名}の昇順でコンテンツカテゴリを取得する。<br />
	 * 作者メール、又は対象言語は、指定された場合だけ、検索条件にすること。
	 * 
	 * @param creatorMail 作者メール
	 * @param lang 対象言語
	 * @return
	 */
	public List<SiteContentsCategoryModel> findSiteContentsCategory(
			String creatorMail, String lang) {
		SiteContentsCategoryModelMeta meta = SiteContentsCategoryModelMeta.get();
		List<EqualCriterion> filter = new ArrayList<EqualCriterion>(2);
		if(StringUtils.isNotEmpty(creatorMail)){
			filter.add(meta.creatorMail.equal(creatorMail));
		}
		if(StringUtils.isNotEmpty(lang)){
			filter.add(meta.language.equal(lang));
		}
		return Datastore.query(SiteContentsCategoryModel.class)
			.filter(filter.toArray(new FilterCriterion[]{}))
			.sort(meta.updateDate.desc, meta.name.asc).asList();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsModel insertSiteContentsModel(SiteContentsModel contents) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsModel updateSiteContentsModel(SiteContentsModel contents) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsTagModel insertSiteContentsTagModel(
			SiteContentsTagModel tag) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsTagModel updateSiteContentsTagModel(
			SiteContentsTagModel tag) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsFileModel insertSiteContentsFileModel(
			SiteContentsFileModel file) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsFileModel updateSiteContentsFileModel(
			SiteContentsFileModel file) {
		// TODO Auto-generated method stub
		return null;
	}

}
