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

import static cn.loveapple.service.cool.model.LoveappleModel.*;
import static cn.loveapple.service.util.LocaleUtil.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EqualCriterion;
import org.slim3.datastore.FilterCriterion;
import org.springframework.util.CollectionUtils;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import cn.loveapple.service.cool.meta.SiteContentsCategoryModelMeta;
import cn.loveapple.service.cool.meta.SiteModelMeta;
import cn.loveapple.service.cool.model.SiteContentsCategoryModel;
import cn.loveapple.service.cool.model.SiteContentsFileModel;
import cn.loveapple.service.cool.model.SiteContentsModel;
import cn.loveapple.service.cool.model.SiteContentsTagModel;
import cn.loveapple.service.cool.model.SiteModel;
import cn.loveapple.service.cool.service.SiteContentsService;
import cn.loveapple.service.type.ServiceComp;


/**
 * サイトコンテンツ操作サービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@ServiceComp
public class SiteContentsServiceImpl extends BaseServiceImpl implements SiteContentsService {

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsCategoryModel insertSiteContentsCategory(
			SiteContentsCategoryModel category) {
		if(category == null){
			throw new IllegalArgumentException("category is empty.");
		}
		return dmLoveappleModel(category);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsCategoryModel updateSiteContentsCategory(
			SiteContentsCategoryModel category) {
		if(category == null){
			throw new IllegalArgumentException("category is empty.");
		}
		return null;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<SiteContentsCategoryModel> findSiteContentsCategory(
			Long siteId, Long creatorId, String creatorMail, String lang, int start, int size) {
		if(!isSupportedLanguage(lang)){
			throw new RuntimeException("not be supported language. " + lang);
		}
		List<SiteContentsCategoryModel> tmp = findSiteContentsCategory(siteId, creatorId, creatorMail, lang);
		
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
	 * 
	 * {@inheritDoc}
	 */
	public List<SiteContentsCategoryModel> findSiteContentsCategory(
			Long siteId, Long creatorId, String creatorMail, String lang) {
		SiteContentsCategoryModelMeta meta = SiteContentsCategoryModelMeta.get();
		List<EqualCriterion> filter = new ArrayList<EqualCriterion>(3);
		if(siteId != null){
			filter.add(meta.siteId.equal(siteId));
		}
		if(creatorId != null){
			filter.add(meta.creatorId.equal(creatorId));
		}
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
		if(contents == null){
			throw new IllegalArgumentException("contents is empty.");
		}
		return dmLoveappleModel(contents);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsModel updateSiteContentsModel(SiteContentsModel contents) {
		if(contents == null){
			throw new IllegalArgumentException("contents is empty.");
		}
		return dmLoveappleModel(contents);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsTagModel insertSiteContentsTagModel(
			SiteContentsTagModel tag) {
		if(tag == null){
			throw new IllegalArgumentException("tag is empty.");
		}
		return dmLoveappleModel(tag);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsTagModel updateSiteContentsTagModel(
			SiteContentsTagModel tag) {
		if(tag == null){
			throw new IllegalArgumentException("tag is empty.");
		}
		return dmLoveappleModel(tag);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsFileModel insertSiteContentsFileModel(
			SiteContentsFileModel file) {
		if(file == null){
			throw new IllegalArgumentException("file is empty.");
		}
		return dmLoveappleModel(file);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteContentsFileModel updateSiteContentsFileModel(
			SiteContentsFileModel file) {
		if(file == null){
			throw new IllegalArgumentException("file is empty.");
		}
		return dmLoveappleModel(file);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteModel insertSite(SiteModel site) {
		if(site == null){
			throw new IllegalArgumentException("site is empty.");
		}
		return dmLoveappleModel(site);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public SiteModel updateSite(SiteModel site) {
		if(site == null){
			throw new IllegalArgumentException("site is empty.");
		}
		return dmLoveappleModel(site);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<SiteModel> findSite(Long ...idList){
		if(idList == null){
			return null;
		}
		List<Key> keyList = new ArrayList<Key>(idList.length);
		for (Long id : idList) {
			keyList.add(KeyFactory.createKey(SITE_MODEL, id));
		}
		SiteModelMeta meta = SiteModelMeta.get();
		
		return Datastore.query(SiteModel.class).filter(
				meta.key.in((Key[]) keyList.toArray())).asList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SiteModel findSite(String unixName) {
		if(StringUtils.isEmpty(unixName)){
			throw new IllegalArgumentException("UNIX name is empty.");
		}
		
		SiteModelMeta meta = SiteModelMeta.get();
		
		return Datastore.query(SiteModel.class).filter(meta.unixName.equal(unixName)).asSingle();
	}
}
