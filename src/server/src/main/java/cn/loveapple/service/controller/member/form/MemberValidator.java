package cn.loveapple.service.controller.member.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * TODO
 * 
 * @see http://static.springsource.org/spring/docs/3.1.0.M1/spring-framework-reference/html/validation.html#validation-mvc-jsr303
 * @author hao_shurni
 * @since 2011/02/17
 * @version $Revision$
 */
public class MemberValidator implements Validator {

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(MemberValidator.class);
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberForm.class.equals(clazz);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void validate(Object target, Errors errors) {
		
		MemberForm form = (MemberForm) target;
		if(!form.getPassword().equals(form.getPasswordConfirm())){
			errors.reject("loveappleErrors.invalid", new Object[]{"msg.member.password"}, "");
		}
	}

}
