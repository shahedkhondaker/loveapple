package cn.loveapple.service.controller.member.form;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;

import com.google.appengine.api.datastore.GeoPt;

/**
 * Loveapple会員モデル
 * 
 * @author hao_shunri
 * TODO 作成中
 */
public class MemberForm extends MemberAuthForm implements Serializable{
	
	/**
	 * ログインID
	 */
	@NotNull
	@Size(min=1, max=25)
	//@Pattern(regexp="\\p{Alnum}\\-\\_")
	private String loginId;
	
	/**
	 * パスワード
	 */
	@NotNull
	@Size(min=32, max=32, message="{javax.validation.constraints.Pattern.message}")
	//@Pattern(regexp="\\p{Alnum}=")
	private String password;
	
	/**
	 * ドメイン名付きのGmailのログインID
	 */
	@Email
	private String gmailId;
	
	/**
	 * Gmailアカウントのパスワード
	 */
	private String gmailPassword;
	
	/**
	 * 表示用のユーザ名
	 */
	private String name;
	
	/**
	 * QQのID
	 */
	private String qqId;
	
	/**
	 * QQアカウントのパスワード
	 */
	private String qqIdPassword;
	
	/**
	 * QQ認証キー
	 */
	private String qqAuthKey;
	
	
	/**
	 * 直近更新した座標
	 */
	private GeoPt lastLocation;
	

	/**
	 * 直近更新した誤差
	 */
	private Double lastAccuracy;
		
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * ログインIDを取得します。
	 * @return ログインID
	 */
	public String getLoginId() {
	    return loginId;
	}

	/**
	 * ログインIDを設定します。
	 * @param loginId ログインID
	 */
	public void setLoginId(String loginId) {
	    this.loginId = loginId;
	}

	/**
	 * ドメイン名付きのGmailのログインIDを取得します。
	 * @return ドメイン名付きのGmailのログインID
	 */
	public String getGmailId() {
	    return gmailId;
	}

	/**
	 * ドメイン名付きのGmailのログインIDを設定します。
	 * @param gmailId ドメイン名付きのGmailのログインID
	 */
	public void setGmailId(String gmailId) {
	    this.gmailId = gmailId;
	}

	/**
	 * Gmailアカウントのパスワードを取得します。
	 * @return Gmailアカウントのパスワード
	 */
	public String getGmailPassword() {
	    return gmailPassword;
	}

	/**
	 * Gmailアカウントのパスワードを設定します。
	 * @param gmailPassword Gmailアカウントのパスワード
	 */
	public void setGmailPassword(String gmailPassword) {
	    this.gmailPassword = gmailPassword;
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
	public String getQqId() {
	    return qqId;
	}

	/**
	 * QQのIDを設定します。
	 * @param qqId QQのID
	 */
	public void setQqId(String qqId) {
	    this.qqId = qqId;
	}

	/**
	 * QQアカウントのパスワードを取得します。
	 * @return QQアカウントのパスワード
	 */
	public String getQqIdPassword() {
	    return qqIdPassword;
	}

	/**
	 * QQアカウントのパスワードを設定します。
	 * @param qqIdPassword QQアカウントのパスワード
	 */
	public void setQqIdPassword(String qqIdPassword) {
	    this.qqIdPassword = qqIdPassword;
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


}
