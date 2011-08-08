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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.util.CollectionUtils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.loveapple.client.android.LoveappleHealthDatabaseOpenHelper;
import cn.loveapple.client.android.database.BaseDao;
import cn.loveapple.client.android.database.TemperatureDao;
import cn.loveapple.client.android.database.entity.TemperatureEntity;
import cn.loveapple.client.android.util.DateUtil;
import cn.loveapple.client.android.util.StringUtils;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class TemperatureDaoImpl extends BaseDao implements TemperatureDao {
	
	/**
	 * 月数の境界
	 */
	public static final int LIMIT_MONTH = 6;

	
	/**
	 * 
	 * @param helper
	 */
	public TemperatureDaoImpl(LoveappleHealthDatabaseOpenHelper helper){
		super(helper);
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public TemperatureEntity save(TemperatureEntity source){
		TemperatureEntity result = null;

		ContentValues values = new ContentValues();
		values.put(COLUMN_COITUS_FLG, source.getCoitusFlg());
		values.put(COLUMN_DATE, source.getDate());
		values.put(COLUMN_TIMESTAMP, source.getTimestamp());
		values.put(COLUMN_TEMPERATURE, source.getTemperature());
		values.put(COLUMN_MENSTRUATION_FLG, source.getMenstruationFlg());
		values.put(COLUMN_DYSMENORRHEA_FLG, source.getDysmenorrheaFlg());
		values.put(COLUMN_LEUKORRHEA, source.getLeukorrhea());
		values.put(COLUMN_MENSTRUATION_LEVEL, source.getMenstruationLevel());
		values.put(COLUMN_MENSTRUATION_CYCLE, source.getMenstruationCycle());
		try{
			writableDb = getWritableDb();
			
			int colNum = writableDb.update(TABLE_NAME, values, COLUMN_DATE + "=?", new String[]{source.getDate()});
			if(colNum < 1) {
				writableDb.insert(TABLE_NAME, null, values);
				Log.i("BBT", values.toString());
			}
			result = findByDate(source.getDate());
		}finally{
			writableDb.close();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TemperatureEntity> findByTerm(String start, String end){
		List<TemperatureEntity> result = null;
		
		try{
			//readableDb = helper.getReadableDatabase();
			writableDb = getWritableDb();
			
			Cursor cursor = writableDb.query(
					TABLE_NAME, 
					null, 
					"?<=" + COLUMN_DATE + " AND ?>=" + COLUMN_DATE + " ORDER BY " + COLUMN_DATE ,
					new String[]{start, end}, null, null, null);
			result = new ArrayList<TemperatureEntity>();
			cursor.moveToFirst();
			while(!cursor.isAfterLast()){
				result.add(getTemperatureEntity(cursor));
				cursor.moveToNext();
			}
		}finally{
			writableDb.close();
		}
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TemperatureEntity findByDate(String date){
		
		TemperatureEntity result = null;
		try{
			//readableDb = helper.getReadableDatabase();
			writableDb = getWritableDb();
			
			Cursor cursor = writableDb.query(
					TABLE_NAME,
					null, 
					COLUMN_DATE + " <=? ORDER BY " + COLUMN_DATE + " DESC LIMIT 1",
					new String[]{date}, null, null, null);
			cursor.moveToFirst();
			result = getTemperatureEntity(cursor);
		}finally{
			if(writableDb != null){
				writableDb.close();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param cursor
	 * @return
	 */
	private TemperatureEntity getTemperatureEntity(Cursor cursor){
		if(cursor.getCount() < 1){
			return null;
		}
		TemperatureEntity result = new TemperatureEntity();
		result.setDate(cursor.getString(0));
		result.setTimestamp(cursor.getString(1));
		result.setTemperature(cursor.getDouble(2));
		result.setCoitusFlg(cursor.getString(3));
		result.setMenstruationFlg(cursor.getString(4));
		result.setDysmenorrheaFlg(cursor.getString(5));
		result.setLeukorrhea(cursor.getString(6));
		result.setMenstruationLevel(cursor.getString(7));
		result.setMenstruationCycle(cursor.getInt(8));
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedMap<String, TemperatureEntity> findTemperatureMap() {
		return findTemperatureMap(null) ;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedMap<String, TemperatureEntity> findTemperatureMap(String datePoint) {
		Calendar current = Calendar.getInstance();
		Calendar start = Calendar.getInstance();
		if(StringUtils.isNotEmpty(datePoint)){
			current.setTime(DateUtil.paseDate(datePoint, DateUtil.DATE_PTTERN_YYYYMMDD));
		}
		start.set(
				current.get(Calendar.YEAR),
				current.get(Calendar.MONTH) - LIMIT_MONTH,
				current.get(Calendar.DAY_OF_MONTH));
		
		List<TemperatureEntity> tempList = findByTerm(
												DateUtil.toDateString(start.getTime()),
												DateUtil.toDateString(current.getTime()));
		
		SortedMap<String, TemperatureEntity> result = new TreeMap<String, TemperatureEntity>();

		if(CollectionUtils.isEmpty(tempList)){
			return result;
		}
		
		// 必要なキー一覧を生成
		SortedSet<String> dateSet = createDateKey(tempList.get(0).getDate());
		for(TemperatureEntity temp : tempList){
			dateSet.remove(temp.getDate());
			result.put(temp.getDate(), temp);
		}
		for(String date : dateSet){
			result.put(date, EMPTY_TEMPERATURE_ENTITY);
		}
		
		return result;
	}
	
	/**
	 * 指定された開始日付から現在日付まで、日付文字の一覧を作成
	 * @param startDate
	 * @return
	 */
	private SortedSet<String> createDateKey(String startDate){
		SortedSet<String> result = new TreeSet<String>();
		Date now = new Date();
		Date start = DateUtil.paseDate(startDate, DateUtil.DATE_PTTERN_YYYYMMDD);
		
		Calendar date = Calendar.getInstance();
		date.setTime(start);
		for (int i = 0; now.after(date.getTime()); i++){
			date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH) + i);
			result.add(DateUtil.toDateString(date.getTime()));
		}
		
		return result;
	}

}
