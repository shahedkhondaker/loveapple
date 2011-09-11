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
package cn.loveapple.client.android.shiba.database.impl;

import static cn.loveapple.client.android.Constant.*;
import static cn.loveapple.client.android.shiba.database.entity.CacheEntity.*;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import cn.loveapple.client.android.shiba.database.CacheDao;
import cn.loveapple.client.android.shiba.database.entity.BookMarkEntity;
import cn.loveapple.client.android.shiba.database.entity.CacheEntity;
import cn.loveapple.client.android.util.StringUtils;

/**
 * キャッシュを操作するDAO
 * 
 * @author loveapple
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class CacheDaoImpl implements CacheDao {

	private SQLiteDatabase readableDatabase;
	
	protected SQLiteDatabase getReadableDatabase(){
		if(readableDatabase == null || !readableDatabase.isOpen()){
			readableDatabase = SQLiteDatabase.openDatabase(
					Environment.getDataDirectory()+"/data/cn.loveapple.client.android.shiba/databases/webviewCache.db",
					null,
					SQLiteDatabase.OPEN_READONLY);
		}
		
		return readableDatabase;
	}
	
	public void destory(){
		try{
			if(readableDatabase != null && readableDatabase.isOpen()){
				readableDatabase.close();
			}
		}catch (Exception e) {
			Log.i(LOG_TAG, e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<String> findCacheHttpUrl(String url, int limit){
		List<CacheEntity> result = findCache("http://" + url, limit);
		List<String> urls = new ArrayList<String>(result.size());
		for (CacheEntity cacheEntity : result) {
			urls.add(cacheEntity.getUrl());
		}
		return urls;
	}

	/**
	 * 
	 */
	@Override
	public List<CacheEntity> findCache(String url, int limit) {
		StringBuilder condition = new StringBuilder();
		List<String> params = new ArrayList<String>();
		if(StringUtils.isNotEmpty(url)){
			condition.append(" AND ");
			condition.append(COLUMN_URL);
			condition.append(" LIKE '?%' ");
			params.add(url);
		}
		Cursor cursor = null;
		List<CacheEntity> result = null;
		
		try{
			readableDatabase = getReadableDatabase();
			cursor = readableDatabase.query(
					TABLE_NAME, 
					BIND_COLUMN, 
					COLUMN_MIMETYPE + "='text/html' " + " ORDER BY " + COLUMN_ID + " DESC LIMIT " + limit ,
					null, null, null, null);
			result = new ArrayList<CacheEntity>();
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				result.add(getUrlHistoryEntity(cursor));
				cursor.moveToNext();
			}
		}finally{
			if(cursor != null){
				cursor.close();
			}
		}
		return result;
	}

	/**
	 * {@linkplain Cursor カーソル}から{@linkplain BookMarkEntity URL履歴}に変換する
	 * 
	 * @param cursor
	 * @return
	 */
	private CacheEntity getUrlHistoryEntity(Cursor cursor) {
		if(cursor == null || cursor.getCount() < 1){
			return null;
		}
		CacheEntity entity = new CacheEntity();
		entity.setId(cursor.getLong(0));
		entity.setUrl(cursor.getString(1));
		entity.setLastModify(cursor.getString(2));
		entity.setEtag(cursor.getString(3));
		entity.setExpires(cursor.getLong(4));
		entity.setExpiresString(cursor.getString(5));
		entity.setMimetype(cursor.getString(6));
		entity.setEncoding(cursor.getString(7));
		entity.setHttpStatus(cursor.getInt(8));
		entity.setLocation(cursor.getString(9));
		entity.setContentLength(cursor.getInt(10));
		entity.setContentDisposition(cursor.getString(11));
		entity.setCrossDomain(cursor.getString(12));
		
		return entity;
	}

}
