package cn.loveapple.service.cool.service;

import java.util.List;

import cn.loveapple.service.cool.model.SampleModel;



/**
 * ロジック処理を行うサンプルのサービスインターフェイス
 * 
 * @author hao_shunri
 *
 */
public interface SampleService extends BaseService {
	/**
	 * 新しい位置情報を検索する
	 * 
	 * @param name
	 * @param latitude
	 * @param longitude
	 * @param accuracy 
	 * @param detail
	 * @return
	 */
	public SampleModel newAndPut(String name,Float latitude, Float longitude, Double accuracy, String detail);
	/**
	 * すべての位置情報を検索する
	 * 
	 * @return
	 */
	public List<SampleModel> queryAll();
	/**
	 * ユーザ名を元に位置情報を検索する
	 * @param name
	 * @return
	 */
	public List<SampleModel> queryAllByName(String name);

	/**
	 * 経緯度を元に、DBにある位置情報を検索する。
	 * 
	 * @param latitude
	 * @param longitude
	 * @param name TODO
	 * @return
	 */
	public SampleModel queryByGeoPt(float latitude, float longitude, String name);
	
	/**
	 * キーをもとにデータを検索する
	 * @param keyId
	 * @return
	 */
	public SampleModel queryByKey(long keyId);
}
