package cn.loveapple.client.android.damtomo.net.bean;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "list")
public class DataList<E> extends ArrayList<E> implements List<E> {
	
	/**
	 * 
	 */
	@Attribute(name="count", required=false)
	private Integer count;
}
