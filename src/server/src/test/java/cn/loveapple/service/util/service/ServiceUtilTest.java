/*
 * $HeadURL$
 * $Author$
 * $Revision$
 * $Date$
 *
 * ====================================================================
 *
 * Copyright (C) 2008 by loveapple.sourceforge.jp
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
package cn.loveapple.service.util.service;

import static cn.loveapple.service.util.service.ServiceUtil.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slim3.datastore.ModelMeta;

import cn.loveapple.service.cool.meta.LoveappleMemberModelMeta;
import cn.loveapple.service.cool.model.LoveappleMemberModel;
import cn.loveapple.service.cool.model.LoveappleModel;

/**
 * @author $author:$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class ServiceUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link cn.loveapple.service.util.service.ServiceUtil#getModelMeta(java.lang.String)}.
	 */
	@Test
	public void testGetModelMeta() {
		
		ModelMeta<LoveappleMemberModelMeta> meta = getModelMeta(LoveappleMemberModel.class.getName());
		assertNotNull(meta);
		assertEquals(LoveappleModel.LOVEAPPLE_MEMBER_MODEL, meta.getKind());
		assertEquals(1, getMetastorage().size());
		getModelMeta(LoveappleMemberModel.class.getName());
		assertEquals(1, getMetastorage().size());
		
		
		try{
			getModelMeta("");
			fail();
		}catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try{
			// メタ情報存在しない
			getModelMeta(LoveappleModel.class.getName());
		}catch(Exception e){
			assertTrue(e instanceof RuntimeException);
		}
	}

}
