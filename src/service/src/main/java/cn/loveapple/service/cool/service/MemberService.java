/**
 * 
 */
package cn.loveapple.service.cool.service;

import java.util.List;

import cn.loveapple.service.cool.pojo.LoveappleMember;

/**
 * 会員情報を処理するサービス
 * 
 * @author hcl
 *
 */
public interface MemberService {
	/**
	 * 会員登録
	 * 
	 * @see #updateMember(LoveappleMember)
	 * @param member
	 * @return
	 */
	public LoveappleMember registMember(LoveappleMember member);
	/**
	 * 会員情報更新
	 * 
	 * @param member
	 * @return
	 */
	public LoveappleMember updateMember(LoveappleMember member);
	/**
	 * 条件にあった会員の一覧を取得する
	 * 
	 * @param member
	 * @return
	 */
	public List<LoveappleMember> getMemberList(LoveappleMember member);
	/**
	 * 対象となる会員を取得する
	 * 
	 * @param nickName
	 * @return
	 */
	public LoveappleMember getMember(String nickName);
}
