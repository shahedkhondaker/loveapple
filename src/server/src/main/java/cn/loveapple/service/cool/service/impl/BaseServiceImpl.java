package cn.loveapple.service.cool.service.impl;

import static cn.loveapple.service.util.service.ServiceUtil.*;

import org.slim3.datastore.CoreAttributeMeta;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.ModelMeta;

import cn.loveapple.service.cool.model.LoveappleModel;

import com.google.appengine.api.datastore.Key;


public class BaseServiceImpl {

	/**
	 * TODO 未試験
	 * 
	 * @param member
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <O> O dmLoveappleMember(LoveappleModel model) {
		
		Key key = Datastore.put(model);
		return (O) queryByKey(key, model.getClass());
	}
	

	/**
	 * TODO 未試験
	 * 
	 * @param <O> 検索結果の{@linkplain LoveappleModel モデル}を戻す。
	 * @param key 検索キー
	 * @param clz 検索対象のクラス
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <O> O queryByKey(Key key, Class<O> clz) {
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
