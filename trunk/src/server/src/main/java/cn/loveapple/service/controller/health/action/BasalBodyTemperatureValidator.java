package cn.loveapple.service.controller.health.action;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Errors;

import cn.loveapple.service.controller.BaseValidator;
import cn.loveapple.service.controller.health.form.BasalBodyTemperatureForm;
import cn.loveapple.service.util.DateUtil;

/**
 * TODO
 * 
 * @see http://static.springsource.org/spring/docs/3.1.0.M1/spring-framework-reference/html/validation.html#validation-mvc-jsr303
 * @author hao_shurni
 * @since 2011/02/17
 * @version $Revision$
 */
public class BasalBodyTemperatureValidator extends BaseValidator {
		
	/**
	 * 
	 * @param messageSource
	 */
	public BasalBodyTemperatureValidator(ReloadableResourceBundleMessageSource messageSource, Locale locale){
		super(messageSource, locale);
	}

	/**
	 * ログ
	 */
	private static Log log = LogFactory.getLog(BasalBodyTemperatureValidator.class);
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return BasalBodyTemperatureForm.class.equals(clazz);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		
		BasalBodyTemperatureForm form = (BasalBodyTemperatureForm) target;
		
		if(StringUtils.isNotEmpty(form.getMeasureDay())){
			if(! DateUtil.isDateStr(form.getMeasureDay(), DateUtil.DATE_PTTERN_YYYYMMDD)){
				errors.reject("loveappleErrors.invalid", createArgs("msg.bbt.measureDay"), "");
			}
		}
	}

}
