/**
 * 
 */
package cn.loveapple.service.util.web;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.UserService;

/**
 * @author hao_shunri
 *
 */
public class AccountUtil {
	public static String getFacadeUrl(UserService service, HttpServletRequest request){
		String requestUrl = request.getRequestURL().toString();
		if(request.getUserPrincipal() == null){
			return service.createLoginURL(requestUrl);
		}
		return service.createLogoutURL(requestUrl);
	}
	
	/**
	 * 
	 * @return
	 */
	public static String genCertificationCode(){
		Random random = new Random();
		return Long.toHexString(random.nextLong()) + Long.toHexString(random.nextLong());
	}
}
