/**
 * 
 */
package cn.loveapple.service.controller.member;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.loveapple.service.cool.pojo.LoveappleMember;
import cn.loveapple.service.cool.pojo.type.Sex;
import cn.loveapple.service.cool.service.MemberService;
import cn.loveapple.service.form.MemberForm;

/**
 * @author hcl
 * 
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	/**
	 * 
	 */
	private static Log log = LogFactory.getLog(MemberController.class);

	/**
	 * 
	 */
	private MemberService memberService;

	Long PLACE_KEY;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String registMemberInput(Model model) {
		MemberForm form = new MemberForm();
		model.addAttribute(form);
		return "member/registInput";
	}

	/**
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "confirm", method = RequestMethod.POST)
	public String registMemberConfirm(@Valid MemberForm form,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "member/registInput";
		}

		PLACE_KEY = (new Random()).nextLong();
		LoveappleMember member = memberService.getMember(form.getNickName());

		if (member != null) {
			result.addError(new ObjectError("beRegisted", "登録済みのユーザです。"));
			return "member/registInput";
		}

		return "member/registConfirm";
	}

	/**
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "complet", method = RequestMethod.POST)
	public String registMemberComplet(@Valid MemberForm form,
			BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				log.error(objectError.getDefaultMessage());
			}
			return "member/registInput";
		}

		LoveappleMember member = null;
		if (((Long) session.getAttribute("LAST_PLACE_KEY")) != null
				&& ((Long) session.getAttribute("LAST_PLACE_KEY"))
						.equals(PLACE_KEY)) {
			member = memberService.getMember(form.getNickName());
			return "redirect:/member/complete/" + member.getKey().getId();
		}
		session.setAttribute("LAST_PLACE_KEY", PLACE_KEY);


		member = new LoveappleMember();
		BeanUtils.copyProperties(form, member);
		switch (Integer.parseInt(form.getSexStr())) {
		case 1:
			member.setSex(Sex.FAIR);
			break;

		case 2:
			member.setSex(Sex.MALE);
			break;
		
		case 3:
			member.setSex(Sex.OTHER);
		}

		LoveappleMember resultMember = memberService.registMember(member);

		return "redirect:/member/complete/" + resultMember.getKey().getId();
	}

	/**
	 * 
	 * @param nickName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "complete/{nickName}", method = RequestMethod.GET)
	public String registMemberCompleted(@PathVariable String nickName, Model model) {
		LoveappleMember member = memberService.getMember(nickName);

		if (member != null) {
			throw new RuntimeException("登録済みのユーザです。");
		}

		return "member/complete";
	}

	/**
	 * memberServiceを設定します。
	 * 
	 * @param memberService
	 *            memberService
	 */
	@Autowired(required = true)
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
}
