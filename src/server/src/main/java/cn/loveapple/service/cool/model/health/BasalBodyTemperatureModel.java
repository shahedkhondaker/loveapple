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
package cn.loveapple.service.cool.model.health;
import static cn.loveapple.service.cool.model.LoveappleModel.*;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import cn.loveapple.service.cool.model.LoveappleModel;
import cn.loveapple.service.util.DateUtil;

import com.google.appengine.api.datastore.Key;

/**
 * loveapple健康、基礎体温モデル
 * 
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Model(kind=BASAL_BODY_TEMPERATURE_MODEL)
@SuppressWarnings("serial")
public class BasalBodyTemperatureModel implements LoveappleModel{
	/**
	 * おりもの
	 * 
	 */
	public static enum Leukorrhea {LITTLE, USUALLY, MUCH};
	
	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;
	
	/**
	 * 計測日<br>
	 * {@linkplain DateUtil#DATE_PTTERN_YYYYMMDD YYYYMMDD}の日付文字列
	 */
	@Attribute(unindexed=false)
	private String measureDay;
	
	/**
	 * ユーザメール
	 */
	@Attribute(unindexed=false)
	private String mail;
	
	/**
	 * ユーザ名
	 */
	@Attribute
	private String name;
	
	/**
	 * 計測日時
	 */
	@Attribute
	private Date timeStamp;
	
	/**
	 * 性交フラグ
	 */
	@Attribute
	private boolean isCoitus;
	
	/**
	 * 生理期間フラグ
	 */
	@Attribute
	private boolean isMenstruation;
	
	/**
	 * 生理痛フラグ
	 */
	@Attribute
	private boolean isDysmenorrhea;
	
	/**
	 * おりもの
	 */
	@Attribute
	private Leukorrhea leukorrhea;
	
	/**
	 * 温度(°C)
	 */
	@Attribute
	private Double temperature;

	/**
	 * 登録日時
	 */
	@Attribute
	private Date insertDate;
		
	/**
	 * 更新日時
	 */
	@Attribute
	private Date updateDate;
	/**
	 * キーを取得します。
	 * @return キー
	 */
	public Key getKey() {
	    return key;
	}

	/**
	 * キーを設定します。
	 * @param key キー
	 */
	public void setKey(Key key) {
	    this.key = key;
	}

	/**
	 * 計測日<br>を取得します。
	 * @return 計測日<br>
	 */
	public String getMeasureDay() {
	    return measureDay;
	}

	/**
	 * 計測日<br>を設定します。
	 * @param measureDay 計測日<br>
	 */
	public void setMeasureDay(String measureDay) {
	    this.measureDay = measureDay;
	}

	/**
	 * ユーザメールを取得します。
	 * @return ユーザメール
	 */
	public String getMail() {
	    return mail;
	}

	/**
	 * ユーザメールを設定します。
	 * @param mail ユーザメール
	 */
	public void setMail(String mail) {
	    this.mail = mail;
	}

	/**
	 * ユーザ名を取得します。
	 * @return ユーザ名
	 */
	public String getName() {
	    return name;
	}

	/**
	 * ユーザ名を設定します。
	 * @param name ユーザ名
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * 計測日時を取得します。
	 * @return 計測日時
	 */
	public Date getTimeStamp() {
	    return timeStamp;
	}

	/**
	 * 計測日時を設定します。
	 * @param timeStamp 計測日時
	 */
	public void setTimeStamp(Date timeStamp) {
	    this.timeStamp = timeStamp;
	}

	/**
	 * 性交フラグを取得します。
	 * @return 性交フラグ
	 */
	public boolean isCoitus() {
	    return isCoitus;
	}

	/**
	 * 性交フラグを設定します。
	 * @param isCoitus 性交フラグ
	 */
	public void setCoitus(boolean isCoitus) {
	    this.isCoitus = isCoitus;
	}

	/**
	 * 生理期間フラグを取得します。
	 * @return 生理期間フラグ
	 */
	public boolean isMenstruation() {
	    return isMenstruation;
	}

	/**
	 * 生理期間フラグを設定します。
	 * @param isMenstruation 生理期間フラグ
	 */
	public void setMenstruation(boolean isMenstruation) {
	    this.isMenstruation = isMenstruation;
	}

	/**
	 * 生理痛フラグを取得します。
	 * @return 生理痛フラグ
	 */
	public boolean isDysmenorrhea() {
	    return isDysmenorrhea;
	}

	/**
	 * 生理痛フラグを設定します。
	 * @param isDysmenorrhea 生理痛フラグ
	 */
	public void setDysmenorrhea(boolean isDysmenorrhea) {
	    this.isDysmenorrhea = isDysmenorrhea;
	}

	/**
	 * おりものを取得します。
	 * @return おりもの
	 */
	public Leukorrhea getLeukorrhea() {
	    return leukorrhea;
	}

	/**
	 * おりものを設定します。
	 * @param leukorrhea おりもの
	 */
	public void setLeukorrhea(Leukorrhea leukorrhea) {
	    this.leukorrhea = leukorrhea;
	}

	/**
	 * 温度(°C)を取得します。
	 * @return 温度(°C)
	 */
	public Double getTemperature() {
	    return temperature;
	}

	/**
	 * 温度(°C)を設定します。
	 * @param temperature 温度(°C)
	 */
	public void setTemperature(Double temperature) {
	    this.temperature = temperature;
	}

	/**
	 * 登録日時を取得します。
	 * @return 登録日時
	 */
	public Date getInsertDate() {
	    return insertDate;
	}

	/**
	 * 登録日時を設定します。
	 * @param insertDate 登録日時
	 */
	public void setInsertDate(Date insertDate) {
	    this.insertDate = insertDate;
	}

	/**
	 * 更新日時を取得します。
	 * @return 更新日時
	 */
	public Date getUpdateDate() {
	    return updateDate;
	}

	/**
	 * 更新日時を設定します。
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(Date updateDate) {
	    this.updateDate = updateDate;
	}

}
