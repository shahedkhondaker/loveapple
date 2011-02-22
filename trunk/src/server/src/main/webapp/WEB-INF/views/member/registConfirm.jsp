<%@ include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript" src="/javascript/md5.js"></script>
<script type="text/javascript">
<!--
function encryptPassword(form){
	if(form.password.value){
		data = utf16to8(form.password.value);
		form.password.value = MD5_hexhash(data);
	}
	return form;
}
function submit(form, action){
	form = encryptPassword(form);
	if(action){
		form.action = action;
	}
}
//-->
</script>
<h1><fmt:message key="msg.member.join"/></h1>

<h3><span><fmt:message key="msg.member.join.input"/></span></h3>
<div>
	<form:form action="/member/registComplete" modelAttribute="memberForm" onsubmit="submit(this)" method="post">
		<fieldset>
			<p>
				<form:label	for="loginId" path="loginId" cssErrorClass="error"><fmt:message key="msg.member.id"/></form:label><br/>
				<c:out value="${memberForm.loginId}" />
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
				<form:label	for="mail" path="mail" cssErrorClass="error"><fmt:message key="msg.member.mail"/></form:label><br/>
				<c:out value="${memberForm.mail}" />
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
				<input type="submit" />
				<input type="button" onclick="submit(this.form, '<spring:url value="/member/regist" />')" value="<spring:message code="msg.back" />" />
				<input type="reset"/> 
			</p>
		</fieldset>
	</form:form>
</div>
