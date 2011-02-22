package cn.loveapple.service.cool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.loveapple.service.cool.service.AdminService;
import cn.loveapple.service.type.Service;

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
 * @since 2011/02/16
 * @version $Revision$
 */
@Service
public class AdminServiceImpl implements AdminService {

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(AdminServiceImpl.class);

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Key> sessionCleanup(long time, int limit) {
		Query query = new Query("_ah_SESSION");
		query.addFilter("_expires", FilterOperator.LESS_THAN, time);
		query.setKeysOnly();
		ArrayList<Key> killList = new ArrayList<Key>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		PreparedQuery pq = datastore.prepare(query);
		Iterable<Entity> entities = pq.asIterable(FetchOptions.Builder
				.withLimit(limit));
		for (Entity expiredSession : entities) {
			Key key = expiredSession.getKey();
			killList.add(key);
		}

		try {
			datastore.delete(killList);
		} catch (DatastoreTimeoutException e) {
			log.warn("DatastoreTimeoutException on" + killList.size()
					+ "expired sessions.", e);
			return null;
		}
		return killList;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public List<Key> sessionCleanup(long time) {
		return sessionCleanup(time, DELETE_SESSION_LIMIT);
	}

}
