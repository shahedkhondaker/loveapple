package cn.loveapple.service.controller.pojo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * レスポンスの基底クラス
 * 
 * @author hao_shunri
 *
 */
public class JsonResponse extends MappingJacksonJsonView{
	
	/**
	 * データノード
	 */
	private Object data;
	
	/**
	 * 結果を格納するマップ
	 */
	private Map<String, Object> result;
	
	/**
	 * 状態ノード
	 */
	private Map<String, String> status;
	
	public JsonResponse(){
		status = new HashMap<String, String>();
		result = new HashMap<String, Object>();
		result.put("status", status);
		setAttributesMap(result);
	}
	
	/**
	 * レスポンスステータス
	 * @param status
	 * @return
	 */
	public String setStatus(String status){
		return this.status.put("status", status);
	}
	
	public String setStatusCode(String statusCode){
		return status.put("statusCode", statusCode);
	}
	public String setMsg(String msg){
		return status.put("msg", msg);
	}
	public <O> O setData(Object data){
		Map<String, Object> result = getAttributesMap();
		result.put("data", data);
		return (O) data;
	}
}
