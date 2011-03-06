package cn.loveapple.service.cool.service.impl;

import static cn.loveapple.service.cool.model.LoveappleModel.*;
import static cn.loveapple.service.util.service.MailUtil.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.slim3.datastore.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import cn.loveapple.service.cool.meta.LoveappleMemberModelMeta;
import cn.loveapple.service.cool.model.FixedMailModel;
import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.service.MemberCoreService;
import cn.loveapple.service.cool.service.exception.MailException;
import cn.loveapple.service.type.ServiceComp;
import cn.loveapple.service.util.web.AccountUtil;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
/**
 * 
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
@ServiceComp
public class MemberCoreServiceImpl extends BaseServiceImpl implements MemberCoreService {

	/**
	 * メール送信
	 */
	private JavaMailSender javaMailSender;
	
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
		if(member != null 
				&& password.equals(member.getPassword())
				&& LoveappleMemberModel.Status.NORMAL.equals(member.getStatus())){
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
		
		Date now = new Date();
		
		member.setCertificationCode(AccountUtil.genCertificationCode());
		member.setInsertDate(now);
		member.setUpdateDate(now);
		return dmLoveappleMember(member);
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
		return queryByKey(key, LoveappleMemberModel.class);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public MimeMessage sendRegistCertificationMail(LoveappleMemberModel member) throws MailException {
		if(member == null){
			throw new IllegalArgumentException("mail address is empty.");
		}
		
		// TODO 定形メールを取得する。
		FixedMailModel fixedMail = new FixedMailModel();
		fixedMail.setMailCode("");
		fixedMail.setEncode("ISO-2022-JP");
		fixedMail.setBody("以下のURLへアクセスして登録を完成させてください。\n" +
				"http://loveapple-facade.appspot.com/member/certification?" +
				"id=" + member.getKey().getId() +
				"&certificationCode=" + member.getCertificationCode());
		try {
			fixedMail.setFromAddress(new InternetAddress("hao0323@gmail.com", "loveapple", fixedMail.getEncode()));
		} catch (UnsupportedEncodingException e) {
			throw new MailException("invalid encode: " + fixedMail.getEncode() , e);
		}
		fixedMail.setLocale(member.getDefaultLocale());
		fixedMail.setSubject("loveapple登録の承認");
		
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			mimeMessage = genMessageFromFixed(fixedMail, mimeMessage, member.getMail(), null);
			Transport.send(mimeMessage);
		} catch (MessagingException e) {
			throw new MailException("fail send mail: " + member.getMail(), e);
		}
		
		return mimeMessage;
	}

	/**
	 * メール送信を設定します。
	 * @param javaMailSender メール送信
	 */
	@Autowired(required=true)
	public void setJavaMailSender(JavaMailSender javaMailSender) {
	    this.javaMailSender = javaMailSender;
	}

}
