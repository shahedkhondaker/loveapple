package cn.loveapple.service.cool.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slim3.datastore.Datastore;

import cn.loveapple.service.cool.meta.MetaInfoModelMeta;
import cn.loveapple.service.cool.model.MetaInfoModel;
import cn.loveapple.service.cool.service.MetaInfoService;
import cn.loveapple.service.type.ServiceComp;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * 
 * @author hao_shunri
 * @since 2011/02/23
 * @version $Revision$
 */
@ServiceComp
public class MetaInfoServiceImpl implements MetaInfoService {

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public MetaInfoModel dmMetaInfo(MetaInfoModel meta) {	
		if(meta == null || StringUtils.isEmpty(meta.getKindName())){
			throw new IllegalArgumentException("meta or kind name is empty.");
		}	
		Key key = Datastore.put(meta);
		
		return queryByKey(key);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public MetaInfoModel findByKind(String kind) {
		if(StringUtils.isEmpty(kind)){
			throw new IllegalArgumentException("kind name is empty.");
		}
		MetaInfoModelMeta meta = MetaInfoModelMeta.get();
		return Datastore.query(MetaInfoModel.class).filter(meta.kindName.equal(kind)).asSingle();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public MetaInfoModel queryByKey(Key key) {
		if(key == null){
			throw new IllegalArgumentException("key is empty.");
		}
		
		MetaInfoModelMeta meta = MetaInfoModelMeta.get();
		return Datastore.query(MetaInfoModel.class).filter(meta.key.equal(key)).asSingle();
	}
}
