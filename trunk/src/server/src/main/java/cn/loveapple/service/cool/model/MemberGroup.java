package cn.loveapple.service.cool.model;

import static cn.loveapple.service.cool.model.LoveappleModel.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author hao_shunri
 * @since 2011/02/21
 * @version $Revision$
 */
@SuppressWarnings("serial")
@Model(kind=MEMBER_GROUP_MODEL)
public class MemberGroup implements LoveappleModel {

	/**
	 * キー
	 */
	@Attribute(primaryKey=true)
	private Key key;
	
	/**
	 * グループ名
	 */
	@Attribute(unindexed=false)
	private String name;
	
	/**
	 * グループパーミッション
	 */
	@Attribute(unindexed=false)
	private Integer permission;

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
	 * グループ名を取得します。
	 * @return グループ名
	 */
	public String getName() {
	    return name;
	}

	/**
	 * グループ名を設定します。
	 * @param name グループ名
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * グループパーミッションを取得します。
	 * @return グループパーミッション
	 */
	public Integer getPermission() {
	    return permission;
	}

	/**
	 * グループパーミッションを設定します。
	 * @param permission グループパーミッション
	 */
	public void setPermission(Integer permission) {
	    this.permission = permission;
	}
	
	
}
