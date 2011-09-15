<%@tag import="org.apache.commons.lang.StringUtils"%>
<%@tag import="java.util.*"%>
<%@tag import="cn.loveapple.service.util.LocaleUtil" %>
<%@tag import="cn.loveapple.service.controller.pojo.FrontSelectItem"%>
<%@ include file="/WEB-INF/tags/include.tag" %>
<%-- 
 -- システムがサポートする言語、国、タイムゾーンの設定情報セレクトボックスで取り扱えるビーンリストに設定する
 --
 --%>
<%@ attribute name="languageList" type="java.lang.String" rtexprvalue="true" required="false"%>
<%@ attribute name="countryList" type="java.lang.String" rtexprvalue="true" required="false"%>
<%@ attribute name="timezoneList" type="java.lang.String" rtexprvalue="true" required="false"%>
<%
if(StringUtils.isNotEmpty(languageList)){
	List<FrontSelectItem> languageListResult = new ArrayList<FrontSelectItem>(LocaleUtil.LANGUAGE_MAP.size());
	for(Map.Entry<String, Locale> entry:LocaleUtil.LANGUAGE_MAP.entrySet()){
		String label = entry.getValue().getDisplayLanguage(request.getLocale());
		if(StringUtils.isWhitespace(label)){
			continue;
		}
		FrontSelectItem item = new FrontSelectItem();
		item.setCode(entry.getKey());
		item.setLabel(label);
		languageListResult.add(item);
	}
	request.setAttribute(languageList, languageListResult);
}
if(StringUtils.isNotEmpty(countryList)){
	List<FrontSelectItem> countryListResult = new ArrayList<FrontSelectItem>(LocaleUtil.LANGUAGE_MAP.size());
	for(Map.Entry<String, Locale> entry:LocaleUtil.COUNTRY_MAP.entrySet()){
		String label = entry.getValue().getDisplayCountry(request.getLocale());
		if(StringUtils.isWhitespace(label)){
			continue;
		}
		FrontSelectItem item = new FrontSelectItem();
		item.setCode(entry.getKey());
		item.setLabel(label);
		countryListResult.add(item);
	}
	request.setAttribute(countryList, countryListResult);
}
if(StringUtils.isNotEmpty(timezoneList)){
	List<FrontSelectItem> timezoneListResult = new ArrayList<FrontSelectItem>(LocaleUtil.TIMEZONE_MAP.size());
	for(Map.Entry<String, TimeZone> entry:LocaleUtil.TIMEZONE_MAP.entrySet()){
		String label = entry.getValue().getDisplayName(request.getLocale());
		if(StringUtils.isWhitespace(label)){
			continue;
		}
		FrontSelectItem item = new FrontSelectItem();
		item.setCode(entry.getKey());
		item.setLabel(label);
		timezoneListResult.add(item);
	}
	request.setAttribute(timezoneList, timezoneListResult);
}
%>