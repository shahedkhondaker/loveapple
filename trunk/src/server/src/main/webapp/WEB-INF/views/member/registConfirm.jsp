<%@ include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript">
<!--
function submit(form, action){
	if(action){
		form.action = action;
	}
	if(form != null){
		form.submit();
	}
}
//-->
</script>
<h1><fmt:message key="msg.member.join"/></h1>

<h3><span><fmt:message key="msg.member.join.input"/></span></h3>
<div>
	<form:form action="/member/registComplete" modelAttribute="memberForm" method="post">
		<fieldset>
			<p>
				<form:label	for="mail" path="mail" cssErrorClass="error"><fmt:message key="msg.member.mail"/></form:label><br/>
				<c:out value="${memberForm.mail}" />
			</p>
			<p>
				<form:label	for="name" path="name" cssErrorClass="error"><fmt:message key="msg.member.name"/></form:label><br/>
				<c:out value="${memberForm.name}" />
			</p>
			<p>
				<form:label	for="password" path="password" cssErrorClass="error"><fmt:message key="msg.member.password"/></form:label><br/>
				******
			</p>
			<p>
				<form:label	for="qqId" path="qqId" cssErrorClass="error"><fmt:message key="msg.member.qqId"/></form:label><br/>
				<c:out value="${memberForm.qqId}" />
			</p>
			<p>
				<form:label	for="qqAuthKey" path="qqAuthKey" cssErrorClass="error"><fmt:message key="msg.member.qqAuthKey"/></form:label><br/>
				<c:out value="${memberForm.qqAuthKey}" />
			</p>
			<p>
				<form:label	for="longitude" path="longitude" cssErrorClass="error"><fmt:message key="msg.member.longitude"/></form:label><br/>
				<c:out value="${memberForm.longitude}" />
			</p>
			<p>
				<form:label	for="latitude" path="latitude" cssErrorClass="error"><fmt:message key="msg.member.latitude"/></form:label><br/>
				<c:out value="${memberForm.latitude}" />
			</p>
			<p>
				<form:label	for="lastAccuracy" path="lastAccuracy" cssErrorClass="error"><fmt:message key="msg.member.lastAccuracy"/></form:label><br/>
				<c:out value="${memberForm.lastAccuracy}" />
			</p>
			<p>
				<a href="#" onclick="javascript:submit($('memberForm'));"><spring:message code="msg.send" /></a>
				<a href="#" onclick="javascript:submit(this.form, '<spring:url value="/member/regist" />');"><spring:message code="msg.back" /></a>
				<a href="#" onclick="javascript:$('memberForm').reset();"><spring:message code="msg.reset" /></a>
			</p>
		</fieldset>
	</form:form>
</div>
