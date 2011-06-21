package cn.loveapple.service.util.service;

import static cn.loveapple.service.util.LocaleUtil.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocaleUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsSupportedLanguage() {
		assertTrue(isSupportedLanguage("ja"));
		assertFalse(isSupportedLanguage("JA"));
		assertFalse(isSupportedLanguage(null));
	}

}
