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

import java.io.Serializable;

import cn.loveapple.service.cool.model.health.BasalBodyTemperatureModel;

/**
 * モデル名の定数群
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
public interface LoveappleModel extends Serializable {
	/**
	 * {@linkplain MetaInfoModel}のモデル名
	 */
	public static final String META_INFO_MODEL = "MetaInfo";
	
	/**
	 * {@linkplain LoveappleMemberModel}のモデル名
	 */
	public static final String LOVEAPPLE_MEMBER_MODEL = "LoveappleMember";
	
	/**
	 * {@linkplain MEMBER_GROUP_MODEL}のモデル名
	 */
	public static final String MEMBER_GROUP_MODEL = "MemberGroup";
	
	/**
	 * {@linkplain FixedMailModel}のモデル名
	 */
	public static final String FIXED_MAIL_MODEL = "FixedMail";
	
	/**
	 * {@linkplain SiteContentsModel}のモデル名
	 */
	public static final String SITE_CONTENTS_MODEL = "SiteContents";
	
	/**
	 * {@linkplain SiteContentsCategoryModel}のモデル名
	 */
	public static final String SITE_CONTENTS_CATEGORY_MODEL = "SiteContentsCategory";
	
	/**
	 * {@linkplain SiteContentsTagModel}のモデル名
	 */
	public static final String SITE_CONTENTS_TAG_MODEL = "SiteContentsTag";
	
	/**
	 * {@linkplain SiteContentsFileModel}のモデル名
	 */
	public static final String SITE_CONTENTS_FILE_MODEL = "SiteContentsFile";
	
	/**
	 * {@linkplain SiteModel}のモデル名
	 */
	public static final String SITE_MODEL = "Site";
	
	/**
	 * {@linkplain BasalBodyTemperatureModel}のモデル名
	 */
	public static final String BASAL_BODY_TEMPERATURE_MODEL = "BasalBodyTemperature";
}
