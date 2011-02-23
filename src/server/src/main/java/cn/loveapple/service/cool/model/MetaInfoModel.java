package cn.loveapple.service.cool.model;

import static cn.loveapple.service.cool.model.LoveappleModel.*;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * 各モデルのメタ情報
 * 
 * @author hao_shunri
 * @since 2011/02/23
 * @version $Revision$
 */
@SuppressWarnings("serial")
@Model(kind=META_INFO_MODEL)
public class MetaInfoModel implements LoveappleModel {

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;
	
	/**
	 * モデル名
	 */
	@Attribute(unindexed=false)
	@NotEmpty
	private String kindName;
	
	/**
	 * 件数
	 */
	@Attribute
	@NotNull
	private Integer count;
	
	/**
	 * 直近の登録/更新処理の時刻
	 */
	@Attribute
	@NotNull
	private Date lastUpdateDate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
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
	 * モデル名を取得します。
	 * @return モデル名
	 */
	public String getKindName() {
	    return kindName;
	}

	/**
	 * モデル名を設定します。
	 * @param kindName モデル名
	 */
	public void setKindName(String kindName) {
	    this.kindName = kindName;
	}

	/**
	 * 件数を取得します。
	 * @return 件数
	 */
	public Integer getCount() {
	    return count;
	}

	/**
	 * 件数を設定します。
	 * @param count 件数
	 */
	public void setCount(Integer count) {
	    this.count = count;
	}

	/**
	 * 直近の登録/更新処理の時刻を取得します。
	 * @return 直近の登録/更新処理の時刻
	 */
	public Date getLastUpdateDate() {
	    return lastUpdateDate;
	}

	/**
	 * 直近の登録/更新処理の時刻を設定します。
	 * @param lastUpdateDate 直近の登録/更新処理の時刻
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
	    this.lastUpdateDate = lastUpdateDate;
	}
}
