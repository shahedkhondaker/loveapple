<%@ include file="/WEB-INF/tags/include.tag" %>
<%@tag import="cn.loveapple.service.controller.SessionLabel;"%>
<c:set var="sessionKey"><%=SessionLabel.LOVEAPPLE_MEMBER%></c:set>
<c:choose>
	<c:when test="${empty sessionScope[sessionKey]}" >
		<a href="/member/regist"><fmt:message key="msg.member.join"/></a>
		<a href="/member"><fmt:message key="msg.member.login"/></a>
	</c:when>
	<c:otherwise>
		<a href="/member/core/info/${sessionScope[sessionKey]['key']['id']}">${sessionScope[sessionKey]['name']}</a>
		<a href="/member/logout"><spring:message code="msg.member.logout" /></a>
	</c:otherwise>
</c:choose>