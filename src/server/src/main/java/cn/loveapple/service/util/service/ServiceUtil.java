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
package cn.loveapple.service.util.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slim3.datastore.ModelMeta;

/**
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@SuppressWarnings("unchecked")
public class ServiceUtil {

	/**
	 * モデルメタインスタンスのストレージ
	 */
	private static final Map<String, ModelMeta> metaStorage = new HashMap<String, ModelMeta>();
	
	/**
	 * <code>cn.loveapple.service.cool.model</code>配下のモデルクラス名から、
	 * <code>cn.loveapple.service.cool.meta</code>配下のメタインスタンスを取得する。
	 * 
	 * 
	 * @param modelName パッケージ名を含んだパス名
	 * @return 当てはまるメタインスタンス
	 */
	public static ModelMeta getModelMeta(String modelName){
		if(StringUtils.isEmpty(modelName)){
			throw new IllegalArgumentException("ModelMeta key is empty.");
		}
		if(metaStorage.get(modelName) == null){
			StringBuilder metaClass = new StringBuilder(modelName.length());
			metaClass.append(modelName);
			metaClass.append("Meta");
			metaClass.replace(26, 31, "meta");
			
			Method getMethod = null;
			try{
				getMethod = Class.forName(metaClass.toString()).getMethod("get");
			}catch (Exception e) {
				throw new RuntimeException("create meta method class error.", e);
			}
			
			try {
				metaStorage.put(modelName, (ModelMeta) getMethod.invoke(null));
			} catch (Exception e) {
				throw new RuntimeException("invoke meta method class error.", e);
			}
		}
		
		return metaStorage.get(modelName);
	}

	/**
	 * モデルメタインスタンスのストレージを取得します。
	 * @return モデルメタインスタンスのストレージ
	 */
	public static Map<String,ModelMeta> getMetastorage() {
	    return metaStorage;
	}
}
