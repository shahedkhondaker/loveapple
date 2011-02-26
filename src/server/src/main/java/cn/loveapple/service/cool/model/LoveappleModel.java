package cn.loveapple.service.cool.model;

import java.io.Serializable;

/**
 * モデル名の定数群
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
public interface LoveappleModel extends Serializable {
	/**
	 * {@linkplain MetaInfoModel}のモデル名
	 */
	public static final String META_INFO_MODEL = "MetaInfo";
	
	/**
	 * {@linkplain LoveappleMemberModel}のモデル名
	 */
	public static final String LOVEAPPLE_MEMBER_MODEL = "LoveappleMember";
	
	/**
	 * {@linkplain MEMBER_GROUP_MODEL}のモデル名
	 */
	public static final String MEMBER_GROUP_MODEL = "MEMBER_GROUP_MODEL";
	
	/**
	 * {@linkplain FixedMailModel}のモデル名
	 */
	public static final String FIXED_MAIL_MODEL = "FixedMailModel";
}
