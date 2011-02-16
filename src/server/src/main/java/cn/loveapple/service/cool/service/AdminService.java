package cn.loveapple.service.cool.service;

import java.util.List;

import com.google.appengine.api.datastore.Key;



/**
 * 管理操作を行うサービス
 * 
 * @author hao_shunri
 *
 */
public interface AdminService {
	/**
	 * 最大削除するセッション数
	 */
	public static final int DELETE_SESSION_LIMIT = 375;
	/**
	 * 
	 * ミリ秒単位で、時間が経ったセッションをDBから削除
	 * 
	 * @param time クリアするセッション情報の時間
	 * @param limit 最大削除するデータ数
	 * @return 削除したキーの一覧を返す。
	 */
	public List<Key> sessionCleanup(long time, int limit);
	
	/**
	 * @see #sessionCleanup(long, int)
	 * @see #DELETE_SESSION_LIMIT
	 * @param time
	 * @return
	 */
	public List<Key> sessionCleanup(long time);
}
