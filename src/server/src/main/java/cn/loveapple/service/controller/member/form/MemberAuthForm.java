package cn.loveapple.service.controller.member.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ログインフォーム
 * 
 * @author hao_shunri
 *
 */
@SuppressWarnings("serial")
public class MemberAuthForm implements Serializable {

	/**
	 * 連絡用メールアドレス
	 */
	@NotEmpty
	@Email
	@Size(max = 256)
	private String mail;
		
	/**
	 * パスワード
	 */
	@NotEmpty
	@Size(min=32, max=32, message="{loveappleErrors.passwordSize}")
	//@Pattern(regexp="\\p{Alnum}=")
	private String password;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
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

}