/**
 * 
 */
package cn.loveapple.service.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

/**
 * @author hcl
 *
 */
public class MemberForm {

	/**
	 * nick name
	 */
	@NotNull
	@Size(min=2, max=20)
	private String nickName;
	/**
	 * password(MD5)
	 */
	@NotNull
	@Size(max=32)
	private String password;
	/**
	 * Family name
	 */
	@Size(min=2, max=20)
	private String familyName;
	/**
	 * First name
	 */
	@Size(min=2, max=20)
	private String firstName;
	/**
	 * Sex
	 */
	@Size(min=1, max=1)
	@NumberFormat(style=Style.NUMBER)
	private String sexStr;
	
	/**
	 * nick nameを取得します。
	 * @return nick name
	 */
	public String getNickName() {
	    return nickName;
	}
	/**
	 * nick nameを設定します。
	 * @param nickName nick name
	 */
	public void setNickName(String nickName) {
	    this.nickName = nickName;
	}
	/**
	 * password(MD5)を取得します。
	 * @return password(MD5)
	 */
	public String getPassword() {
	    return password;
	}
	/**
	 * password(MD5)を設定します。
	 * @param password password(MD5)
	 */
	public void setPassword(String password) {
	    this.password = password;
	}
	/**
	 * Family nameを取得します。
	 * @return Family name
	 */
	public String getFamilyName() {
	    return familyName;
	}
	/**
	 * Family nameを設定します。
	 * @param familyName Family name
	 */
	public void setFamilyName(String familyName) {
	    this.familyName = familyName;
	}
	/**
	 * First nameを取得します。
	 * @return First name
	 */
	public String getFirstName() {
	    return firstName;
	}
	/**
	 * First nameを設定します。
	 * @param firstName First name
	 */
	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}
	/**
	 * Sexを取得します。
	 * @return Sex
	 */
	public String getSexStr() {
	    return sexStr;
	}
	/**
	 * Sexを設定します。
	 * @param sex Sex
	 */
	public void setSexStr(String sex) {
	    this.sexStr = sex;
	}
}
