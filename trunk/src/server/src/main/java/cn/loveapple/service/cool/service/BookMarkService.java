package cn.loveapple.service.cool.service;

import java.util.List;

import cn.loveapple.service.cool.model.LoveappleBookMarkModel;
import cn.loveapple.service.cool.model.LoveappleBookMarkTagModel;

public interface BookMarkService extends BaseService{
	/**
	 * ブックマークを登録する
	 * @param bookmark
	 * @return
	 */
	public LoveappleBookMarkModel insertBookMark(LoveappleBookMarkModel bookmark);
	
	/**
	 * ブックマークを更新する
	 * @param bookmark
	 * @return
	 */
	public LoveappleBookMarkModel updateBookMark(LoveappleBookMarkModel bookmark);
	
	/**
	 * ブックマークのタグを登録する
	 * @param bookmarkTag
	 * @return
	 */
	public LoveappleBookMarkTagModel insertBookMarkTag(LoveappleBookMarkTagModel bookmarkTag);
	
	/**
	 * ブックマークのタグを更新する
	 * @param bookmarkTag
	 * @return
	 */
	public LoveappleBookMarkTagModel updateBookMarkTag(LoveappleBookMarkTagModel bookmarkTag);
	
	/**
	 * タグを元にブックマークを検索する
	 * @param mail
	 * @param tags
	 * @return
	 */
	public List<LoveappleBookMarkModel> findBookMarkByTag(String mail, int startPoint, int endPoint, String... tags);
}
