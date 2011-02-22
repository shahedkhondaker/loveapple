package cn.loveapple.service.controller;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * フォームビーン妥当性チェック用のベースValidator
 * 
 * @author hao_shunri
 * @since 2011/02/22
 * @version $Revision$
 */
public abstract class BaseValidator implements Validator {

	/**
	 * メッセージソース
	 */
	private ReloadableResourceBundleMessageSource messageSource;
	
	/**
	 * ロケール情報
	 */
	private Locale locale;
	
	/**
	 * {@linkplain ReloadableResourceBundleMessageSource メッセージソース}を設定してバリデーターを作成。
	 * 
	 * @param messageSource
	 */
	public BaseValidator(ReloadableResourceBundleMessageSource messageSource, Locale locale){
		this.messageSource = messageSource;
		this.locale = locale;
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public abstract boolean supports(Class<?> clazz);
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public abstract void validate(Object target, Errors errors);
	
	/**
	 * メッセージに設定する引数を{@linkplain ReloadableResourceBundleMessageSource メッセージソース}から
	 * 適切な内容をを生成して返す。
	 * 
	 * @param argCodes 引数メセージのキー
	 * @return 生成したメッセージ配列
	 */
	public Object[] createArgs(String ...argCodes ){
		if(argCodes == null){
			return null;
		}
		if(argCodes.length == 0){
			return argCodes;
		}
		Object[] result = new Object[argCodes.length];
		for (int i = 0; i < argCodes.length; i++) {
			result[i] = messageSource.getMessage(argCodes[i], null, locale);
		}
		
		return result;
	}

}
