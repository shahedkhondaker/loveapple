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
package cn.loveapple.service.cool.service.health.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slim3.datastore.Datastore;

import cn.loveapple.service.cool.meta.health.BasalBodyTemperatureModelMeta;
import cn.loveapple.service.cool.model.health.BasalBodyTemperatureModel;
import cn.loveapple.service.cool.service.health.BasalBodyTemperatureService;
import cn.loveapple.service.cool.service.impl.BaseServiceImpl;
import cn.loveapple.service.type.ServiceComp;
import cn.loveapple.service.util.DateUtil;

/**
 * loveapple基礎体温サービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@ServiceComp
public class BasalBodyTemperatureServiceImpl extends BaseServiceImpl implements BasalBodyTemperatureService {

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<BasalBodyTemperatureModel> findBasalBodyTemperatureByUser(
			String mail, String startDay, String endDay) {
		if(StringUtils.isEmpty(mail)){
			throw new IllegalArgumentException("mail is empty.");
		}
		if(!DateUtil.isDateStr(startDay, DateUtil.DATE_PTTERN_YYYYMMDD)){
			throw new IllegalArgumentException("startDay is invalid.");
		}
		if(!DateUtil.isDateStr(endDay, DateUtil.DATE_PTTERN_YYYYMMDD)){
			throw new IllegalArgumentException("endDay is invalid.");
		}
		
		BasalBodyTemperatureModelMeta bbtMeta = BasalBodyTemperatureModelMeta.get();
		
		return Datastore.query(BasalBodyTemperatureModel.class).filter(
				bbtMeta.mail.equal(mail),
				bbtMeta.measureDay.lessThanOrEqual(endDay),
				bbtMeta.measureDay.greaterThanOrEqual(startDay)).asList();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public BasalBodyTemperatureModel insertBasalBodyTemperatureModel(
			BasalBodyTemperatureModel bbt) {
		if(bbt == null){
			throw new IllegalArgumentException("bbt is empty.");
		}
		if(bbt.getKey() != null){
			throw new IllegalArgumentException("bbt hased be instered.");
		}
		Date now = new Date();
		bbt.setInsertDate(now);
		bbt.setUpdateDate(now);
		return dmLoveappleModel(bbt);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public BasalBodyTemperatureModel updateBasalBodyTemperatureModel(
			BasalBodyTemperatureModel bbt) {
		if(bbt == null || bbt.getKey() == null){
			throw new IllegalArgumentException("bbt is empty.");
		}
		
		Date now = new Date();
		bbt.setUpdateDate(now);
		return dmLoveappleModel(bbt);
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public double getAverageBbtMeasureTime(String mail, int scope) {
		if(StringUtils.isEmpty(mail)){
			throw new IllegalArgumentException("mail is empty.");
		}
		Calendar lastPoint = Calendar.getInstance();
		lastPoint.set(Calendar.DATE, -1);
		
		Calendar startPoint = Calendar.getInstance();
		startPoint.set(Calendar.DATE, -(scope+1));
		
		List<BasalBodyTemperatureModel> bbtList =
				findBasalBodyTemperatureByUser(
						mail,
						DateUtil.toDateString(startPoint.getTime(), DateUtil.DATE_PTTERN_YYYYMMDD), 
						DateUtil.toDateString(lastPoint.getTime(), DateUtil.DATE_PTTERN_YYYYMMDD));
		double totalTime = 0;
		// 平均値計算
		Calendar temp = Calendar.getInstance();
		for (BasalBodyTemperatureModel basalBodyTemperatureModel : bbtList) {
			temp.setTime(basalBodyTemperatureModel.getTimeStamp());
			totalTime += (temp.get(Calendar.MINUTE)/60 + temp.get(Calendar.HOUR));
		}
		
		return totalTime/bbtList.size();
	}

}
