package cn.loveapple.service.controller.member.action;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.loveapple.service.controller.exception.ResourceNotFoundException;
import cn.loveapple.service.controller.member.form.MemberAuthForm;
import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.service.MemberCoreService;

/**
 * サンプルコントローラ
 * 
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Controller
@RequestMapping(value="/member")
public class MemberController {
	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(MemberController.class);
		
	/**
	 * 会員情報操作ロジック
	 */
	private MemberCoreService memberCoreService;


	/**
	 * 直打ちの場合、実行される
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		MemberAuthForm form = new MemberAuthForm();
		
		model.addAttribute(form);
		return "member/index";
	}
	/**
	 * ポスト送信された場合、実行される
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value="auth", method=RequestMethod.POST)
	public String auth(@Valid MemberAuthForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "member/index";
		}
		
		LoveappleMemberModel member = memberCoreService.authenticateLoveappleMember(form.getLoginId(), form.getPassword());

		if(member == null){
			result.addError(new ObjectError("auth", "errors.auth"));
			return "member/index";
		}
		
		return "redirect:/member/core/info" + member.getKey().getId();
	}

	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="core/info/{id}", method=RequestMethod.GET)
	public String info(@PathVariable Long id, Model model) {
		
		LoveappleMemberModel member = memberCoreService.queryByKey(id);
		
		if(member == null){
			throw new ResourceNotFoundException(id);
		}
		
		model.addAttribute("member", member);
		
		return "member/core/info";
	}
	
	/**
	 * 会員情報操作ロジックを設定します。
	 * @param memberCoreService 会員情報操作ロジック
	 */
	@Autowired(required=true)
	public void setMemberCoreService(MemberCoreService memberCoreService) {
	    this.memberCoreService = memberCoreService;
	}
}
