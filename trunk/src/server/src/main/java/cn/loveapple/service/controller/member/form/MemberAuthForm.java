package cn.loveapple.service.controller.member.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ログインフォーム
 * 
 * @author hao_shunri
 *
 */
public class MemberAuthForm {
	
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
	@Size(min=32, max=32)
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
}