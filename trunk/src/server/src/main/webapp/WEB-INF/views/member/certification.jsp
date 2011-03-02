<%@ include file="/WEB-INF/views/common/include.jsp" %>
<h1><spring:message code="msg.member.certification"/></h1>

<div>
	<c:choose>
		<c:when test="${certificationResult}"><spring:message code="msg.member.sucessCertification"/></c:when>
		<c:otherwise><spring:message code="msg.member.failCertification"/></c:otherwise>
	</c:choose>
</div>
