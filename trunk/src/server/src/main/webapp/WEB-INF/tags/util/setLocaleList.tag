<%@tag import="java.util.*"%>
<%@tag import="cn.loveapple.service.util.LocaleUtil" %>
<%@tag import="cn.loveapple.service.controller.pojo.FrontSelectItem"%>
<%@ include file="/WEB-INF/tags/include.tag" %>
<%@ attribute name="languageList" type="java.lang.String" required="false"%>
<%@ attribute name="countryList" type="java.lang.String" required="false"%>
<%@ attribute name="timezoneList" type="java.lang.String" required="false"%>
<%@ variable name-from-attribute="languageList" alias="languageList-result"%>
<%@ variable name-from-attribute="countryList" alias="countryList-result"%>
<%@ variable name-from-attribute="timezoneList" alias="timezoneList-result"%>
<%
if(languageList != null){
	List<FrontSelectItem> languageList = new ArrayList<FrontSelectItem>(LocaleUtil.LANGUAGE_MAP.size());
	for(Map.Entry<String, Locale> entry:LocaleUtil.LANGUAGE_MAP.entrySet()){
		FrontSelectItem item = new FrontSelectItem();
		item.setCode(entry.getKey());
		item.setLabel(entry.getValue().getDisplayCountry(request.getLocale()));
	}
	jspContext.setAttribute("languageList", languageList);
}
%>