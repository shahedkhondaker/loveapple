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
package cn.loveapple.service.cool.service;

import java.util.Date;
import java.util.List;

import cn.loveapple.service.cool.model.health.BasalBodyTemperatureModel;
import cn.loveapple.service.util.DateUtil;

/**
 * loveapple健康サービス
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public interface HealthService extends BaseService {

	/**
	 * 基礎体温を検索する。
	 * 
	 * @param mail 対象ユーザのメールアドレス
	 * @param startDay　計測開始日。フォーマット：{@linkplain DateUtil#DATE_PTTERN_YYYYMMDD}
	 * @param endDay 計測終了日。フォーマット：{@linkplain DateUtil#DATE_PTTERN_YYYYMMDD}
	 * @return 基礎体温の検索結果
	 */
	public List<BasalBodyTemperatureModel> findBasalBodyTemperatureByUser(String mail, String startDay, String endDay);

	/**
	 * ユーザ、及び計測日を基準に、基礎体温を更新/登録する。
	 * 
	 * @param bbt 登録/更新する基礎体温
	 * @return 登録/更新した基礎体温を戻す
	 */
	public BasalBodyTemperatureModel updateBasalBodyTemperatureModel(BasalBodyTemperatureModel bbt);

	/**
	 * ある期間内、平均基礎対応計測日時を取得する。
	 * 
	 * @param mail 対象ユーザのMail
	 * @param scope　前日からの日数。<code>0</code>を指定した場合、前日の計測日時を戻す
	 * @return {@linkplain BasalBodyTemperatureModel#getTimeStamp()}の平均値
	 */
	public Date getAverageBbtMeasureDate(String mail, int scope);
}
