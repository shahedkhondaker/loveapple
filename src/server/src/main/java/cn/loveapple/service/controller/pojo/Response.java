package cn.loveapple.service.controller.pojo;

/**
 * レスポンスの基底クラス
 * 
 * @author hao_shunri
 *
 */
public class Response {
	/**
	 * ステータスコード
	 */
	private String statusCode;
	/**
	 * ステータス
	 */
	private String status;
	/**
	 * レスポンスメッセージ
	 */
	private String msg;
	/**
	 * ステータスコードを取得します。
	 * @return ステータスコード
	 */
	public String getStatusCode() {
	    return statusCode;
	}
	/**
	 * ステータスコードを設定します。
	 * @param statusCode ステータスコード
	 */
	public void setStatusCode(String statusCode) {
	    this.statusCode = statusCode;
	}
	/**
	 * ステータスを取得します。
	 * @return ステータス
	 */
	public String getStatus() {
	    return status;
	}
	/**
	 * ステータスを設定します。
	 * @param status ステータス
	 */
	public void setStatus(String status) {
	    this.status = status;
	}
	/**
	 * レスポンスメッセージを取得します。
	 * @return レスポンスメッセージ
	 */
	public String getMsg() {
	    return msg;
	}
	/**
	 * レスポンスメッセージを設定します。
	 * @param msg レスポンスメッセージ
	 */
	public void setMsg(String msg) {
	    this.msg = msg;
	}
}
