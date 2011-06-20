package cn.loveapple.service.util.service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class LocaleUtil {
	/**
	 * システムサポートする言語
	 */
	public static final Set<String> LANGUAGE_SET;
	/**
	 * システムサポートするロケール
	 */
	public static final Set<Locale> LOCALE_SET;
	/**
	 * システムサポートする国
	 */
	public static final Set<String> COUNTRY_SET;
	
	static{
		Locale[] locales = Locale.getAvailableLocales();
		LOCALE_SET = new HashSet<Locale>(locales.length);
		LANGUAGE_SET = new HashSet<String>(locales.length);
		COUNTRY_SET = new HashSet<String>(locales.length);
		for (Locale locale : locales) {
			LOCALE_SET.add(locale);
			LANGUAGE_SET.add(locale.getLanguage());
			COUNTRY_SET.add(locale.getCountry());
		}
	}
	
	/**
	 * 
	 * @param lang
	 * @return
	 */
	public static boolean isSupportedLanguage(String lang){
		if(StringUtils.isEmpty(lang)){
			return false;
		}
		return LANGUAGE_SET.contains(lang);
	}
}
