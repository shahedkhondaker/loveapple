package cn.loveapple.service.controller.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreTimeoutException;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

/**
 * 
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
@Controller
@RequestMapping(value="/")
public class RootController {

	/**
	 * TODO
	 * @see com.google.apphosting.utils.servlet.SessionCleanupServlet
	 * @param model
	 * @return
	 */
	@RequestMapping(value="_ah_sessioncleanup", method=RequestMethod.GET)
	public void _ah_sessioncleanup(HttpServletRequest req, HttpServletResponse resp, OutputStream outputStream) {
		String numString = req.getParameter("num"); 
        int limit = numString == null ? 375 : Integer.parseInt(numString); 
        Query query = new Query("_ah_SESSION"); 
        query.addFilter("_expires", FilterOperator.LESS_THAN, 
        System.currentTimeMillis()-604800000/*7*24*3600*1000*/); 
        query.setKeysOnly(); 
        ArrayList<Key> killList = new ArrayList<Key>(); 
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery pq = datastore.prepare(query); 
        Iterable<Entity> entities = pq.asIterable(FetchOptions.Builder.withLimit(limit)); 
        for(Entity expiredSession : entities) { 
                Key key = expiredSession.getKey(); 
            killList.add(key); 
        } 

        try { 
                datastore.delete(killList); 
        } catch (DatastoreTimeoutException e) { 

                resp.setStatus(200); 
            try { 
                resp.getWriter().println((new StringBuilder()).append("DatastoreTimeoutException on ").append(killList.size()).append("expired sessions.").toString()); 
            } 
            catch(IOException ex) { } 
            return; 
        } 

        resp.setStatus(200); 
        try { 
            resp.getWriter().println((new StringBuilder()).append("Cleared ").append(killList.size()).append(" expired sessions.").toString()); 
        } 

        catch(IOException ex) { } 
	}
}
