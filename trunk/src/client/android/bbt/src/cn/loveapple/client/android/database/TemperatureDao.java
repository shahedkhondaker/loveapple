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

import java.util.List;
import java.util.Map;

import cn.loveapple.client.android.database.entity.TemperatureEntity;

/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public interface TemperatureDao extends LoveappleDao {
	/**
	 * 期間から{@linkplain TemperatureEntity 体温}を検索する
	 * @param start
	 * @param end
	 * @return
	 */
	public List<TemperatureEntity> findByTerm(String start, String end);
	/**
	 * 指定された日付の{@linkplain TemperatureEntity 体温}を取得する
	 * @param date
	 * @return
	 */
	public TemperatureEntity findByDate(String date);
	/**
	 * {@linkplain TemperatureEntity 体温}を保存・更新する
	 * @param source
	 * @return
	 */
	public TemperatureEntity save(TemperatureEntity source);
	
	/**
	 * 
	 * 半年分の{@linkplain TemperatureEntity 体温}を取得する
	 * 
	 * @param datePoint 時間計算ポイント
	 * @return
	 */
	public Map<String, TemperatureEntity> findTemperatureMap(String datePoint);

	/**
	 * 
	 * 半年分の{@linkplain TemperatureEntity 体温}を取得する
	 * 
	 * @return
	 */
	public Map<String, TemperatureEntity> findTemperatureMap();
}
