<%@ include file="/WEB-INF/views/common/include.jsp" %>
<h1><fmt:message key="msg.member.login"/></h1>

<h3><span><spring:message code="msg.member.info"/></span></h3>
<div>
	<table>
		<tr>
			<td><spring:message code="msg.member.mail"/></td><td><c:out value="${member.mail}" /></td>
		</tr>
		<tr>
			<td><spring:message code="msg.member.name"/></td><td><c:out value="${member.name}" /></td>
		</tr>
		<tr>
			<td><spring:message code="msg.member.qqId"/></td><td><c:out value="${member.qqId}" /></td>
		</tr>
		<tr>
			<td><spring:message code="msg.member.qqAuthKey"/></td><td><c:out value="${member.qqAuthKey}" /></td>
		</tr>
		<tr>
			<td><spring:message code="msg.member.update"/></td><td><c:out value="${member.updateDate}" /></td>
		</tr>
		<tr>
			<td><spring:message code="msg.member.insert"/></td><td><c:out value="${member.insertDate}" /></td>
		</tr>
		<tr>
			<td><spring:message code="msg.member.lastLoginDate"/></td><td><c:out value="${member.lastLoginDate}" /></td>
		</tr>
		<tr>
		<%-- //TODO サイト情報表示 --%>
			<td></td><td></td>
		</tr>
	</table>
</div>
