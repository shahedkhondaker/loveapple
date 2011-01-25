package cn.loveapple.service.controller;
import static cn.loveapple.service.controller.Constant.*;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import cn.loveapple.service.controller.exception.ResourceNotFoundException;
import cn.loveapple.service.controller.pojo.Response;
/**
 * 基底コントローラクラス。<br>
 * システム全体に関わるアクションを定期する。
 * 
 * @author hao_shunri
 * @version $Revision: 62 $
 * @date $Date: 2010-10-11 20:39:25 +0900 (月, 11 10 2010) $
 * @id $Id: BaseController.java 62 2010-10-11 11:39:25Z hao0323 $
 *
 */
@Controller
@RequestMapping(value=URL_BASE)
public class BaseController{
	/**
	 * 
	 * @param basePath コントローラのパス
	 * @param type {@linkplain #CONTENTS_TYPE_HTML}又は{@linkplain #CONTENTS_TYPE_JSON}のコンテンツタイプ
	 * @param id
	 * @return redirect:<<code>basePath</code>>/<<code>id</code>>.<<code>type</code>>を戻す
	 */
	protected String redirectHtmlOrJsonView(String basePath,String type, String id) {
		if(!CONTENTS_TYPE_HTML.equals(type)
				&& !CONTENTS_TYPE_JSON.equals(type)){
			throw new ResourceNotFoundException("invalid contents type.");
		}
		StringBuilder redirectPath = new StringBuilder(128);
		redirectPath.append("redirect:");
		redirectPath.append(basePath);
		if(!basePath.endsWith("/")){
			redirectPath.append("/");
		}
		redirectPath.append(id);
		redirectPath.append(".");
		redirectPath.append(type);
		
		return redirectPath.toString();
	}

	/**
	 * 入力エラーがない場合、<code>null</code>を返す。
	 * 
	 * @param result
	 * @return
	 */
	public MappingJacksonJsonView userErrorJsonView(BindingResult result){
		if(!result.hasErrors()){
			return null;
		}
		Response response = new Response();
		response.setStatus(STATUS_NG);
		response.setStatusCode(STATUS_CODE_USER_ERROR);
		StringBuilder errMsg = new StringBuilder(512);
		for (FieldError error : result.getFieldErrors()) {
			errMsg.append(error.getRejectedValue());
			errMsg.append("\n");
		}
		response.setMsg(errMsg.toString());
		MappingJacksonJsonView json = new MappingJacksonJsonView();
		json.addStaticAttribute("response", response);
		return json;
	}
	/**
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="{userName}." + CONTENTS_TYPE_HTML,method=RequestMethod.GET)
	public String userErrorHtmlView(@PathVariable String userName){
		return "";
	}
}
