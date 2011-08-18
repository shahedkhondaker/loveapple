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
package cn.loveapple.service.controller.contents.action;

import java.util.Locale;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import cn.loveapple.service.controller.SessionLabel;
import cn.loveapple.service.controller.contents.form.SiteForm;
import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.model.SiteModel;
import cn.loveapple.service.cool.service.SiteContentsService;
import cn.loveapple.service.cool.service.exception.MailException;

/**
 * サイト操作コントローラ
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Controller
@RequestMapping(value="/site")
public class SiteController implements SessionLabel {

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(SiteController.class);
	
	/**
	 * サイトコンテンツ操作
	 */
	private SiteContentsService siteContentsService;
	
	/**
	 * メッセージソース
	 */
	private ReloadableResourceBundleMessageSource messageSource;

	/**
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "core/regist", method=RequestMethod.GET)
	public String regist(HttpSession session, Model model){
		session.removeAttribute(FORM);
		SiteForm form = new SiteForm();		
		model.addAttribute(form);
		return "core/regist";
	}

	/**
	 * 登録確認
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "core/registConfirm", method=RequestMethod.POST)
	public String registConfirm(@Valid SiteForm form, BindingResult result, HttpSession session, Model model, Locale locale) {
		model.addAttribute(form);	
		SiteValidator validator = new SiteValidator(messageSource, locale);
		if(result.hasErrors()){
			if(log.isDebugEnabled()){
				log.debug(ToStringBuilder.reflectionToString(result.getAllErrors()));
			}
			return "core/regist";
		}
		
		SiteModel siteModel = siteContentsService.findSite(form.getUnixName());
		
		if(siteModel != null){
			result.reject("loveappleErrors.beRegisted", validator.createArgs("msg.site"), "");
			return "core/regist"; 
		}
		
		session.setAttribute(FORM, form);
		
		return "core/registConfirm";
	}
	@RequestMapping(value = "core/registComplete", method=RequestMethod.POST)
	public String registComplete(HttpSession session, HttpServletRequest request, Model model, Locale locale) {
		
		SiteForm siteForm = (SiteForm) session.getAttribute(FORM);
		if(siteForm == null){
			try {
				throw new NoSuchRequestHandlingMethodException(request);
			} catch (NoSuchRequestHandlingMethodException e) {
				throw new HttpMessageNotWritableException(e.getMessage(), e);
			}
		}
		/*LoveappleMemberModel member = (LoveappleMemberModel) session.getAttribute(LOVEAPPLE_MEMBER_TMP);
		
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
		session.removeAttribute(FORM);
		session.removeAttribute(LOVEAPPLE_MEMBER_TMP);
		
		// 承認メールを送信
		try {
			MimeMessage message = memberCoreService.sendRegistCertificationMail(member);
			log.info("Send mail: " + ToStringBuilder.reflectionToString(message));
		} catch (MailException e) {
			log.warn("メール送信失敗。", e);
		}*/
		
		return "redirect:/member/certification";
	}
}
