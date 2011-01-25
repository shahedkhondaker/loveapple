package cn.loveapple.service.cool.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;

/**
 * Slim3用データモデル(coolクラス)
 * 
 * @author hao_shunri
 *
 */
@Model
public class SampleModel {

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;
	/**
	 * ユーザ名
	 */
	@Attribute(unindexed=false)
	private String name;
	
	/**
	 * 経度
	 */
	@Attribute(unindexed=false)
	private GeoPt location;
	

	/**
	 * 誤差
	 */
	@Attribute(unindexed=true)
	private Double accuracy;
	
	/**
	 * 詳細
	 */
	@Attribute(lob=true, unindexed=true)
	private String detail;
	
	/**
	 * 登録日時
	 */
	@Attribute(unindexed=true)
	private Date insertDate;
	
	/**
	 * 更新日時
	 */
	@Attribute(unindexed=true)
	private Date updateDate;

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public GeoPt getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(GeoPt location) {
		this.location = location;
	}

	/**
	 * @return the accuracy
	 */
	public Double getAccuracy() {
		return accuracy;
	}

	/**
	 * @param accuracy the accuracy to set
	 */
	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the insertDate
	 */
	public Date getInsertDate() {
		return insertDate;
	}

	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
