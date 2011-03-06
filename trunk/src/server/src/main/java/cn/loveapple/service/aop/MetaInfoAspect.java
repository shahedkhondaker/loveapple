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
package cn.loveapple.service.aop;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slim3.datastore.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.loveapple.service.cool.model.LoveappleModel;
import cn.loveapple.service.cool.model.MetaInfoModel;
import cn.loveapple.service.cool.service.MetaInfoService;

/**
 * 
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Aspect
@Component(value="metaInfoAspect")
@SuppressWarnings({"unused", "unchecked"})
public class MetaInfoAspect {
	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(MetaInfoAspect.class);
	
	/**
	 * メタ情報操作サービス
	 */
	private MetaInfoService metaInfoService;
	
	/**
	 * 
	 * @param retVal
	 */
	@AfterReturning(
			pointcut="execution(* cn.loveapple.service.cool.service.impl.*.update*(..))",
			returning="retVal")
	private void updateMetaInfo(Object retVal){
		if(log.isDebugEnabled()){
			log.debug("AOP: updateMetaInfo. " + retVal);
		}
		if(retVal instanceof LoveappleModel){
			updateMetaInfo((Class<LoveappleModel>) retVal.getClass(), 0);
		}else{
			throw new RuntimeException(" Model is not a type of " + LoveappleModel.class.getName());
		}
	}
	/**
	 * 
	 * @param retVal
	 */
	@AfterReturning(
			pointcut="execution(* cn.loveapple.service.cool.service.impl.*.insert*(..))",
			returning="retVal")
	private void insertMetaInfo(Object retVal){
		if(log.isDebugEnabled()){
			log.debug("AOP: insertMetaInfo. " + retVal);
		}
		if(retVal instanceof LoveappleModel){
			updateMetaInfo((Class<LoveappleModel>) retVal.getClass(), 1);
		}else{
			throw new RuntimeException(" Model is not a type of " + LoveappleModel.class.getName());
		}
	}
	/**
	 * 
	 * @param clz
	 * @return
	 */
	private MetaInfoModel updateMetaInfo(Class<LoveappleModel> clz, int count){
		String kindName = getKindName(clz);
		MetaInfoModel model = metaInfoService.findByKind(kindName);
		MetaInfoModel result = null;
		if(model == null){
			model = new MetaInfoModel();
			model.setKindName(kindName);
			model.setCount(count);
			model.setLastUpdateDate(new Date());
			result = metaInfoService.dmMetaInfo(model);
		}else{
			model.setCount(model.getCount() + count);
			model.setLastUpdateDate(new Date());
			result = metaInfoService.dmMetaInfo(model);
		}
		log.info("Update MetaInfo: " + result);
		return result;
	}
	/**
	 * 
	 * @param clz
	 * @return
	 */
	protected String getKindName(Class<LoveappleModel> clz){
		Model annotation = clz.getAnnotation(Model.class);
		if(StringUtils.isEmpty(annotation.kind())){
			return clz.getName();
		}
		return annotation.kind();
	}
	/**
	 * メタ情報操作サービスを設定します。
	 * @param metaInfoService メタ情報操作サービス
	 */
	@Autowired(required=true)
	public void setMetaInfoService(MetaInfoService metaInfoService) {
	    this.metaInfoService = metaInfoService;
	}
}
