package cn.loveapple.service.cool.model;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * Slim3用データモデル(coolクラス)
 * 
 * @author hao_shunri
 *
 */
@SuppressWarnings("serial")
@Model
public class LoveappleBookMarkTagModel implements LoveappleModel{

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;

	/**
	 * 連絡用メールアドレス
	 */
	@NotEmpty
	@Email
	@Attribute(unindexed=false)
	private String mail;
	
	/**
	 * ユーザ名
	 */
	@Attribute(unindexed=true)
	private String name;
	
	/**
	 * タグ
	 */
	@NotEmpty
	@Attribute(unindexed=false)
	private String tag;
	
	/**
	 * URL
	 */
	@Attribute()
	private Long entryCount;
	
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
	 * 連絡用メールアドレスを取得します。
	 * @return 連絡用メールアドレス
	 */
	public String getMail() {
	    return mail;
	}

	/**
	 * 連絡用メールアドレスを設定します。
	 * @param mail 連絡用メールアドレス
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
	 * タグを取得します。
	 * @return タグ
	 */
	public String getTag() {
	    return tag;
	}

	/**
	 * タグを設定します。
	 * @param tag タグ
	 */
	public void setTag(String tag) {
	    this.tag = tag;
	}

	/**
	 * URLを取得します。
	 * @return URL
	 */
	public Long getEntryCount() {
	    return entryCount;
	}

	/**
	 * URLを設定します。
	 * @param entryCount URL
	 */
	public void setEntryCount(Long entryCount) {
	    this.entryCount = entryCount;
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
