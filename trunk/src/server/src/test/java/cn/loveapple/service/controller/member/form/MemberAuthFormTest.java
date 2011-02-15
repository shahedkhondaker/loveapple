package cn.loveapple.service.controller.member.form;


import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.loveapple.service.controller.member.form.MemberAuthForm;

/**
 * {@linkplain MemberAuthForm}のテストクラス
 * 
 * @author hao_shunri
 * @since 2011/02/15
 * @version $Revision$
 */
public class MemberAuthFormTest {
	private static Validator validator;
	private MemberAuthForm form;
	

	/**
	 * 初期化を行う
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		form = new MemberAuthForm();
		form.setLoginId("loginId");
		form.setPassword("12345678901234567890123456789012");
	}


	/**
	 * ログインIDの妥当性チェックを確認
	 */
	@Test
	public void testLoginId() {
		Set<ConstraintViolation<MemberAuthForm>> constraintViolations = validator.validate(form);
		assertEquals(0, constraintViolations.size());
		
		form.setLoginId("12345678901234567890123456");
		constraintViolations = validator.validate(form);
		assertEquals(1, constraintViolations.size());
		form.setLoginId("");
		constraintViolations = validator.validate(form);
		assertEquals(1, constraintViolations.size());
		form.setLoginId(null);
		constraintViolations = validator.validate(form);
		assertEquals(1, constraintViolations.size());
	}
	/**
	 * ログインIDの妥当性チェックを確認
	 */
	@Test
	public void testPassword() {
		Set<ConstraintViolation<MemberAuthForm>> constraintViolations = validator.validate(form);
		assertEquals(0, constraintViolations.size());
		
		form.setPassword("123456789012345678901234567890123");
		constraintViolations = validator.validate(form);
		assertEquals(1, constraintViolations.size());

		form.setPassword("1234567890123456789012345678901");
		constraintViolations = validator.validate(form);
		assertEquals(1, constraintViolations.size());
		form.setPassword(null);
		constraintViolations = validator.validate(form);
		assertEquals(1, constraintViolations.size());
		
	}
}
