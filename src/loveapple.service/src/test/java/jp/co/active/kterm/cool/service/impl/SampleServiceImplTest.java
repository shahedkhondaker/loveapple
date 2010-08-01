package jp.co.active.kterm.cool.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.slim3.tester.AppEngineTester;

import cn.loveapple.service.cool.service.impl.SampleServiceImpl;

/**
 * @author hcl
 *
 */
public class SampleServiceImplTest {

	private SampleServiceImpl service;
	private static AppEngineTester testHelper;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testHelper = new AppEngineTester();
		testHelper.setUp();
		service = new SampleServiceImpl();
	}

	/**
	 * 
	 */
	@Test
	public void testNewAndPut() {
		//service.newAndPut("name", 1.1f, 1.2f, 1.0, "detail");
		//assertThat(service.queryAll().size(), is(equalTo(1)));
		//assertThat(service.queryAllByName("name").size(), is(equalTo(1)));
		//assertThat(service.queryByGeoPt(1.1f, 1.2f, "name").getName(), is(equalTo("name")));
	}
}
