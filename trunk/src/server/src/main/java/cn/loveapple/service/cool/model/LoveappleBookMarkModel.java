package cn.loveapple.service.cool.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
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
public class LoveappleBookMarkModel implements LoveappleModel{

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
	 * ブックマークのタイトル
	 */
	@NotEmpty
	@Attribute(unindexed=false)
	private String title;
	
	/**
	 * URL
	 */
	@NotEmpty
	@Attribute(unindexed=false)
	private String url;
	
	/**
	 * ブックマークタグ
	 */
	@Attribute(lob=true)
	private String[] bookMarkTags;
	
	/**
	 * 詳細
	 */
	@Attribute(lob=true, unindexed=true)
	private String detail;
	
	/**
	 * サムネイルのバイナリ
	 */
	@Attribute(lob=true,unindexed=true)
	private byte[] thumbnail;
	
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
	 * ブックマークのタイトルを取得します。
	 * @return ブックマークのタイトル
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * ブックマークのタイトルを設定します。
	 * @param title ブックマークのタイトル
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * URLを取得します。
	 * @return URL
	 */
	public String getUrl() {
	    return url;
	}

	/**
	 * URLを設定します。
	 * @param url URL
	 */
	public void setUrl(String url) {
	    this.url = url;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * ブックマークタグを取得します。
	 * @return ブックマークタグ
	 */
	public String[] getBookMarkTags() {
	    return bookMarkTags;
	}

	/**
	 * ブックマークタグを設定します。
	 * @param bookMarkTags ブックマークタグ
	 */
	public void setBookMarkTags(String[] bookMarkTags) {
	    this.bookMarkTags = bookMarkTags;
	}

	/**
	 * 詳細を取得します。
	 * @return 詳細
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 詳細を設定します。
	 * @param detail 詳細
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * サムネイルのバイナリを取得します。
	 * @return サムネイルのバイナリ
	 */
	public byte[] getThumbnail() {
	    return thumbnail;
	}

	/**
	 * サムネイルのバイナリを設定します。
	 * @param thumbnail サムネイルのバイナリ
	 */
	public void setThumbnail(byte[] thumbnail) {
	    this.thumbnail = thumbnail;
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
