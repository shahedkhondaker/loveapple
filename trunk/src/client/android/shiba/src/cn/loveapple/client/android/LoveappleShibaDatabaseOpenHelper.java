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
package cn.loveapple.client.android;

import static cn.loveapple.client.android.Constant.*;
import cn.loveapple.client.android.shiba.database.entity.ProxyServerEntity;
import cn.loveapple.client.android.shiba.database.entity.BookMarkEntity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;
import android.util.Log;


/**
 * 
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class LoveappleShibaDatabaseOpenHelper extends SQLiteOpenHelper {
	
	/**
	 * DB名
	 */
	public static final String DB_NAME="LOVEAPPLE_SHIBA";
	/**
	 * DBパス
	 */
	public static String DB_PATH = Environment.getDataDirectory()+"/data/cn.loveapple.client.android.shiba/databases/";

	/**
	 * 
	 * @param context
	 * @param factory
	 * @param version
	 */
	public LoveappleShibaDatabaseOpenHelper(Context context, CursorFactory factory, int version) {
		super(context, DB_NAME, factory, version);
	}

	/**
	 * 体温テーブルを定義するSQL文
	 */
	private static final String DEFINE_TABLE_TEMPERATURE = "create table "
															+ ProxyServerEntity.TABLE_NAME + "("
															+ ProxyServerEntity.COLUMN_URL + " text primary key,"
															+ ProxyServerEntity.COLUMN_TIMESTAMP + " integer,"
															+ ProxyServerEntity.COLUMN_STATUS + " integer,"
															+ ProxyServerEntity.COLUMN_TITLE + " text); "
															+ "create table "
															+ BookMarkEntity.TABLE_NAME + " ("
															+ BookMarkEntity.COLUMN_URL + " text primary key,"
															+ BookMarkEntity.COLUMN_TITLE + " text,"
															+ BookMarkEntity.COLUMN_TIMESTAMP + " integer);";
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		try{
			StringBuilder createSql = new StringBuilder();
			
			createSql.append(DEFINE_TABLE_TEMPERATURE);
			
			Log.i(LOG_TAG, "init DB:" + createSql);
			
			db.execSQL(createSql.toString());
			
			db.setTransactionSuccessful();
		}finally{
			db.close();
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(LOG_TAG, "onUpgrade.");
		
	}

}
