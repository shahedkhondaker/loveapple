package cn.loveapple.service.util.web;

import static cn.loveapple.service.controller.Constant.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.xml.internal.ws.resources.HttpserverMessages;

import cn.loveapple.service.controller.Constant;

/**
 * コントローラ、ビューのUTIL。
 * 
 * @author hao_shunri
 *
 */
public class FrontUtil {
	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(FrontUtil.class);
	
	/**
	 * URL文字列を<code>a/b/c</code>の形式に生成する。
	 * @param seqs URL文字列
	 * @return URLを返す
	 */
	public static String createUrlStr(CharSequence...seqs){
		if(seqs == null || seqs.length < 1){
			return "";
		}
		StringBuilder url = new StringBuilder(1024);
		for(int i = 0; i < seqs.length; i++){
			if(seqs[i].length() < 1){
				continue;
			}
			if(i != 0 && seqs[i].charAt(0) != '/'){
				url.append('/');
			}
			url.append(seqs[i]);
		}
		return url.toString();
	}
	/**
	 * URL文字列を<code>redirect:/a/b/c</code>の形式に生成する。
	 * 
	 * @see #createUrlStr(CharSequence...) URLの生成処理を行う。
	 * @param seqs URL文字列
	 * @return リダイレクト用のURLを返す
	 */
	public static String createRedirectUrlStr(CharSequence...seqs){
		String url = createUrlStr(seqs);
		if(url.startsWith("/")){
			return "redirect:" + url;
		}
		return "redirect:/" + url;
	}
	/**
	 * 
	 * {@linkplain HttpServletRequest#getRequestURI() リクエストURL}と{@linkplain HttpServletRequest#getQueryString() クエリ文字列}をもとに、
	 * {@linkplain Constant#APP_URL_HEAD}なしのフルーなURLを生成する。
	 * 
	 * @param request
	 * @return
	 */
	public static String createFullRequestUrl (HttpServletRequest request){
		return createFullRequestUrl(request, APP_URL_HEAD);
	}
	/**
	 * 
	 * {@linkplain HttpServletRequest#getRequestURI() リクエストURL}と{@linkplain HttpServletRequest#getQueryString() クエリ文字列}をもとに、
	 * フルーなURLを生成する。
	 * 
	 * @param request
	 * @param isNeedContextPath
	 * @return
	 */
	public static String createFullRequestUrl (HttpServletRequest request, String deleteStr){
		if(request == null){
			return null;
		}
		StringBuilder requestUrl = new StringBuilder(request.getRequestURI());
		if(StringUtils.isNotEmpty(deleteStr)){
			requestUrl.delete(0, deleteStr.length());
			if(log.isDebugEnabled()){
				log.debug("delete contextPath:" + request.getContextPath() + " requestUrl:" + requestUrl);
			}
		}
		if(StringUtils.isEmpty(request.getQueryString())){
			return requestUrl.toString();
		}
		String queryString = request.getQueryString();
		int requestUrlLength = requestUrl.length();
		int queryStringLength = queryString.length();
		
		StringBuilder fullRequestUrl = new StringBuilder(requestUrlLength + queryStringLength +  1);
		fullRequestUrl.append(requestUrl);
		fullRequestUrl.append('?');
		fullRequestUrl.append(queryString);
		return fullRequestUrl.toString();
	}

	/**
	 * 
	 * @param session
	 * @param attributeName
	 * @return
	 */
	public static boolean hasAttributeInSession(HttpSession session, String attributeName){
		if(session == null){
			return false;
		}
		if(session.getAttribute(attributeName) == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param request
	 * @param attributeName
	 * @return
	 */
	public static boolean hasAttributeInSession(HttpServletRequest request, String attributeName){
		if(request == null){
			return false;
		}
		HttpSession session = request.getSession(false);
		return hasAttributeInSession(session, attributeName);
	}
}
