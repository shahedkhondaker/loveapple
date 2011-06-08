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
package cn.loveapple.service.cool.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.loveapple.service.cool.model.health.BasalBodyTemperatureModel;
import cn.loveapple.service.cool.service.HealthService;

/**
 * loveapple健康サービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class HealthServiceImpl extends BaseServiceImpl implements HealthService {

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
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public BasalBodyTemperatureModel updateBasalBodyTemperatureModel(
			BasalBodyTemperatureModel bbt) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Date getAverageBbtMeasureDate(String mail, int scope) {
		if(StringUtils.isEmpty(mail)){
			throw new IllegalArgumentException("mail is empty.");
		}
		Calendar lastPoint = Calendar.getInstance();
		
		return null;
	}

}
