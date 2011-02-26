package cn.loveapple.service.cool.model;

import static cn.loveapple.service.cool.model.LoveappleModel.*;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;

/**
 * Loveapple会員モデル
 * 
 * @author hao_shunri
 *
 */
@SuppressWarnings("serial")
@Model(kind=LOVEAPPLE_MEMBER_MODEL)
public class LoveappleMemberModel implements LoveappleModel{
		
	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;

	/**
	 * 会員状態
	 */
	@Attribute()
	private Status status;
		
	/**
	 * 連絡用メールアドレス
	 */
	@Email
	@Attribute(unindexed=false)
	private String mail;
	
	/**
	 * 表示用のユーザ名
	 */
	@NotEmpty
	@Attribute(unindexed=true)
	private String name;
	
	/**
	 * QQのID
	 */
	@Attribute(unindexed=true)
	private Long qqId;
	
	/**
	 * QQ認証キー
	 */
	@Attribute(unindexed=true)
	private String qqAuthKey;
	
	/**
	 * パスワード
	 */
	@Size(min = 32, max = 32)
	@Attribute(unindexed=true)
	private String password;
	
	/**
	 * 直近更新した座標
	 */
	@Attribute(unindexed=false)
	private GeoPt lastLocation;
	

	/**
	 * 直近更新した誤差
	 */
	@Attribute(unindexed=true)
	private Double lastAccuracy;
		
	/**
	 * 登録日時
	 */
	@Attribute(unindexed=true)
	private Date insertDate;
	
	/**
	 * 直近ログインした日時
	 */
	@Attribute(unindexed=true)
	private Date lastLoginDate;
	
	/**
	 * 更新日時
	 */
	@Attribute(unindexed=true)
	private Date updateDate;
	
	/**
	 * 権限
	 */
	@Attribute(unindexed=false)
	private Integer permission;
	
	/**
	 * 属しているグループ名
	 */
	@Attribute(unindexed=false,lob=false )
	private List<String> groupName;
	
	/**
	 * 認証コード
	 */
	@Attribute
	private String certificationCode;
	

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
	 * 会員状態を取得します。
	 * @return 会員状態
	 */
	public Status getStatus() {
	    return status;
	}

	/**
	 * 会員状態を設定します。
	 * @param status 会員状態
	 */
	public void setStatus(Status status) {
	    this.status = status;
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
	 * 表示用のユーザ名を取得します。
	 * @return 表示用のユーザ名
	 */
	public String getName() {
	    return name;
	}

	/**
	 * 表示用のユーザ名を設定します。
	 * @param name 表示用のユーザ名
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * QQのIDを取得します。
	 * @return QQのID
	 */
	public Long getQqId() {
	    return qqId;
	}

	/**
	 * QQのIDを設定します。
	 * @param qqId QQのID
	 */
	public void setQqId(Long qqId) {
	    this.qqId = qqId;
	}

	/**
	 * QQ認証キーを取得します。
	 * @return QQ認証キー
	 */
	public String getQqAuthKey() {
	    return qqAuthKey;
	}

	/**
	 * QQ認証キーを設定します。
	 * @param qqAuthKey QQ認証キー
	 */
	public void setQqAuthKey(String qqAuthKey) {
	    this.qqAuthKey = qqAuthKey;
	}

	/**
	 * パスワードを取得します。
	 * @return パスワード
	 */
	public String getPassword() {
	    return password;
	}

	/**
	 * パスワードを設定します。
	 * @param password パスワード
	 */
	public void setPassword(String password) {
	    this.password = password;
	}

	/**
	 * 直近更新した座標を取得します。
	 * @return 直近更新した座標
	 */
	public GeoPt getLastLocation() {
	    return lastLocation;
	}

	/**
	 * 直近更新した座標を設定します。
	 * @param lastLocation 直近更新した座標
	 */
	public void setLastLocation(GeoPt lastLocation) {
	    this.lastLocation = lastLocation;
	}

	/**
	 * 直近更新した誤差を取得します。
	 * @return 直近更新した誤差
	 */
	public Double getLastAccuracy() {
	    return lastAccuracy;
	}

	/**
	 * 直近更新した誤差を設定します。
	 * @param lastAccuracy 直近更新した誤差
	 */
	public void setLastAccuracy(Double lastAccuracy) {
	    this.lastAccuracy = lastAccuracy;
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
	 * 直近ログインした日時を取得します。
	 * @return 直近ログインした日時
	 */
	public Date getLastLoginDate() {
	    return lastLoginDate;
	}

	/**
	 * 直近ログインした日時を設定します。
	 * @param lastLoginDate 直近ログインした日時
	 */
	public void setLastLoginDate(Date lastLoginDate) {
	    this.lastLoginDate = lastLoginDate;
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

	/**
	 * 権限を取得します。
	 * @return 権限
	 */
	public Integer getPermission() {
	    return permission;
	}

	/**
	 * 権限を設定します。
	 * @param permission 権限
	 */
	public void setPermission(Integer permission) {
	    this.permission = permission;
	}

	/**
	 * 属しているグループ名を取得します。
	 * @return 属しているグループ名
	 */
	public List<String> getGroupName() {
	    return groupName;
	}

	/**
	 * 属しているグループ名を設定します。
	 * @param groupName 属しているグループ名
	 */
	public void setGroupName(List<String> groupName) {
	    this.groupName = groupName;
	}
	
	/**
	 * {@linkplain #DELETE 削除}、{@linkplain #CONFIRMATION 確認中}、
	 * {@linkplain #NORMAL 正常}の状態を管理する。
	 * 
	 * @author hao_shunri
	 * @since 2011/02/22
	 * @version $Revision$
	 */
	public static enum Status{
		/**
		 * 削除
		 */
		DELETE, 
		/**
		 * 確認中
		 */
		CONFIRMATION,
		/**
		 * 正常
		 */
		NORMAL}

	/**
	 * 認証コードを取得します。
	 * @return 認証コード
	 */
	public String getCertificationCode() {
	    return certificationCode;
	}

	/**
	 * 認証コードを設定します。
	 * @param certificationCode 認証コード
	 */
	public void setCertificationCode(String certificationCode) {
	    this.certificationCode = certificationCode;
	};
}
