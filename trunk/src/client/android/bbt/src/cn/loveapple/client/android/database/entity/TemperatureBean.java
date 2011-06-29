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
package cn.loveapple.client.android.database.entity;
/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class TemperatureBean {
	/**
	 * 日付
	 */
	private String date;
	/**
	 * タイムスタンプ
	 */
	private String timestamp;
	/**
	 * 体温
	 */
	private Double temperature;
	/**
	 * セックス
	 */
	private String coitusFlg;
	/**
	 * 生理フラグ
	 */
	private String menstruationFlg;
	/**
	 * 生理痛フラグ
	 */
	private String dysmenorrheaFlg;
	/**
	 * 下り物
	 */
	private String leukorrhea;
	/**
	 * 生理量
	 */
	private String dysmenorrhea_level;
	/**
	 * 周期日数
	 */
	private Integer menstruation_cycle;
	/**
	 * 日付を取得します。
	 * @return 日付
	 */
	public String getDate() {
	    return date;
	}
	/**
	 * 日付を設定します。
	 * @param date 日付
	 */
	public void setDate(String date) {
	    this.date = date;
	}
	/**
	 * タイムスタンプを取得します。
	 * @return タイムスタンプ
	 */
	public String getTimestamp() {
	    return timestamp;
	}
	/**
	 * タイムスタンプを設定します。
	 * @param timestamp タイムスタンプ
	 */
	public void setTimestamp(String timestamp) {
	    this.timestamp = timestamp;
	}
	/**
	 * 体温を取得します。
	 * @return 体温
	 */
	public Double getTemperature() {
	    return temperature;
	}
	/**
	 * 体温を設定します。
	 * @param temperature 体温
	 */
	public void setTemperature(Double temperature) {
	    this.temperature = temperature;
	}
	/**
	 * セックスを取得します。
	 * @return セックス
	 */
	public String getCoitusFlg() {
	    return coitusFlg;
	}
	/**
	 * セックスを設定します。
	 * @param coitusFlg セックス
	 */
	public void setCoitusFlg(String coitusFlg) {
	    this.coitusFlg = coitusFlg;
	}
	/**
	 * 生理フラグを取得します。
	 * @return 生理フラグ
	 */
	public String getMenstruationFlg() {
	    return menstruationFlg;
	}
	/**
	 * 生理フラグを設定します。
	 * @param menstruationFlg 生理フラグ
	 */
	public void setMenstruationFlg(String menstruationFlg) {
	    this.menstruationFlg = menstruationFlg;
	}
	/**
	 * 生理痛フラグを取得します。
	 * @return 生理痛フラグ
	 */
	public String getDysmenorrheaFlg() {
	    return dysmenorrheaFlg;
	}
	/**
	 * 生理痛フラグを設定します。
	 * @param dysmenorrheaFlg 生理痛フラグ
	 */
	public void setDysmenorrheaFlg(String dysmenorrheaFlg) {
	    this.dysmenorrheaFlg = dysmenorrheaFlg;
	}
	/**
	 * 下り物を取得します。
	 * @return 下り物
	 */
	public String getLeukorrhea() {
	    return leukorrhea;
	}
	/**
	 * 下り物を設定します。
	 * @param leukorrhea 下り物
	 */
	public void setLeukorrhea(String leukorrhea) {
	    this.leukorrhea = leukorrhea;
	}
	/**
	 * 生理量を取得します。
	 * @return 生理量
	 */
	public String getDysmenorrhea_level() {
	    return dysmenorrhea_level;
	}
	/**
	 * 生理量を設定します。
	 * @param dysmenorrhea_level 生理量
	 */
	public void setDysmenorrhea_level(String dysmenorrhea_level) {
	    this.dysmenorrhea_level = dysmenorrhea_level;
	}
	/**
	 * 周期日数を取得します。
	 * @return 周期日数
	 */
	public Integer getMenstruation_cycle() {
	    return menstruation_cycle;
	}
	/**
	 * 周期日数を設定します。
	 * @param menstruation_cycle 周期日数
	 */
	public void setMenstruation_cycle(Integer menstruation_cycle) {
	    this.menstruation_cycle = menstruation_cycle;
	}
}
