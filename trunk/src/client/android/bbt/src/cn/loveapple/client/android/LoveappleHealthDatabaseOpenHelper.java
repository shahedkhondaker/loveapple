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
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Environment;
import android.util.Log;
import cn.loveapple.client.android.database.entity.TemperatureEntity;


/**
 * 
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class LoveappleHealthDatabaseOpenHelper extends SQLiteOpenHelper {
	
	/**
	 * DB名
	 */
	public static final String DB_NAME="LOVEAPPLE_HEALTH";
	/**
	 * DBパス
	 */
	public static String DB_PATH = Environment.getDataDirectory()+"/data/cn.loveapple.client.android.bbt/databases/";

	/**
	 * 
	 * @param context
	 * @param factory
	 * @param version
	 */
	public LoveappleHealthDatabaseOpenHelper(Context context, CursorFactory factory, int version) {
		super(context, DB_NAME, factory, version);
	}

	/**
	 * 体温テーブルを定義するSQL文
	 */
	private static final String DEFINE_TABLE_TEMPERATURE = "create table "
															+ TemperatureEntity.TABLE_NAME + "("
															+ TemperatureEntity.COLUMN_DATE + " text primary key,"
															+ TemperatureEntity.COLUMN_TIMESTAMP + " text,"
															+ TemperatureEntity.COLUMN_TEMPERATURE + " real,"
															+ TemperatureEntity.COLUMN_COITUS_FLG + " text,"
															+ TemperatureEntity.COLUMN_MENSTRUATION_FLG + " text,"
															+ TemperatureEntity.COLUMN_DYSMENORRHEA_FLG + " text,"
															+ TemperatureEntity.COLUMN_LEUKORRHEA + " text,"
															+ TemperatureEntity.COLUMN_MENSTRUATION_LEVEL + " text,"
															+ TemperatureEntity.COLUMN_MENSTRUATION_CYCLE + " integer);";
	
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
