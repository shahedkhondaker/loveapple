package cn.loveapple.service.controller;

/**
 * プレゼンテーション層用定数
 * 
 * @author $author:$
 * @version $Revision: 51 $
 * @date $Date: 2010-10-07 14:07:43 +0900 (木, 07 10 2010) $
 * @id $Id: Constant.java 51 2010-10-07 05:07:43Z hao0323 $
 *
 */
public final class Constant {
	/**
	 * コンテンツタイプ：JSON/JavaScript
	 */
	public static final String CONTENTS_TYPE_JSON = "js";
	/**
	 * コンテンツタイプ：html
	 */
	public static final String CONTENTS_TYPE_HTML = "html";
	public static final String STATUS_OK = "OK";
	public static final String STATUS_NG = "NG";
	public static final String STATUS_CODE_SUCCESS = "00000";
	public static final String STATUS_CODE_USER_ERROR = "10000";
	public static final String STATUS_CODE_SYSTEM_ERROR = "E9999";

	/**
	 * ベースURL定義：会員認証系
	 */
	public static final String URL_CORE = "/core";
	/**
	 * ベースURL定義：{@linkplain BaseController ベースコントローラ}用
	 */
	public static final String URL_BASE = "/system";
	

	/**
	 * ベースURL定義：サンプルコントローラ用
	 */
	public static final String URL_SAMPLE = "/sample";
}
