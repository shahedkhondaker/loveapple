/**
 * 
 */
package cn.loveapple.service.controller.sample.form;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.loveapple.service.controller.sample.form.SampleForm;

import static org.junit.Assert.*;

/**
 * JSR303のテストケースのサンプル。
 * 
 * サンプル：http://musingsofaprogrammingaddict.blogspot.com/2009/01/getting-started-with-jsr-303-beans.html
 * 
 * @author hao_shunri
 *
 */
public class SampleFormTest {

	private static Validator validator;
	private SampleForm form;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		form = createForm();
	}
	
	private SampleForm createForm(){
		SampleForm form = new SampleForm();
		form.setAccuracy(0.1);
		form.setDetail("詳細");
		form.setLatitude(1.1f);
		form.setLongitude(1.1f);
		form.setName("name");
		return form;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link cn.loveapple.service.controller.sample.form.SampleForm#setLatitude(java.lang.Float)}.
	 */
	@Test
	public void testLatitude_正常() {
		form.setLatitude(6f);
		Set<ConstraintViolation<SampleForm>> constraintViolations = validator.validate(form);
		
		assertEquals(0, constraintViolations.size());
	}
	/**
	 * 
	 */
	@Test
	public void testLatitude_異常_空の場合() {
		form.setLatitude(null);
		Set<ConstraintViolation<SampleForm>> constraintViolations = validator.validate(form);
		System.out.println(constraintViolations);
		
		assertEquals(1, constraintViolations.size());
		
		for (ConstraintViolation<SampleForm> constraintViolation : constraintViolations) {
			// 属性latitudeに対しての妥当性チェックでこけていることを確認
			assertEquals("latitude", constraintViolation.getPropertyPath().toString());
			// NotNullに設定したメッセージのキーを確認する
			assertEquals("{javax.validation.constraints.NotNull.message}", constraintViolation.getMessageTemplate());
			// フォームに設定した値を確認する
			assertEquals(null, form.getLatitude());
		}
	}

}
