package cn.loveapple.service.cool.service.impl;

import static cn.loveapple.service.util.service.ServiceUtil.*;

import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.ModelMeta;

import com.google.appengine.api.datastore.Key;


public class BaseServiceImpl {

	/**
	 * TODO 未試験
	 * 
	 * @param member
	 * @return
	 */
	private <O> O dmLoveappleMember(O obj) {
		
		Key key = Datastore.put(obj);
		
		return queryByKey(key, obj.getClass());
	}
	

	/**
	 * TODO 未試験
	 * 
	 * @param <O>
	 * @param key
	 * @param clz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <O> O queryByKey(Key key, Class clz) {
		if(key == null){
			throw new IllegalArgumentException("key is empty.");
		}
		
		ModelMeta meta = getModelMeta(clz.getName());
				
		CoreAttributeMeta keyObj = null;
		try {
			keyObj = (CoreAttributeMeta) meta.getClass().getField("key").get(meta);
		} catch (Exception e) {
			throw new RuntimeException("get key fields error.", e);
		}
		
		return (O) Datastore.query(clz).filter(keyObj.equal(key)).asSingle();
	}
}
