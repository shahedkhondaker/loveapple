package cn.loveapple.service.controller.member.form;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 会員承認用のフォーム
 * 
 * @author hao_shunri
 * @since 2011/03/02
 * @version $Revision$
 */
public class MemberCertificationForm {
	/**
	 * 会員ID
	 */
	@NotEmpty
	@Digits(fraction = 0, integer = 100)
	private String id;
	
	/**
	 * 認証コード
	 */
	@NotEmpty
	private String certificationCode;

	/**
	 * 会員IDを取得します。
	 * @return 会員ID
	 */
	public String getId() {
	    return id;
	}

	/**
	 * 会員IDを設定します。
	 * @param id 会員ID
	 */
	public void setId(String id) {
	    this.id = id;
	}

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
	}
}
