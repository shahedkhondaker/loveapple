/**
 * 
 */
package cn.loveapple.service.interceptor.front.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Google会員向けコンテンツ(URL:/core/*)のインターセプタ、Googleの認証処理を行う。
 * 
 * @author hao_shunri
 *
 */
public class GoogleAuthInterceptor extends HandlerInterceptorAdapter {

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(GoogleAuthInterceptor.class);
	
	/**
	 * VIEWが返された後、実行する。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("[afterCompletion]check google account status.");
	}

	/**
	 * コントローラが実行された後、実行する。
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("[postHandle]check google account status.");
	}

	/**
	 * コントローラが実行される直前、実行する。<br>
	 * Googleアカウントのログイン状態を確認し、まだログインしていない状態の場合、Googleのログイン画面へリダイレクトする。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.info("[preHandle]check google account status.");
		return true;
	}

}
