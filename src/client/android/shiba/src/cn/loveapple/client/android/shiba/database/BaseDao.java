/*
 * $HeadURL: https://loveapple.googlecode.com/svn/trunk/src/client/android/bbt/src/cn/loveapple/client/android/database/BaseDao.java $
 * $Author: hao0323@gmail.com $
 * $Revision: 239 $
 * $Date: 2011-08-13 17:18:21 +0900 (土, 13 8 2011) $
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
 
package cn.loveapple.client.android.shiba.database;

import static cn.loveapple.client.android.Constant.*;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.loveapple.client.android.LoveappleShibaDatabaseOpenHelper;

/**
 * 
 * @author $Author: hao0323@gmail.com $
 * @version $Revision: 239 $
 * @date $Date: 2011-08-13 17:18:21 +0900 (土, 13 8 2011) $
 * @id $Id: BaseDao.java 239 2011-08-13 08:18:21Z hao0323@gmail.com $
 *
 */
public abstract class BaseDao implements LoveappleDao{
	protected SQLiteDatabase writableDb;
	
	/**
	 * LoveappleシバDBヘルパー
	 */
	protected LoveappleShibaDatabaseOpenHelper helper = null;
	
	/**
	 * 
	 * @param <T>
	 * @return
	 */
	public SQLiteDatabase getWritableDb(){
		if(writableDb == null || !writableDb.isOpen()){
			writableDb = SQLiteDatabase.openDatabase(
					LoveappleShibaDatabaseOpenHelper.DB_PATH + LoveappleShibaDatabaseOpenHelper.DB_NAME,
					null,
					SQLiteDatabase.OPEN_READWRITE);
		}
		return writableDb;
	}
	
	/**
	 * 
	 * @param helper
	 */
	public BaseDao(LoveappleShibaDatabaseOpenHelper helper){
		this.helper = helper;
	}
	
	public void destory(){
		try{
			if(writableDb != null && writableDb.isOpen()){
				writableDb.close();
			}
		}catch (Exception e) {
			Log.i(LOG_TAG, e.getMessage(), e);
		}
	}
}