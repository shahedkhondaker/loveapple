/**
 * 
 */
package cn.loveapple.service.cool.pojo;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import cn.loveapple.service.cool.pojo.type.Sex;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;


/**
 * 会員情報を管理するPOJO
 * 
 * @author $author:$
 * @version $Revision:$
 * @date $Date:$
 * @id $Id:$
 *
 */
@Model
public class LoveappleMember {
	/**
	 * key
	 */
	@Attribute(primaryKey=true)
	private Key key;
	/**
	 * 会員を特定するメールアドレス
	 * 
	 * @see User#getEmail()
	 */
	@Attribute(unindexed=false)
	private Email mail;
	/**
	 * ニックネーム
	 */
	@Attribute(unindexed=false)
	private String nickName;
	/**
	 * パスワード(MD5)
	 */
	@Attribute(unindexed=true)
	private String password;
	/**
	 * 氏名(姓)
	 */
	@Attribute(unindexed=true)
	private String familyName;
	/**
	 * 氏名(名)
	 */
	@Attribute(unindexed=true)
	private String firstName;
	/**
	 * 性別
	 */
	@Attribute(unindexed=true)
	private Sex sex;
	
	/**
	 * keyを取得します。
	 * @return key
	 */
	public Key getKey() {
	    return key;
	}
	/**
	 * keyを設定します。
	 * @param key key
	 */
	public void setKey(Key key) {
	    this.key = key;
	}
	/**
	 * mailを取得します。
	 * @return mail
	 */
	public Email getMail() {
	    return mail;
	}
	/**
	 * mailを設定します。
	 * @param mail mail
	 */
	public void setMail(Email mail) {
	    this.mail = mail;
	}
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
	public Sex getSex() {
	    return sex;
	}
	/**
	 * Sexを設定します。
	 * @param sex Sex
	 */
	public void setSex(Sex sex) {
	    this.sex = sex;
	}
}
