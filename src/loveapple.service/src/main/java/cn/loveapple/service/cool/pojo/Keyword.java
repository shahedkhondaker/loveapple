/**
 * 
 */
package cn.loveapple.service.cool.pojo;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Category;
import com.google.appengine.api.datastore.Key;

/**
 *  {@linkplain Contents コンテンツ}のキーワードPOJO
 *  
 * @author $author:$
 * @version $Revision:$
 * @date $Date:$
 * @id $Id:$
 *
 */
@Model
public class Keyword {
	/**
	 * key
	 */
	@Attribute(primaryKey=true)
	private Key key;
	
	/**
	 * キーワード
	 */
	@Attribute(unindexed=false)
	private Category keyword;

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
	 * キーワードを取得します。
	 * @return キーワード
	 */
	public Category getKeyword() {
	    return keyword;
	}

	/**
	 * キーワードを設定します。
	 * @param keyword キーワード
	 */
	public void setKeyword(Category keyword) {
	    this.keyword = keyword;
	}
}
