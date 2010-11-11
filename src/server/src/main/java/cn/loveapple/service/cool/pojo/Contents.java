/**
 * 
 */
package cn.loveapple.service.cool.pojo;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Key;

/**
 * コンテンツ情報を管理するPOJO
 * 
 * @author $author:$
 * @version $Revision:$
 * @date $Date:$
 * @id $Id:$
 *
 */
@Model
public class Contents {

	/**
	 * 主キー
	 */
	@Attribute(primaryKey=true)
	private Key key;
	
	/**
	 * 作者
	 */
	@Attribute(unindexed=false)
	private String creator;
		
	/**
	 * 内容
	 */
	@Attribute(unindexed=true,lob=true)
	private String body;
	
	/**
	 * キーワード
	 */
	@Attribute(unindexed=true,lob=true)
	private Category[] keyword;
	
	/**
	 * タイトル
	 */
	@Attribute(unindexed=false)
	private String title;
	
	/**
	 * アクセスキー
	 */
	@Attribute(unindexed=false)
	private String accessKey;
	
	/**
	 * ヘッド
	 */
	@Attribute(unindexed=false)
	private String head;

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
	 * 作者を取得します。
	 * @return 作者
	 */
	public String getCreator() {
	    return creator;
	}

	/**
	 * 作者を設定します。
	 * @param creator 作者
	 */
	public void setCreator(String creator) {
	    this.creator = creator;
	}

	/**
	 * 内容を取得します。
	 * @return 内容
	 */
	public String getBody() {
	    return body;
	}

	/**
	 * 内容を設定します。
	 * @param body 内容
	 */
	public void setBody(String body) {
	    this.body = body;
	}

	/**
	 * キーワードを取得します。
	 * @return キーワード
	 */
	public Category[] getKeyword() {
	    return keyword;
	}

	/**
	 * キーワードを設定します。
	 * @param keyword キーワード
	 */
	public void setKeyword(Category[] keyword) {
	    this.keyword = keyword;
	}

	/**
	 * タイトルを取得します。
	 * @return タイトル
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * タイトルを設定します。
	 * @param title タイトル
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * アクセスキーを取得します。
	 * @return アクセスキー
	 */
	public String getAccessKey() {
	    return accessKey;
	}

	/**
	 * アクセスキーを設定します。
	 * @param accessKey アクセスキー
	 */
	public void setAccessKey(String accessKey) {
	    this.accessKey = accessKey;
	}

	/**
	 * ヘッドを取得します。
	 * @return ヘッド
	 */
	public String getHead() {
	    return head;
	}

	/**
	 * ヘッドを設定します。
	 * @param head ヘッド
	 */
	public void setHead(String head) {
	    this.head = head;
	}
}
