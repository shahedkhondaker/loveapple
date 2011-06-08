/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.cn
 *
 * All copyright notices regarding loveapple and loveapple CoreLib
 * MUST remain intact in the scripts, documents and source code.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Correspondence and Marketing Questions can be sent to:
 * info at loveapple
 *
 * @author: loveapple
 */
package cn.loveapple.service.util;

import static org.junit.Assert.*;
import static cn.loveapple.service.util.DateUtil.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class DateUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link cn.loveapple.service.util.DateUtil#paseDate(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testPaseDate() {
		String str = "19800323";
		Date date = paseDate(str, DATE_PTTERN_YYYYMMDD);
		
		assertNotNull(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(1980, calendar.get(Calendar.YEAR));
		assertEquals(3-1, calendar.get(Calendar.MONTH));
		assertEquals(23, calendar.get(Calendar.DATE));
		
		assertNull(paseDate("", DATE_PTTERN_YYYYMMDD));
		assertNull(paseDate(str, ""));
		
	}

	/**
	 * Test method for {@link cn.loveapple.service.util.DateUtil#isDateStr(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testIsDateStr() {
		String str = "19800323";
		assertTrue(isDateStr(str, DATE_PTTERN_YYYYMMDD));
		
		assertFalse(isDateStr("", DATE_PTTERN_YYYYMMDD));
		assertFalse(isDateStr(str, ""));
	}

}
