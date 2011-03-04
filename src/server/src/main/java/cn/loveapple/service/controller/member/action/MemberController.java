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
package cn.loveapple.service.controller.member.action;

import static cn.loveapple.service.cool.service.MemberCoreService.*;
import static cn.loveapple.service.util.web.FrontUtil.*;

import java.util.Locale;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import cn.loveapple.service.controller.SessionLabel;
import cn.loveapple.service.controller.exception.ResourceNotFoundException;
import cn.loveapple.service.controller.member.form.MemberAuthForm;
import cn.loveapple.service.controller.member.form.MemberCertificationForm;
import cn.loveapple.service.controller.member.form.MemberForm;
import cn.loveapple.service.controller.member.form.MemberValidator;
import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.model.LoveappleMemberModel.Status;
import cn.loveapple.service.cool.service.MemberCoreService;
import cn.loveapple.service.cool.service.exception.MailException;

import com.google.appengine.api.datastore.GeoPt;

/**
 * 会員情報操作コントローラ
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Controller
@RequestMapping(value="/member")
public class MemberController implements SessionLabel{

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(MemberController.class);
		
	/**
	 * 会員情報操作ロジック
	 */
	private MemberCoreService memberCoreService;
	
	/**
	 * メッセージソース
	 */
	private ReloadableResourceBundleMessageSource messageSource;

	/**
	 * 登録入力
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "regist", method=RequestMethod.GET)
	public String regist(HttpSession session, Model model) {
		clearMemberInfo(session);
		Object formObj = session.getAttribute(MemberForm.FORM_NAME);
		MemberForm form = formObj instanceof MemberForm ? (MemberForm)formObj :null ;
		if(form == null){
			form = new MemberForm();
		}
		
		model.addAttribute(form);
		return "member/regist";
	}
	
	/**
	 * 登録確認
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "registConfirm", method=RequestMethod.POST)
	public String registConfirm(@Valid MemberForm form, BindingResult result, HttpSession session, Model model, Locale locale) {
		model.addAttribute(form);
		MemberValidator validator = new MemberValidator(messageSource, locale);
		validator.validate(form, result);		
		if(result.hasErrors()){
			if(log.isDebugEnabled()){
				log.debug(ToStringBuilder.reflectionToString(result.getAllErrors()));
			}
			return "member/regist";
		}
		
		LoveappleMemberModel member = memberCoreService.findByEmail(form.getMail());
		if(member != null){
			result.reject("loveappleErrors.beRegisted", validator.createArgs("msg.member"), "");
			return "member/regist"; 
		}
		
		
		member = createModel(form, locale);
		
		session.setAttribute(MemberForm.FORM_NAME, form);
		session.setAttribute(LOVEAPPLE_MEMBER_TMP, member);
		
		return "member/registConfirm";
	}
	
	/**
	 * 
	 * @param form
	 * @return
	 */
	protected LoveappleMemberModel createModel(MemberForm form, Locale locale){
		LoveappleMemberModel model = new LoveappleMemberModel();
		
		model.setMail(form.getMail());
		model.setLastAccuracy(form.getLastAccuracy());
		if(form.getLatitude() != null && form.getLongitude() != null){
			model.setLastLocation(new GeoPt(form.getLatitude(), form.getLongitude()));
		}
		model.setName(form.getName());
		model.setPassword(form.getPassword());
		model.setPermission(DEFAULT_MEMBER_PERMISSION);
		model.setQqAuthKey(form.getQqAuthKey());
		model.setQqId(form.getQqId());
		model.setStatus(Status.CONFIRMATION);
		model.setDefaultLocale(locale);
		
		return model;
	}
	
	/**
	 * 登録完了
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "registComplete", method=RequestMethod.POST)
	public String registComplete(HttpSession session, HttpServletRequest request, Model model, Locale locale) {
		
		LoveappleMemberModel member = (LoveappleMemberModel) session.getAttribute(LOVEAPPLE_MEMBER_TMP);
		
		if(member == null){
			try {
				throw new NoSuchRequestHandlingMethodException(request);
			} catch (NoSuchRequestHandlingMethodException e) {
				throw new HttpMessageNotWritableException(e.getMessage(), e);
			}
		}
		member = memberCoreService.insertLoveappleMember(member);
		if(member == null){
			throw new HttpMessageNotWritableException("can not regist member. "
					+ ToStringBuilder.reflectionToString(session.getAttribute(LOVEAPPLE_MEMBER_TMP)));
		}
		session.removeAttribute(MemberForm.FORM_NAME);
		session.removeAttribute(LOVEAPPLE_MEMBER_TMP);
		
		// 承認メールを送信
		try {
			MimeMessage message = memberCoreService.sendRegistCertificationMail(member);
			log.info("Send mail: " + ToStringBuilder.reflectionToString(message));
		} catch (MailException e) {
			log.warn("メール送信失敗。", e);
		}
		
		return "redirect:/member/certification";
	}

	/**
	 * 直打ちの場合、実行される
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String index(HttpSession session, Model model) {
		clearMemberInfo(session);
		MemberAuthForm form = new MemberAuthForm();
		
		model.addAttribute(form);
		return "member/index";
	}
	/**
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
		if(hasAttributeInSession(session, LOVEAPPLE_MEMBER)){
			session.removeAttribute(LOVEAPPLE_MEMBER);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="certification", method=RequestMethod.GET)
	public String certification(@Valid MemberCertificationForm form, BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("certificationResult", false);
			return "member/certification";
		}
		LoveappleMemberModel member = memberCoreService.queryByKey(new Long(form.getId()));
		
		if(member != null && form.getCertificationCode().equals(member.getCertificationCode())){
			member.setStatus(Status.NORMAL);
			member.setCertificationCode(null);
			memberCoreService.updateLoveappleMember(member);
			model.addAttribute("certificationResult", true);
		}else{
			model.addAttribute("certificationResult", false);
		}
		
		return "member/certification";
	}
	/**
	 * ポスト送信された場合、実行される
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value="auth", method=RequestMethod.POST)
	public String auth(@Valid MemberAuthForm form, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "member/index";
		}
		
		LoveappleMemberModel member = memberCoreService.authenticateLoveappleMember(form.getMail(), form.getPassword());

		if(member == null){
			result.reject("loveappleErrors.auth");
			return "member/index";
		}
				
		session.setAttribute(LOVEAPPLE_MEMBER, member);
		
		Object refererUrl = session.getAttribute(REFERER_INNER_URL);
		if(log.isDebugEnabled()){
			log.debug("auth will redirect? [" + refererUrl + "]");
		}
		if(refererUrl != null){
			session.removeAttribute(REFERER_INNER_URL);
			return "redirect:" + refererUrl;
		}
		return "redirect:/member/core/info/" + member.getKey().getId();
	}

	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="core/info/{id}", method=RequestMethod.GET)
	public String info(@PathVariable Long id, Model model, HttpSession session) {
				
		LoveappleMemberModel member = memberCoreService.queryByKey(id);
		
		if(member == null){
			throw new ResourceNotFoundException(id);
		}
		
		model.addAttribute("member", member);
		
		return "member/core/info";
	}
	
	/**
	 * セッション情報から会員情報を削除する。
	 * 
	 * @param session セッション
	 */
	protected void clearMemberInfo(HttpSession session) {
		session.removeAttribute(LOVEAPPLE_MEMBER);
	}
	/**
	 * 会員情報操作ロジックを設定します。
	 * @param memberCoreService 会員情報操作ロジック
	 */
	@Autowired(required=true)
	public void setMemberCoreService(MemberCoreService memberCoreService) {
	    this.memberCoreService = memberCoreService;
	}

	/**
	 * メッセージソースを設定します。
	 * @param messageSource メッセージソース
	 */
	@Autowired(required=true)
	public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
	    this.messageSource = messageSource;
	}
	
	
}
