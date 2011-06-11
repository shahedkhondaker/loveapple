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

import static cn.loveapple.service.util.web.FrontUtil.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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

import cn.loveapple.service.controller.SessionLabel;
import cn.loveapple.service.controller.health.form.BasalBodyTemperatureForm;
import cn.loveapple.service.cool.model.SampleModel;
import cn.loveapple.service.cool.service.health.BasalBodyTemperatureService;

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
@RequestMapping(value="/core/health/bbt")
public class BasalBodyTemperatureController implements SessionLabel {
	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(BasalBodyTemperatureController.class);
	
	/**
	 * ビジネスロジック
	 */
	private BasalBodyTemperatureService basalBodyTemperatureService;

	/**
	 * ポスト送信された場合、実行される
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String registBbt(@Valid BasalBodyTemperatureForm form, BindingResult result, HttpSession session, Model model, Locale locale) {
		if (result.hasErrors()) {
			return "sample/createForm";
		}
		
		if(!hasAttributeInSession(session, LOVEAPPLE_MEMBER)){
			//TODO
		}
		
		//TODO ログイン中であれば、基礎体温の更新などを行う
		
		//SampleModel data = basalBodyTemperatureService.
		//		newAndPut(form.getName(), form.getLatitude(), form.getLongitude(), form.getAccuracy(), form.getDetail());
		
		return "redirect:/sample/" ;//+ data.getKey().getId();
	}
	
	/**
	 * @{@link #create(BasalBodyTemperatureForm, BindingResult)}からリダイレクトされ、画面を表示させる
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{start}/{end}", method=RequestMethod.GET)
	public String getBbtList(@PathVariable String start, String end, Model model) {
		/*SampleModel data = basalBodyTemperatureService.queryByKey(id);
		if(data == null){
			if(log.isDebugEnabled()){
				log.debug("location list is empty by id :[" + id + "]");
			}
			throw new ResourceNotFoundException(id);
		}

		List<SampleModel> locationList = basalBodyTemperatureService.queryAllByName(data.getName());
		
		BasalBodyTemperatureForm form = new BasalBodyTemperatureForm();
		form.setAccuracy(data.getAccuracy());
		form.setDetail(data.getDetail());
		form.setLatitude(data.getLocation().getLatitude());
		form.setLongitude(data.getLocation().getLongitude());
		form.setName(data.getName());
		
		model.addAttribute(form);
		model.addAttribute("locationList", locationList);*/
		
		return "sample/view";
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
	 * バイナリデータを返すメソッド
	 * 
	 * @param id
	 * @param request
	 * @param session
	 * @param outputStream
	 * @throws IOException
	 */
	@RequestMapping(value="/binary/{id}", method=RequestMethod.GET)
	public void binaryView(@PathVariable Long id,HttpServletRequest request, HttpSession session, OutputStream outputStream) throws IOException{
		outputStream.write((ToStringBuilder.reflectionToString(session) +
				ToStringBuilder.reflectionToString(request)).getBytes());
	}
	/**
	 * @param sampleService the sampleBusinessLogic to set
	 */
	@Autowired(required=true)
	public void setBasalBodyTemperatureService(BasalBodyTemperatureService basalBodyTemperatureService) {
		this.basalBodyTemperatureService = basalBodyTemperatureService;
	}
}
