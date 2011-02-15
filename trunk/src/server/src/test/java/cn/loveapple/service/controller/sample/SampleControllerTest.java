/**
 * 
 */
package cn.loveapple.service.controller.sample;

import static junit.framework.Assert.*;

import java.util.Map;

import javax.inject.Inject;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import cn.loveapple.service.controller.sample.action.SampleController;
import cn.loveapple.service.controller.sample.form.SampleForm;

/**
 * {@linkplain SampleController サンプルコントローラ}のテストクラス。 TODO 作成中
 * 
 * @author hao_shunri
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/app-config.xml" })
public class SampleControllerTest {

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(SampleControllerTest.class);
	/**
	 * アプリケーションコンテキスト
	 */
	@Inject
	private ApplicationContext applicationContext;
	/**
	 * リクエスト
	 */
	private MockHttpServletRequest request;
	/**
	 * レスポンス
	 */
	private MockHttpServletResponse response;
	/**
	 * コントローラのハンドラーアダプター
	 */
	private HandlerAdapter handlerAdapter;
	
	/**
	 * テスト対象のコントローラ
	 */
	private SampleController controller;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = applicationContext.getBean(SampleController.class);
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		
		Map<String, HandlerAdapter> adapters = applicationContext.getBeansOfType(HandlerAdapter.class);
		for (Map.Entry<String, HandlerAdapter> entry : adapters.entrySet()) {
			if(log.isDebugEnabled()){
				log.debug(entry.getKey() + ":" + entry.getValue().getClass().getName());
			}
			if(entry.getValue() instanceof AnnotationMethodHandlerAdapter){
				handlerAdapter = entry.getValue();
				break;
			}
		}
		if(log.isDebugEnabled()){
			log.debug(handlerAdapter);
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link cn.loveapple.service.controller.sample.action.SampleController#create(MemberForm.co.active.kterm.controller.sample.SampleForm, org.springframework.validation.BindingResult)}
	 * .
	 */
	@Test
	public void testGetCreateForm() throws Exception{
		
		request.setRequestURI("/sample");
		request.setMethod("GET");
		final ModelAndView mav = handlerAdapter.handle(request, response, 
		           controller);
		
		System.out.println(mav);
		
		assertEquals("sample/createForm", mav.getViewName());
		
		SampleForm form = (SampleForm) mav.getModelMap().get("sampleForm");
		assertEquals("anyone", form.getName());
		
	}

}
