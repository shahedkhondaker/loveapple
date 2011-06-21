package cn.loveapple.service.util;

import java.util.Locale;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

public class LocaleUtil {
	/**
	 * システムサポートする言語
	 */
	public static final SortedMap<String, String> LANGUAGE_MAP;
	/**
	 * システムサポートするロケール
	 */
	public static final SortedSet<Locale> LOCALE_SET;
	/**
	 * システムサポートする国
	 */
	public static final SortedMap<String, String> COUNTRY_MAP;
	
	static{
		Locale[] locales = Locale.getAvailableLocales();
		LOCALE_SET = new TreeSet<Locale>();
		LANGUAGE_MAP = new TreeMap<String, String>();
		COUNTRY_MAP = new TreeMap<String, String>();
		for (Locale locale : locales) {
			LOCALE_SET.add(locale);
			LANGUAGE_MAP.put(locale.getISO3Language(), locale.getLanguage());
			COUNTRY_MAP.put(locale.getISO3Country(), locale.getCountry());
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
