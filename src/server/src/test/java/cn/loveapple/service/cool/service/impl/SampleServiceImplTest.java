package cn.loveapple.service.cool.service.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.tester.AppEngineTester;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.loveapple.service.cool.service.impl.SampleServiceImpl;

/**
 * {@linkplain SampleServiceImpl サンプルサービス実装クラス}のUNITテストクラス。
 * 
 * @author hao_shunri
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/app-config.xml" })
public class SampleServiceImplTest  {

	/**
	 * アプリケーションコンテキスト
	 */
	@Inject
	private ApplicationContext applicationContext;
	private SampleServiceImpl service;
	private static AppEngineTester testHelper;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testHelper = new AppEngineTester();
		testHelper.setUp();
		service = applicationContext.getBean(SampleServiceImpl.class);
	}

	/**
	 * 
	 */
	@Test
	public void testNewAndPut_登録結果の確認() {
		
		service.newAndPut("name", 1.1f, 1.2f, 1.0, "detail");
		assertThat(service.queryAll().size(), is(equalTo(1)));
		assertThat(service.queryAllByName("name").size(), is(equalTo(1)));
		assertThat(service.queryByGeoPt(1.1f, 1.2f, "name").getName(), is(equalTo("name")));
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception{
		
	}
}
