package cn.loveapple.service.controller.member.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import cn.loveapple.service.controller.exception.ResourceNotFoundException;
import cn.loveapple.service.controller.sample.form.SampleForm;
import cn.loveapple.service.cool.model.SampleModel;
import cn.loveapple.service.cool.service.MemberCoreService;
import cn.loveapple.service.cool.service.SampleService;

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
	 * 会員情報操作ロジックを設定します。
	 * @param memberCoreService 会員情報操作ロジック
	 */
	@Autowired(required=true)
	public void setMemberCoreService(MemberCoreService memberCoreService) {
	    this.memberCoreService = memberCoreService;
	}
}
