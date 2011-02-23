package cn.loveapple.service.cool.service;

import cn.loveapple.service.cool.model.LoveappleMemberModel;

import com.google.appengine.api.datastore.Key;



/**
 * 認証、会員情報更新/削除など、直接{@linkplain LoveappleMemberModel Loveapple会員情報}に対して
 * 操作を行うロジック処理を行うサービス。
 * 
 * @author hao_shunri
 *
 */
public interface MemberCoreService {
	
	/**
	 * デフォルト会員の権限値
	 */
	public static final int DEFAULT_MEMBER_PERMISSION = 0;
	/**
	 * Loveapple会員の認証処理を行う。
	 * 
	 * @see #findByLoginId(String) 
	 * @param loginId ログインID
	 * @param password MD5で暗号化したパスワード
	 * @return 認証が失敗した場合、<code>null</code>を返す。
	 */
	public LoveappleMemberModel authenticateLoveappleMember(String loginId, String password);
	
	/**
	 * 新たな会員情報の登録、又は更新を行う。
	 * 
	 * @param member 登録、又は更新しようとする会員情報
	 * @return 登録、又は更新した会員情報を戻す。
	 */
	public LoveappleMemberModel updateLoveappleMember(LoveappleMemberModel member);
	
	/**
	 * キーをもとにデータを検索する
	 * @param keyId
	 * @return
	 */
	public LoveappleMemberModel queryByKey(Long keyId);
	
	/**
	 * ログインIDをもとに、{@linkplain LoveappleMemberModel Loveapple会員情報}を取得する。
	 * 
	 * @param loginId ログインID
	 * @return 見つからない場合、<code>null</code>を返す。
	 */
	public LoveappleMemberModel findByLoginId(String loginId);
	/**
	 * 
	 * キーをもとにデータを検索する
	 * 
	 * @param key
	 * @return
	 */
	public LoveappleMemberModel queryByKey(Key key);
}
