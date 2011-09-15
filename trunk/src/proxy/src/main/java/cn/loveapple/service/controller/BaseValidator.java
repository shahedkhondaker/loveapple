package cn.loveapple.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
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
	
	protected List<ErrorMessage> errorMessages;
	
	/**
	 * {@linkplain ReloadableResourceBundleMessageSource メッセージソース}を設定してバリデーターを作成。
	 * 
	 * @param messageSource
	 */
	public BaseValidator(ReloadableResourceBundleMessageSource messageSource, Locale locale){
		this.messageSource = messageSource;
		this.locale = locale;
		this.errorMessages = new ArrayList<ErrorMessage>();
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
	public void validate(Object target, Errors errors){
		for (ErrorMessage message : this.errorMessages) {
			errors.reject(message.getBaseCode(),createArgs(message.getArgs()), "");
		}		
	}
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
	/**
	 * メッセージソースを取得します。
	 * @return メッセージソース
	 */
	public ReloadableResourceBundleMessageSource getMessageSource() {
	    return messageSource;
	}
	/**
	 * メッセージソースを設定します。
	 * @param messageSource メッセージソース
	 */
	public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
	    this.messageSource = messageSource;
	}
	/**
	 * ロケール情報を取得します。
	 * @return ロケール情報
	 */
	public Locale getLocale() {
	    return locale;
	}
	
	/**
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	public boolean addErrorMessage(String code, String ...args){
		if(StringUtils.isEmpty(code)){
			return false;
		}
		return this.errorMessages.add(new ErrorMessage(code, args));
	}

	/**
	 * 
	 * 
	 * @author hao_shunri
	 * @since 2011/02/23
	 * @version $Revision$
	 */
	public class ErrorMessage{
		
		/**
		 * 
		 * @param baseCode
		 * @param args
		 */
		public ErrorMessage(String baseCode, String[] args) {
			super();
			this.baseCode = baseCode;
			this.args = args;
		}
		/**
		 * ベースとなるエラーメッセージコード
		 */
		private String baseCode;
		/**
		 * 引数のコード
		 */
		private String[] args;
		/**
		 * baseCodeを戻します。<br>
		 *
		 * @return baseCode
		 */
		public String getBaseCode() {
			return baseCode;
		}
		/**
		 * baseCodeを設定します。<br>
		 *
		 * @param baseCode
		 */
		public void setBaseCode(String baseCode) {
			this.baseCode = baseCode;
		}
		/**
		 * argsを戻します。<br>
		 *
		 * @return args
		 */
		public String[] getArgs() {
			return args;
		}
		/**
		 * argsを設定します。<br>
		 *
		 * @param args
		 */
		public void setArgs(String[] args) {
			this.args = args;
		}
		
	}
}
