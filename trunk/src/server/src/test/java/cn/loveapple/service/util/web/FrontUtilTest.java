/**
 * 
 */
package cn.loveapple.service.util.web;

import static cn.loveapple.service.util.web.FrontUtil.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author hcl
 *
 */
public class FrontUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link cn.loveapple.service.util.web.FrontUtil#createUrlStr(java.lang.CharSequence[])}.
	 */
	@Test
	public void testCreateUrlStr() {
		assertEquals("a/b/c", createUrlStr("a", "b", "c"));
		assertEquals("/a/b/c", createUrlStr("/a", "b", "c"));
		assertEquals("a/b/c", createUrlStr("a", "/b", "c"));
		assertEquals("/a/b/c", createUrlStr("/a", "/b", "c"));
		assertEquals("/a/b/c", createUrlStr("/a", "/b", "/c"));
		assertEquals("a/b/c", createUrlStr("a", "/b", "/c"));
		assertEquals("", createUrlStr(new CharSequence[]{}));
		assertEquals("", createUrlStr(null));
		assertEquals("a/b/d/c", createUrlStr("a", "/b/d", "/c"));
	}

	/**
	 * Test method for {@link cn.loveapple.service.util.web.FrontUtil#createRedirectUrlStr(java.lang.CharSequence[])}.
	 */
	@Test
	public void testCreateRedirectUrlStr() {
		assertEquals("redirect:/a/b/c", createRedirectUrlStr("a", "b", "c"));
		assertEquals("redirect:/a/b/c", createRedirectUrlStr("/a", "b", "c"));
		assertEquals("redirect:/", createRedirectUrlStr(null));
		assertEquals("redirect:/", createRedirectUrlStr(new CharSequence[]{}));
	}

	@Test
	public void testCreateFullRequestUrlHttpServletRequest(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/app/abc");
		assertEquals("/app/abc", createFullRequestUrl(request, null));
		assertEquals("/abc", createFullRequestUrl(request, "/app"));
	}
}
