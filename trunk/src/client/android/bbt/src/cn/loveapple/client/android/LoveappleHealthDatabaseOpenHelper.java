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

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
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
public class LoveappleHealthDatabaseOpenHelper extends SQLiteOpenHelper {
	
	/**
	 * DB名
	 */
	public static final String DB_NAME="LOVEAPPLE_HEALTH";
	

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
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("init", "onCreate.");
		try{
			StringBuilder createSql = new StringBuilder();
			createSql.append("create table temperature(");
			createSql.append("date text primary key");
			createSql.append(",timestamp text");
			createSql.append(",temperature real");
			createSql.append(",coitus_flg text");
			createSql.append(",menstruation_flg text");
			createSql.append(",dysmenorrhea_flg text");
			createSql.append(",leukorrhea text");
			createSql.append(",dysmenorrhea_level text");
			createSql.append(",menstruation_cycle integer");
			createSql.append(");");
			
			db.execSQL(createSql.toString());
			System.out.println("!!!!create table.");
			Log.i("init", "create table.");
			
			ContentValues values = new ContentValues();
			values.put("date", "first");
			db.insert("temperature", null, values);
			System.out.println("!!!!insert");
			Log.i("init", "insert.");
			db.setTransactionSuccessful();
		}finally{
			db.endTransaction();
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("init", "onUpgrade.");
		
	}

}
