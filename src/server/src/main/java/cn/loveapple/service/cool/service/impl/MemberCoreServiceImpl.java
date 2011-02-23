package cn.loveapple.service.cool.service.impl;

import static cn.loveapple.service.cool.model.LoveappleModel.*;

import java.util.Date;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang.StringUtils;
import org.slim3.datastore.Datastore;

import cn.loveapple.service.cool.meta.LoveappleMemberModelMeta;
import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.service.MemberCoreService;
import cn.loveapple.service.type.Service;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
/**
 * 
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
@Service
public class MemberCoreServiceImpl implements MemberCoreService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMemberModel authenticateLoveappleMember(String mail,
			String password) {
		if(StringUtils.isEmpty(mail) || StringUtils.isEmpty(password)){
			throw new IllegalArgumentException("mail or password is empty.");
		}
		LoveappleMemberModel member = findByEmail(mail);
		if(member != null && password.equals(member.getPassword())){
			member.setLastLoginDate(new Date());
			return updateLoveappleMember(member);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMemberModel updateLoveappleMember(LoveappleMemberModel member) {
		if(member == null){
			throw new IllegalArgumentException("member info is empty.");
		}
		LoveappleMemberModel result = findByEmail(member.getMail());
		if(result == null){
			throw new RuntimeException("not found member. " + member.getMail());
		}
		member.setUpdateDate(new Date());
		member.setKey(result.getKey());
		return dmLoveappleMember(member);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMemberModel insertLoveappleMember(LoveappleMemberModel member) {
		if(member == null){
			throw new IllegalArgumentException("member info is empty.");
		}
		LoveappleMemberModel result = findByEmail(member.getMail());
		if(result != null){
			throw new RuntimeException("member is ben registed." + member.getMail());
		}
		member.setUpdateDate(new Date());
		return dmLoveappleMember(member);
	}

	/**
	 * 
	 * @param member
	 * @return
	 */
	private LoveappleMemberModel dmLoveappleMember(LoveappleMemberModel member) {
		Key key = Datastore.put(member);
		
		return queryByKey(key);
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	public LoveappleMemberModel findByEmail(String mail){

		if(StringUtils.isEmpty(mail)){
			throw new IllegalArgumentException("loginId is empty.");
		}
		LoveappleMemberModelMeta meta = LoveappleMemberModelMeta.get();
		return Datastore.query(LoveappleMemberModel.class).filter(meta.mail.equal(mail)).asSingle();
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

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public LoveappleMemberModel queryByKey(Key key) {
		if(key == null){
			throw new IllegalArgumentException("key is empty.");
		}
		
		LoveappleMemberModelMeta meta = LoveappleMemberModelMeta.get();
		return Datastore.query(LoveappleMemberModel.class).filter(meta.key.equal(key)).asSingle();
	}

}
