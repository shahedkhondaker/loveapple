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
package cn.loveapple.service.controller.health.form;


import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author $Author$
 * @version $Revision$
 * @date $Date$
 * @id $Id$
 *
 */
public class BasalBodyTemperatureFormTest {

	private static Validator validator;
	private BasalBodyTemperatureForm form;
	
	@Before
	public void setUp() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		form = createForm();
	}

	private BasalBodyTemperatureForm createForm(){
		BasalBodyTemperatureForm form = new BasalBodyTemperatureForm();
		form.setCoitusFlg("1");
		form.setDysmenorrheaFlg("1");
		form.setLeukorrhea("1");
		form.setMenstruationFlg("1");
		form.setTemperature(35.4);
		return form;
	}
	
	@Test
	public void testCoitusFlg(){
		form.setCoitusFlg("1");
		Set<ConstraintViolation<BasalBodyTemperatureForm>> constraintViolations = validator.validate(form);
		assertEquals(0, constraintViolations.size());

		form.setCoitusFlg("0");
		constraintViolations = validator.validate(form);
		assertEquals(0, constraintViolations.size());
		
		form.setCoitusFlg("2");
		constraintViolations = validator.validate(form);
		assertTrue(constraintViolations.size() > 0);

		form.setCoitusFlg("01");
		constraintViolations = validator.validate(form);
		assertTrue(constraintViolations.size() > 0);
	}
}
