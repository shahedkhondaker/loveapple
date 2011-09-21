package cn.loveapple.service.controller.proxy.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

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
	URLFetchService fetchService;
	public ProxyController(){
		fetchService = URLFetchServiceFactory.getURLFetchService();
	}
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
		
		String queryStr = request.getQueryString();
		if(StringUtils.isEmpty(queryStr) || !queryStr.startsWith(URL_PARAM_NAME)){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.setContentType(request.getContentType());
			return;
		}

		URL url = getUrl(queryStr);

		HTTPRequest clientRequest = new HTTPRequest(url, getMethode(request.getMethod()));

		outputStream.write((url.getProtocol() + ":" + url.getHost() + ":" + url.getFile() + "\n").getBytes());
//		outputStream.write((request.getMethod() + "\n").getBytes());
		
		for (
			@SuppressWarnings("unchecked")
			Enumeration<String> headers = request.getHeaderNames();headers.hasMoreElements();) {
			
			String headerName = (String) headers.nextElement();
			HTTPHeader header = new HTTPHeader(headerName, request.getHeader(headerName));
			clientRequest.setHeader(header);
		}
		for (Object paramEntry : request.getParameterMap().entrySet()) {
			Entry<String, Object> entry = (Entry<String, Object>) paramEntry;
			outputStream.write((entry.getKey() + ":" + entry.getValue() + "\n").getBytes());
		}
	}
	private URL getUrl(String queryStr) throws UnsupportedEncodingException, MalformedURLException{
		String urlStr = URLDecoder.decode(queryStr.substring(URL_PARAM_NAME.length() + 1), "UTF-8");

		String protocol;
		String host;
		String file;
		int port = 80;
		int protocolLength = 0;
		if(urlStr.startsWith("https")){
			protocol = "https";
			protocolLength = "https://".length();
			port = 443;
		}else if(urlStr.startsWith("http")){
			protocol = "http";
			protocolLength = "http://".length();
		}else{
			protocol = "http";
		}
		host = urlStr.substring(protocolLength, urlStr.indexOf('/', protocolLength));
		file = urlStr.substring(protocolLength + host.length());
		
		int portPoint = host.indexOf(':');
		if(1 < portPoint){
			host = host.substring(0, portPoint);
			if(log.isDebugEnabled()){
				log.debug("portPoint:" + portPoint + " host:" + host + " port:" + port + " file:" + file);
				log.debug(host.substring(portPoint));
			}
			port = Integer.parseInt(host.substring(portPoint));
		}
		
		if(log.isDebugEnabled()){
			log.debug("protocol:" + protocol + " host:" + host + " port:" + port + " file:" + file);
		}

		return new URL(protocol, host, port, file);
	}
	private HTTPMethod getMethode(String src){
		if("GET".equals(src.toUpperCase())){
			return HTTPMethod.GET;
		}
		if("POST".equals(src.toUpperCase())){
			return HTTPMethod.POST;
		}
		if("DELETE".equals(src.toUpperCase())){
			return HTTPMethod.DELETE;
		}
		if("PUT".equals(src.toUpperCase())){
			return HTTPMethod.PUT;
		}
		return HTTPMethod.HEAD;
	}
}
