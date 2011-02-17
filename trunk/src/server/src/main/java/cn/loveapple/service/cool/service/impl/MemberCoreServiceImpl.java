package cn.loveapple.service.cool.service.impl;

import static cn.loveapple.service.cool.model.ModelConstant.*;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slim3.datastore.Datastore;
import org.springframework.stereotype.Component;

import cn.loveapple.service.cool.meta.LoveappleMemberModelMeta;
import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.service.MemberCoreService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
/**
 * 
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
@Component
public class MemberCoreServiceImpl implements MemberCoreService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMemberModel authenticateLoveappleMember(String loginId,
			String password) {
		if(StringUtils.isEmpty(loginId) || StringUtils.isEmpty(password)){
			throw new IllegalArgumentException("loginId or password is empty.");
		}
		LoveappleMemberModel member = findByLoginId(loginId);
		if(member != null && password.equals(member.getPassword())){
			member.setLastLoginDate(new Date());
			return newAndPut(member);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMemberModel newAndPut(LoveappleMemberModel member) {
		if(member == null){
			throw new IllegalArgumentException("member info is empty.");
		}
		if(member != null){
			LoveappleMemberModel result = findByLoginId(member.getLoginId());
			member.setKey(result.getKey());
			member.setUpdateDate(new Date());
		}
		Key key = Datastore.put(member);
		
		return queryByKey(key);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public LoveappleMemberModel findByLoginId(String loginId){

		if(StringUtils.isEmpty(loginId)){
			throw new IllegalArgumentException("loginId is empty.");
		}
		LoveappleMemberModelMeta meta = LoveappleMemberModelMeta.get();
		return Datastore.query(LoveappleMemberModel.class).filter(meta.loginId.equal(loginId)).asSingle();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMemberModel queryByKey(Long keyId) {

		if(keyId == null){
			throw new IllegalArgumentException("keyId is empty.");
		}
		
		Key key = KeyFactory.createKey(LOVEAPPLE_MEMBER_MODEL, keyId);
		return queryByKey(key);
	}

	@Override
	public LoveappleMemberModel queryByKey(Key key) {
		if(key == null){
			throw new IllegalArgumentException("key is empty.");
		}
		
		LoveappleMemberModelMeta meta = LoveappleMemberModelMeta.get();
		return Datastore.query(LoveappleMemberModel.class).filter(meta.key.equal(key)).asSingle();
	}

}
