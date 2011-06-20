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
package cn.loveapple.service.controller.health.action;

import static cn.loveapple.service.controller.Constant.*;
import static cn.loveapple.service.util.web.FrontUtil.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import cn.loveapple.service.Constant;
import cn.loveapple.service.controller.BaseController;
import cn.loveapple.service.controller.SessionLabel;
import cn.loveapple.service.controller.exception.ResourceNotFoundException;
import cn.loveapple.service.controller.health.form.BasalBodyTemperatureForm;
import cn.loveapple.service.controller.pojo.JsonResponse;
import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.model.SampleModel;
import cn.loveapple.service.cool.model.health.BasalBodyTemperatureModel;
import cn.loveapple.service.cool.service.health.BasalBodyTemperatureService;
import cn.loveapple.service.util.DateUtil;

/**
 * 
 * TODO
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Controller
@RequestMapping(value="/health/core/bbt")
public class BasalBodyTemperatureController extends BaseController implements SessionLabel {
	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(BasalBodyTemperatureController.class);
	
	/**
	 * ビジネスロジック
	 */
	private BasalBodyTemperatureService basalBodyTemperatureService;

	/**
	 * メッセージソース
	 */
	private ReloadableResourceBundleMessageSource messageSource;
	
	/**
	 * ポスト送信された場合、実行される
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value="regist.json")
	public MappingJacksonJsonView registBbt(@Valid BasalBodyTemperatureForm form, BindingResult result, HttpSession session, Locale locale) {

		
		if(!hasAttributeInSession(session, LOVEAPPLE_MEMBER)){
			return userErrorJsonView(result);
		}
		BasalBodyTemperatureValidator validator = new BasalBodyTemperatureValidator(messageSource, locale);
		validator.validate(form, result);
		if (result.hasErrors()) {
			return userErrorJsonView(result);
		}
		
		LoveappleMemberModel member = (LoveappleMemberModel) session.getAttribute(LOVEAPPLE_MEMBER);
		
		
		//ログイン中であれば、基礎体温の更新などを行う
		List<BasalBodyTemperatureModel> bbtList = null;
		try{
			if(StringUtils.isEmpty(form.getMeasureDay())){
				form.setMeasureDay(DateUtil.toDateString(new Date(), DateUtil.DATE_PTTERN_YYYYMMDD));
			}
			
			bbtList = basalBodyTemperatureService.findBasalBodyTemperatureByUser(
						member.getMail(), form.getMeasureDay(), form.getMeasureDay());
		}catch (IllegalArgumentException e) {
			throw e;
		}
		
		//フォームから更新/登録の基礎体温の属性を設定
		BasalBodyTemperatureModel bbt = createBasalBodyTemperatureModel(form, member);
		
		if(CollectionUtils.isEmpty(bbtList)){
			bbt = basalBodyTemperatureService.insertBasalBodyTemperatureModel(bbt);
		}else{
			bbt = basalBodyTemperatureService.updateBasalBodyTemperatureModel(bbt);
		}
		
		//TODO 正常時のJSON
		JsonResponse response = new JsonResponse();
		response.setStatus(STATUS_OK);
		response.setStatusCode(STATUS_CODE_SUCCESS);
		response.setData(bbt);
		
		return response;
	}
	
	/**
	 * DBに登録する基礎体温のモデルを生成する
	 * 
	 * @param form 元情報
	 * @param member 会員情報
	 * @return モデル
	 */
	private BasalBodyTemperatureModel createBasalBodyTemperatureModel(BasalBodyTemperatureForm form, LoveappleMemberModel member){
		BasalBodyTemperatureModel bbt = new BasalBodyTemperatureModel();
		bbt.setIsCoitus(Constant.FLG_ON.equals(form.getCoitusFlg()));
		bbt.setIsDysmenorrhea(Constant.FLG_ON.equals(form.getDysmenorrheaFlg()));
		bbt.setIsMenstruation(Constant.FLG_ON.equals(form.getMenstruationFlg()));
		if(BasalBodyTemperatureForm.LEUKORRHEA_LITTLE_CODE.equals(form.getLeukorrhea())){
			bbt.setLeukorrhea(BasalBodyTemperatureModel.Leukorrhea.LITTLE);
		}else if(BasalBodyTemperatureForm.LEUKORRHEA_MUCH_CODE.equals(form.getLeukorrhea())){
			bbt.setLeukorrhea(BasalBodyTemperatureModel.Leukorrhea.MUCH);
		}else if(BasalBodyTemperatureForm.LEUKORRHEA_USUALLY_CODE.equals(form.getLeukorrhea())){
			bbt.setLeukorrhea(BasalBodyTemperatureModel.Leukorrhea.USUALLY);
		}
		bbt.setMail(member.getMail());
		bbt.setMeasureDay(form.getMeasureDay());
		bbt.setName(member.getName());
		bbt.setTemperature(form.getTemperature());
		bbt.setTimeStamp(new Date());
		return bbt;
	}
	/**
	 * 
	 * @param start
	 * @param end
	 * @param result
	 * @param session
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping(value="list/{start}/{end}.json", method=RequestMethod.GET)
	public MappingJacksonJsonView getBbtList(@PathVariable String start,@PathVariable  String end, HttpSession session, Model model, Locale locale) {

		if(!DateUtil.isDateStr(start, DateUtil.DATE_PTTERN_YYYYMMDD) 
				|| !DateUtil.isDateStr(end, DateUtil.DATE_PTTERN_YYYYMMDD)){
			if(log.isDebugEnabled()){
				log.debug("invalid date string. start:" + start + " end:" + end);
			}
			throw new ResourceNotFoundException("invalid date string. start:" + start + " end:" + end);
		}
		if(!hasAttributeInSession(session, LOVEAPPLE_MEMBER)){
			if(log.isDebugEnabled()){
				log.debug("invalid member longin info.");
			}
			throw new ResourceNotFoundException("invalid member longin info.");
		}
		
		LoveappleMemberModel member = (LoveappleMemberModel) session.getAttribute(LOVEAPPLE_MEMBER);
		
		List<BasalBodyTemperatureModel> list = basalBodyTemperatureService.findBasalBodyTemperatureByUser(member.getMail(), start, end);
		
		JsonResponse response = new JsonResponse();
		response.setStatus(STATUS_OK);
		response.setStatusCode(STATUS_CODE_SUCCESS);
		response.setData(list);
		
		return response;
	}
	
	/**
	 * JSONを返すメソッド
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/json/{id}", method=RequestMethod.GET)
	public MappingJacksonJsonView jsonView(@PathVariable Long id, Model model){
		
		SampleModel data = new SampleModel();
		data.setName("mock data");
		data.setUpdateDate(new Date());
		MappingJacksonJsonView json = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("data", data);
		json.setAttributesMap(map);
		
		return json;
	}
	
	/**
	 * @param sampleService the sampleBusinessLogic to set
	 */
	@Autowired(required=true)
	public void setBasalBodyTemperatureService(BasalBodyTemperatureService basalBodyTemperatureService) {
		this.basalBodyTemperatureService = basalBodyTemperatureService;
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
