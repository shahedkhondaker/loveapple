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
import cn.loveapple.client.android.database.entity.TemperatureEntity;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public abstract class BaseDao implements LoveappleDao{
	protected TemperatureEntity EMPTY_TEMPERATURE_ENTITY = new EmptyTemperatureEntity();
	protected SQLiteDatabase writableDb;
	
	/**
	 * Loveapple健康DBヘルパー
	 */
	protected LoveappleHealthDatabaseOpenHelper helper = null;
	
	/**
	 * 
	 * @param <T>
	 * @return
	 */
	public SQLiteDatabase getWritableDb(){
		if(writableDb == null || !writableDb.isOpen()){
			writableDb = SQLiteDatabase.openDatabase(
					LoveappleHealthDatabaseOpenHelper.DB_PATH + LoveappleHealthDatabaseOpenHelper.DB_NAME,
					null,
					SQLiteDatabase.OPEN_READWRITE);
		}
		return writableDb;
	}
	
	/**
	 * 
	 * @param helper
	 */
	public BaseDao(LoveappleHealthDatabaseOpenHelper helper){
		this.helper = helper;
		//writableDb = helper.getWritableDatabase();
		//readableDb = helper.getReadableDatabase();
	}
	
	/**
	 * TODO
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public <T> T save(Entity entity){
		ContentValues values = new ContentValues();
		
		return null;
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

	/**
	 * 属性を設定しない{@linkplain TemperatureEntity}
	 * 
	 * @author $author:$
	 * @version $Revision$
	 * @date $Date$
	 * @id $Id$
	 *
	 */
    public static final class EmptyTemperatureEntity extends TemperatureEntity{

    	/**
    	 * 日付を設定します。
    	 * @param date 日付
    	 */
    	public void setDate(String date) {
    	}
    	/**
    	 * タイムスタンプを設定します。
    	 * @param timestamp タイムスタンプ
    	 */
    	public void setTimestamp(String timestamp) {}
    	/**
    	 * 体温を設定します。
    	 * @param temperature 体温
    	 */
    	public void setTemperature(Double temperature) {}
    	/**
    	 * セックスを設定します。
    	 * @param coitusFlg セックス
    	 */
    	public void setCoitusFlg(String coitusFlg) {}
    	/**
    	 * 生理フラグを設定します。
    	 * @param menstruationFlg 生理フラグ
    	 */
    	public void setMenstruationFlg(String menstruationFlg) {}
    	/**
    	 * 生理痛フラグを設定します。
    	 * @param dysmenorrheaFlg 生理痛フラグ
    	 */
    	public void setDysmenorrheaFlg(String dysmenorrheaFlg) {}
    	/**
    	 * 下り物を設定します。
    	 * @param leukorrhea 下り物
    	 */
    	public void setLeukorrhea(String leukorrhea) {}
    	/**
    	 * 生理量を設定します。
    	 * @param menstruationLevel 生理量
    	 */
    	public void setMenstruationLevel(String menstruationLevel) {}
    	/**
    	 * 周期日数を設定します。
    	 * @param menstruationCycle 周期日数
    	 */
    	public void setMenstruationCycle(Integer menstruationCycle) {}
    }
}