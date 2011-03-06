package cn.loveapple.service.controller;

import cn.loveapple.service.cool.model.LoveappleMemberModel;

/**
 * セッション情報にて利用するラベルを定義する。
 * 
 * @author hao_shunri
 * @since 2011/02/16
 * @version $Revision$
 */
public interface SessionLabel {
	/**
	 * {@linkplain LoveappleMemberModel 会員情報}を格納するキー
	 */
	public static final String LOVEAPPLE_MEMBER = "loveappleMember";
	
	/**
	 * 「/」始まりの遷移先のURL
	 */
	public static final String REFERER_INNER_URL = "refererUrl";
	
	/**
	 * {@linkplain LoveappleMemberModel 会員情報}登録、更新処理のために、一時的保存するキー
	 */
	public static final String LOVEAPPLE_MEMBER_TMP = "loveappleMemberTmp";
	
	/**
	 * セッションに保存する入力フォーム
	 */
	public static final String FORM = "inputForm";
}
