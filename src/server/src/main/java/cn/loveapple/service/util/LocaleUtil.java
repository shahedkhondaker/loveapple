package cn.loveapple.service.util;

import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

public class LocaleUtil {
	/**
	 * システムサポートする言語
	 */
	public static final SortedMap<String, Locale> LANGUAGE_MAP;
	/**
	 * システムサポートする国
	 */
	public static final SortedMap<String, Locale> COUNTRY_MAP;
	
	static{
		Locale[] locales = Locale.getAvailableLocales();
		LANGUAGE_MAP = new TreeMap<String, Locale>();
		COUNTRY_MAP = new TreeMap<String, Locale>();
		for (Locale locale : locales) {
			LANGUAGE_MAP.put(locale.getLanguage(), locale);
			COUNTRY_MAP.put(locale.getCountry(), locale);
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
		return LANGUAGE_MAP.containsKey(lang);
	}
}
