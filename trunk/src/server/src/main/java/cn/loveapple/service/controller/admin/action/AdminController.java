package cn.loveapple.service.controller.admin.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.Key;

import cn.loveapple.service.cool.service.AdminService;

/**
 * 
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	/**
	 * 管理操作のサービス
	 */
	private AdminService adminService;
	/**
	 * 24時間以内のセッションをDBからクリア
	 * 
	 * @see com.google.apphosting.utils.servlet.SessionCleanupServlet
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cron/sessionCleanup", method = RequestMethod.GET)
	public void _ah_sessioncleanup(HttpServletRequest req,
			HttpServletResponse resp, OutputStream outputStream) throws IOException{
		String numString = req.getParameter("num");
		int limit = numString == null ? 375 : Integer.parseInt(numString);
		List<Key> result = adminService.sessionCleanup(System.currentTimeMillis()-86400000/*24*3600*1000*/, limit);
		resp.setStatus(HttpServletResponse.SC_OK);
		outputStream.write("OK \n".getBytes());
		if(!CollectionUtils.isEmpty(result)){
			for (Key key : result) {
				outputStream.write(ToStringBuilder.reflectionToString(key).getBytes());
				outputStream.write("\n".getBytes());
			}
		}
	}
	/**
	 * 管理操作のサービスを設定します。
	 * @param adminService 管理操作のサービス
	 */
	@Autowired(required=true)
	public void setAdminService(AdminService adminService) {
	    this.adminService = adminService;
	}
}
