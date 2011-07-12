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
 
package cn.loveapple.client.android.database;

import static cn.loveapple.client.android.Constant.*;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.loveapple.client.android.LoveappleHealthDatabaseOpenHelper;
import cn.loveapple.client.android.database.entity.Entity;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public abstract class BaseDao implements LoveappleDao{
	protected SQLiteDatabase writableDb;
	protected SQLiteDatabase readableDb;
	
	/**
	 * Loveapple健康DBヘルパー
	 */
	protected LoveappleHealthDatabaseOpenHelper helper = null;
	
	/**
	 * 
	 * @param helper
	 */
	public BaseDao(LoveappleHealthDatabaseOpenHelper helper){
		this.helper = helper;
		writableDb = helper.getWritableDatabase();
		readableDb = helper.getReadableDatabase();
	}
	
	public <T> T save(Entity entity){
		ContentValues values = new ContentValues();
		
		return null;
	}
	
	public void destory(){
		try{
			if(writableDb != null){
				writableDb.close();
			}
			if(readableDb != null){
					readableDb.close();
			}
		}catch (Exception e) {
			Log.i(LOG_TAG, e.getMessage(), e);
		}
	}
}