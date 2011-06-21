<%@tag import="java.util.*"%>
<%@tag import="cn.loveapple.service.util.LocaleUtil" %>
<%@tag import="cn.loveapple.service.controller.pojo.FrontSelectItem"%>
<%@ include file="/WEB-INF/tags/include.tag" %>
<%@ attribute name="languageList" type="java.lang.String" required="false"%>
<%@ attribute name="countryList" type="java.lang.String" required="false"%>
<%@ attribute name="timezoneList" type="java.lang.String" required="false"%>
<%
SortedSet<Locale> locales = LocaleUtil.LOCALE_SET;
List<FrontSelectItem> list = new ArrayList<FrontSelectItem>(locales.size());
for(Locale locale:locales){
	FrontSelectItem item = new FrontSelectItem();
	//item.setCode(locale.get)
}
%>