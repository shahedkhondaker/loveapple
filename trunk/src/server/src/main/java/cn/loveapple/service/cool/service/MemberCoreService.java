/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.cn
 *
 * All copyright notices regarding loveapple and loveapple CoreLib
 * MUST remain intact in the scripts, documents and source code.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at loveapple
 *
 * @author: loveapple
 */
package cn.loveapple.service.cool.service;


import javax.mail.internet.MimeMessage;

import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.service.exception.MailException;

import com.google.appengine.api.datastore.Key;

/**
 * 認証、会員情報更新/削除など、直接{@linkplain LoveappleMemberModel Loveapple会員情報}に対して
 * 操作を行うロジック処理を行うサービス。
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
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
	 * @see #findByEmail(String) 
	 * @param loginId ログインID
	 * @param password MD5で暗号化したパスワード
	 * @return 認証が失敗した場合、<code>null</code>を返す。
	 */
	public LoveappleMemberModel authenticateLoveappleMember(String loginId, String password);
	
	/**
	 * 新たな会員情報の更新を行う。
	 * 
	 * @param member 更新しようとする会員情報
	 * @return 登録、又は更新した会員情報を戻す。
	 */
	public LoveappleMemberModel updateLoveappleMember(LoveappleMemberModel member);
	/**
	 * 新たな会員情報の登録を行う。
	 * 
	 * @param member 登録更新しようとする会員情報
	 * @return 登録、又は更新した会員情報を戻す。
	 */
	public LoveappleMemberModel insertLoveappleMember(LoveappleMemberModel member);
	
	/**
	 * キーをもとにデータを検索する
	 * @param keyId
	 * @return
	 */
	public LoveappleMemberModel queryByKey(Long keyId);
	
	/**
	 * ログインIDをもとに、{@linkplain LoveappleMemberModel Loveapple会員情報}を取得する。
	 * 
	 * @param mail ログインID
	 * @return 見つからない場合、<code>null</code>を返す。
	 */
	public LoveappleMemberModel findByEmail(String mail);
	/**
	 * 
	 * キーをもとにデータを検索する
	 * 
	 * @param key
	 * @return
	 */
	public LoveappleMemberModel queryByKey(Key key);
	
	/**
	 * 登録後認証メールを送信
	 * @param member TODO
	 * @throws MailException TODO
	 */
	public MimeMessage sendRegistCertificationMail(LoveappleMemberModel member) throws MailException;
}
