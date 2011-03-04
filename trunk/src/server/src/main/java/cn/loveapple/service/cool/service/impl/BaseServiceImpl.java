package cn.loveapple.service.cool.service.impl;

import java.lang.reflect.Method;

import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.Datastore;

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
		
		return queryByKey(key, obj);
	}
	

	/**
	 * 
	 * TODO 未試験
	 */
	public <O> O queryByKey(Key key, O obj) {
		if(key == null){
			throw new IllegalArgumentException("key is empty.");
		}
		
		StringBuilder metaClass = new StringBuilder(obj.getClass().getName().length());
		metaClass.append(obj.getClass().getName());
		metaClass.append("Meta");
		metaClass.replace(26, 31, "meta");
		
		Method getMethod = null;
		try{
			getMethod = Class.forName(metaClass.toString()).getMethod("get");
		}catch (Exception e) {
			throw new RuntimeException("create meta method class error.", e);
		}
		
		Object meta = null;
		try {
			meta = getMethod.invoke(null);
		} catch (Exception e) {
			throw new RuntimeException("invoke meta method class error.", e);
		}
		
		CoreAttributeMeta keyObj = null;
		try {
			keyObj = (CoreAttributeMeta) meta.getClass().getField("key").get(meta);
		} catch (Exception e) {
			throw new RuntimeException("get key fields error.", e);
		}
		
		return (O) Datastore.query(obj.getClass()).filter(keyObj.equal(key)).asSingle();
	}
}
