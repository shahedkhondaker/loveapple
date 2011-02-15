/*
+* ------------------------------------------------------------------------
 * Current-Module: $RCSfile:$
 * Release-Date: $Date$
 * Release-Version: $Revision$
 * First-Created: 2011/02/15 AW hcl
 * Modifier: $Author$
%* ------------------------------------------------------------------------
 * Module-Description:
 *
-* ------------------------------------------------------------------------
 * Change-Log:
$* ------------------------------------------------------------------------*/
package cn.loveapple.service.cool.service.impl;

import static cn.loveapple.service.cool.model.ModelConstant.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.tester.AppEngineTester;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.loveapple.service.cool.model.LoveappleMemberModel;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/app-config.xml" })
public class MemberCoreServiceImplTest {
	/**
	 * テスト対象ロジック
	 */
	private MemberCoreServiceImpl logic;
	
	protected static AppEngineTester testHelper;
	@Inject
	protected ApplicationContext applicationContext;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testHelper = new AppEngineTester();
		testHelper.setUp();
		logic = applicationContext.getBean(MemberCoreServiceImpl.class); 
	}

	/**
	 * Test method for {@link cn.loveapple.service.cool.service.impl.MemberCoreServiceImpl#authenticateLoveappleMember(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAuthenticateLoveappleMember() {
		String loginId = "id";
		String password = "pass";
		try{
			logic.authenticateLoveappleMember(null, password);
			fail();
		}catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		try{
			logic.authenticateLoveappleMember(loginId, null);
			fail();
		}catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
//		LoveappleMemberModel member = logic.findByLoginId(loginId);
//		if(member == null){
//			member = new LoveappleMemberModel();
//			member.setLoginId(loginId);
//			member.setPassword(password);
//		}
//		logic.newAndPut(member);
//		
//		assertNull(logic.authenticateLoveappleMember("none", "none"));
//		LoveappleMemberModel target = logic.authenticateLoveappleMember(loginId, password);
//		assertEquals(loginId, target.getLoginId());
//		assertEquals(password, target.getPassword());
	}

	/**
	 * Test method for {@link cn.loveapple.service.cool.service.impl.MemberCoreServiceImpl#newAndPut(cn.loveapple.service.cool.model.LoveappleMemberModel)}.
	 */
	@Test
	public void testNewAndPut() {

		try{
			logic.newAndPut(null);
			fail();
		}catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * Test method for {@link cn.loveapple.service.cool.service.impl.MemberCoreServiceImpl#findByLoginId(java.lang.String)}.
	 */
	@Test
	public void testFindByLoginId() {
		String loginId = "id";

		try{
			logic.findByLoginId(null);
			fail();
		}catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
//		LoveappleMemberModel member = logic.findByLoginId(loginId);
//		if(member == null){
//			member = new LoveappleMemberModel();
//			member.setLoginId(loginId);
//		}
//		logic.newAndPut(member);
//		assertNull(logic.findByLoginId("none"));
//		LoveappleMemberModel target = logic.findByLoginId(loginId);
//		assertEquals(loginId, target.getLoginId());
	}

	/**
	 * Test method for {@link cn.loveapple.service.cool.service.impl.MemberCoreServiceImpl#queryByKey(java.lang.Long)}.
	 */
	@Test
	public void testQueryByKeyLong() {
		Long id = 1L;
		
		try{
			logic.queryByKey((Long)null);
			fail();
		}catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}

//		LoveappleMemberModel member = logic.queryByKey(id);
//		if(member == null){
//			member = new LoveappleMemberModel();
//			Key key = KeyFactory.createKey(LOVEAPPLE_MEMBER_MODEL, id);
//			member.setKey(key);
//		}
//		logic.newAndPut(member);
//		
//		assertEquals(id.longValue(), logic.queryByKey(id).getKey().getId());
	}

	/**
	 * Test method for {@link cn.loveapple.service.cool.service.impl.MemberCoreServiceImpl#queryByKey(com.google.appengine.api.datastore.Key)}.
	 */
	@Test
	public void testQueryByKeyKey() {

		try{
			logic.queryByKey((Key)null);
			fail();
		}catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

}
