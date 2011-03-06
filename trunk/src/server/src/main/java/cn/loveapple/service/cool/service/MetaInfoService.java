package cn.loveapple.service.cool.service;

import com.google.appengine.api.datastore.Key;

import cn.loveapple.service.cool.model.MetaInfoModel;

/**
 * DBのメタ情報を操作するサービス
 * 
 * @author hao_shunri
 * @since 2011/02/23
 * @version $Revision$
 */
public interface MetaInfoService extends BaseService {
	/**
	 * 
	 * @param meta
	 * @return
	 */
	public MetaInfoModel dmMetaInfo(MetaInfoModel meta);
	
	public MetaInfoModel findByKind(String kind);
	
	public MetaInfoModel queryByKey(Key key);
}
