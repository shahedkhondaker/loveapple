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
package cn.loveapple.client.android.database.impl;

import static cn.loveapple.client.android.database.entity.TemperatureEntity.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.loveapple.client.android.LoveappleHealthDatabaseOpenHelper;
import cn.loveapple.client.android.database.TemperatureDao;
import cn.loveapple.client.android.database.entity.TemperatureEntity;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class TemperatureDaoImpl implements TemperatureDao {
	private LoveappleHealthDatabaseOpenHelper helper = null;
	public TemperatureDaoImpl(Context context){
		helper = new LoveappleHealthDatabaseOpenHelper(context, null);
	}
	
	public TemperatureEntity save(TemperatureEntity source){
		SQLiteDatabase db = helper.getWritableDatabase();
		TemperatureEntity result = null;
		try{
			ContentValues values = new ContentValues();
			values.put(COLUMN_COITUS_FLG, source.getCoitusFlg());
			values.put(COLUMN_DATE, source.getDate());
			values.put(COLUMN_TIMESTAMP, source.getTimestamp());
			values.put(COLUMN_TEMPERATURE, source.getTemperature());
			values.put(COLUMN_MENSTRUATION_FLG, source.getMenstruationFlg());
			values.put(COLUMN_DYSMENORRHEA_FLG, source.getDysmenorrheaFlg());
			values.put(COLUMN_LEUKORRHEA, source.getLeukorrhea());
			values.put(COLUMN_DYSMENORRHEA_LEVEL, source.getDysmenorrhea_level());
			values.put(COLUMN_MENSTRUATION_CYCLE, source.getMenstruation_cycle());
			
			result = findByDate(source.getDate());
			if(result == null){
				db.insert(TABLE_NAME, null, values);
			}else{
				db.update(TABLE_NAME, values, COLUMN_DATE + "=?", new String[]{source.getDate()});
				result = findByDate(source.getDate());
			}
			
		}finally{
			
		}
		
		return result;
	}
	public TemperatureEntity findByDate(String date){
		SQLiteDatabase db = helper.getReadableDatabase();
		
		TemperatureEntity result = null;
		try{
			Cursor cursor = db.query(TABLE_NAME, null, COLUMN_DATE + "=?", new String[]{date}, null, null, null);
			cursor.moveToFirst();
			result = getTemperatureEntity(cursor);
		}finally{
			db.close();
		}
		return result;
	}
	
	public TemperatureEntity getTemperatureEntity(Cursor cursor){
		TemperatureEntity result = new TemperatureEntity();
		result.setDate(cursor.getString(0));
		result.setTimestamp(cursor.getString(1));
		result.setTemperature(cursor.getDouble(2));
		result.setCoitusFlg(cursor.getString(3));
		result.setMenstruationFlg(cursor.getString(4));
		result.setDysmenorrheaFlg(cursor.getString(5));
		result.setLeukorrhea(cursor.getString(6));
		result.setDysmenorrhea_level(cursor.getString(7));
		result.setMenstruation_cycle(cursor.getInt(8));
		
		return result;
	}
}
