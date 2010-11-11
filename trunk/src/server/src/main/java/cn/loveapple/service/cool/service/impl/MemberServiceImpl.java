/**
 * 
 */
package cn.loveapple.service.cool.service.impl;

import java.util.List;

import org.slim3.datastore.Datastore;

import cn.loveapple.service.cool.pojo.LoveappleMember;
import cn.loveapple.service.cool.pojo.LoveappleMemberMeta;
import cn.loveapple.service.cool.service.MemberService;

import com.google.appengine.api.datastore.Key;

/**
 * @author hcl
 *
 */
public class MemberServiceImpl implements MemberService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMember getMember(String nickName) {
		if(nickName == null){
			throw new IllegalArgumentException("member is empty.");
		}
		LoveappleMemberMeta meta = LoveappleMemberMeta.get();
		
		return Datastore.query(LoveappleMember.class)
				.filter(meta.nickName.equal(nickName)).asSingle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<LoveappleMember> getMemberList(LoveappleMember member) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMember registMember(LoveappleMember member) {
		if(member == null){
			throw new IllegalArgumentException("member is empty.");
		}
		LoveappleMember result = getMember(member.getNickName());
		if(result != null){
			return result;
		}
		
		updateMember(member);
		
		return member;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMember updateMember(LoveappleMember member) {
		if(member == null){
			throw new IllegalArgumentException("member is empty.");
		}
		
		Key key = Datastore.put(member);
		member.setKey(key);
		
		return member;
	}

}
