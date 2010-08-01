package cn.loveapple.service.cool.service.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slim3.datastore.Datastore;
import org.springframework.transaction.annotation.Transactional;

import cn.loveapple.service.cool.pojo.SampleModel;
import cn.loveapple.service.cool.pojo.SampleModelMeta;
import cn.loveapple.service.cool.service.SampleService;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * ロジック処理を行うサンプルのサービスインターフェイスの実装クラス(coolクラス)
 * 
 * @author hao_shunri
 *
 */
@Transactional(readOnly=true)
public class SampleServiceImpl implements SampleService {

	/**
	 * ログ
	 */
	private static Logger log = Logger.getLogger(SampleServiceImpl.class.getName());
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SampleModel newAndPut(String name,Float latitude, Float longitude, Double accuracy, String detail) {
		
		SampleModel model = queryByGeoPt(latitude.floatValue(), longitude.floatValue(), name);
		if(model != null){
			if(log.isLoggable(Level.FINEST)){
				log.finest("location has ben added. ");
			}
			model.setUpdateDate(new Date());
			return putData(model);
		}

		if(log.isLoggable(Level.FINEST)){
			log.finest("add location. ");
		}
		
		model = new SampleModel();
		
		GeoPt location = new GeoPt(latitude.floatValue(), longitude.floatValue());
		model.setName(name);
		model.setDetail(detail);
		model.setAccuracy(accuracy);
		model.setLocation(location);
		model.setInsertDate(new Date());
		
		return putData(model);
	}
	
	/**
	 * Bigtableにデータを送信する
	 * 
	 * @param model
	 * @return
	 */
	private SampleModel putData(SampleModel model){
		if(log.isLoggable(Level.FINEST)){
			log.finest("put samplemodel: [" + model + "]");
		}
		Key key = Datastore.put(model);
		model.setKey(key);
		return model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SampleModel> queryAll() {

		if(log.isLoggable(Level.FINEST)){
			log.finest("queryAll. ");
		}
		
		return Datastore.query(SampleModelMeta.get()).asList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SampleModel> queryAllByName(String name){
		if(name == null){
			throw new IllegalArgumentException("name is empty.");
		}
		if(log.isLoggable(Level.FINEST)){
			log.finest("queryAllByName. name:[" + name + "]");
		}
		SampleModelMeta meta = SampleModelMeta.get();
		return Datastore.query(meta).filter(meta.name.equal(name)).asList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SampleModel queryByGeoPt(float latitude, float longitude, String name){
		if(log.isLoggable(Level.FINEST)){
			log.finest("queryByGeoPt. name:[" + name + "]");
		}
		SampleModelMeta meta = SampleModelMeta.get();
		return Datastore.query(SampleModel.class)
			.filter(meta.location.equal(new GeoPt(latitude, longitude)),
					meta.name.equal(name)).asSingle();
	}

	@Override
	public SampleModel queryByKey(long keyId) {
		Key key = KeyFactory.createKey("SampleModel", keyId);
		SampleModelMeta meta = SampleModelMeta.get();
		return Datastore.query(SampleModel.class)
			.filter(meta.key.equal(key)).asSingle();
	}

}
