package cn.loveapple.service.controller.sample.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * サンプルフォーム
 * 
 * @author hao_shunri
 *
 */
public class SampleForm {
	
	/**
	 * ユーザ名
	 */
	@NotNull
	@Size(min=1, max=25)
	private String name;
	
	/**
	 * 経度
	 */
	@NotNull
	@NumberFormat(style=Style.NUMBER)
	private Float latitude;
	
	/**
	 * 緯度
	 */
	@NotNull
	@NumberFormat(style=Style.NUMBER)
	private Float longitude;

	/**
	 * 誤差
	 */
	@NotNull
	@NumberFormat(style=Style.NUMBER)
	private Double accuracy;
	
	/**
	 * 詳細
	 */
	@Size(min=0, max=1000)
	private String detail;

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
	 * @return the latitude
	 */
	public Float getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Float getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
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
}