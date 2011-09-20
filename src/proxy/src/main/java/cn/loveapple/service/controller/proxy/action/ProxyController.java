package cn.loveapple.service.controller.proxy.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * プロキシーサーバコントローラ
 * 
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
@Controller
@RequestMapping(value="/proxy")
public class ProxyController {
	public static final String URL_PARAM_NAME = "____request_url";
	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(ProxyController.class);
	
	/**
	 * 直打ちの場合、実行される
	 * 
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		SampleForm form = new SampleForm();
		form.setName("anyone");

		model.addAttribute(form);
		
		return "sample/createForm";
	}

	*//**
	 * ポスト送信された場合、実行される
	 * 
	 * @param form
	 * @param result
	 * @return
	 *//*
	@RequestMapping(value="{id}",method=RequestMethod.POST)
	public String create(@Valid SampleForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "sample/createForm";
		}
		
		SampleModel data = sampleService.
				newAndPut(form.getName(), form.getLatitude(), form.getLongitude(), form.getAccuracy(), form.getDetail());
		
		return "redirect:/sample/" + data.getKey().getId();
	}
	
	*//**
	 * @{@link #create(SampleForm, BindingResult)}からリダイレクトされ、画面を表示させる
	 * 
	 * @param id
	 * @param model
	 * @return
	 *//*
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
	
	*//**
	 * JSONを返すメソッド
	 * 
	 * @param id
	 * @param model
	 * @return
	 *//*
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
	}*/
	
	/**
	 * バイナリデータを返すメソッド
	 * 
	 * @param id
	 * @param request
	 * @param session
	 * @param outputStream
	 * @throws IOException
	 */
	@RequestMapping()
	public void binaryView(HttpServletRequest request, HttpServletResponse response, HttpSession session, OutputStream outputStream) throws IOException{
		response.setContentType("text/plain");
		
		for (
			@SuppressWarnings("unchecked")
			Enumeration<String> headers = request.getHeaderNames();headers.hasMoreElements();) {
			String headerName = (String) headers.nextElement();
			String header = request.getHeader(headerName);
			outputStream.write((headerName + ":" + header + "\n").getBytes());
		}
	}
}
