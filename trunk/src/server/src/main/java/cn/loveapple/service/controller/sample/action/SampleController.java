package cn.loveapple.service.controller.sample.action;

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
import cn.loveapple.service.cool.service.SampleService;

/**
 * サンプルコントローラ
 * 
 * @author $author:$
 * @version $Revision: 111 $
 * @date $Date: 2010-10-27 19:18:30 +0900 (水, 27 10 2010) $
 * @id $Id: SampleController.java 111 2010-10-27 10:18:30Z hao0323@gmail.com $
 *
 */
@Controller
@RequestMapping(value="/sample")
public class SampleController {
	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(SampleController.class);
	
	/**
	 * ビジネスロジック
	 */
	private SampleService sampleService;

	/**
	 * 直打ちの場合、実行される
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		SampleForm form = new SampleForm();
		form.setName("anyone");

		model.addAttribute(form);
		
		return "sample/createForm";
	}

	/**
	 * ポスト送信された場合、実行される
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String create(@Valid SampleForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "sample/createForm";
		}
		
		SampleModel data = sampleService.
				newAndPut(form.getName(), form.getLatitude(), form.getLongitude(), form.getAccuracy(), form.getDetail());
		
		return "redirect:/sample/" + data.getKey().getId();
	}
	
	/**
	 * @{@link #create(SampleForm, BindingResult)}からリダイレクトされ、画面を表示させる
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String getView(@PathVariable Long id, Model model) {
		SampleModel data = sampleService.queryByKey(id);
		if(data == null){
			if(log.isDebugEnabled()){
				log.debug("location list is empty by id :[" + id + "]");
			}
			throw new ResourceNotFoundException(id);
		}

		List<SampleModel> locationList = sampleService.queryAllByName(data.getName());
		
		SampleForm form = new SampleForm();
		form.setAccuracy(data.getAccuracy());
		form.setDetail(data.getDetail());
		form.setLatitude(data.getLocation().getLatitude());
		form.setLongitude(data.getLocation().getLongitude());
		form.setName(data.getName());
		
		model.addAttribute(form);
		model.addAttribute("locationList", locationList);
		
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
	public void setSampleBusinessLogic(SampleService sampleService) {
		this.sampleService = sampleService;
	}
}
