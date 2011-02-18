<%@ include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript" src="/javascript/md5.js"></script>
<script type="text/javascript">
<!--
function encryptPassword(form){
	if(form.password.value != null){
		data = utf16to8(form.password.value);
		form.password.value = MD5_hexhash(data);
	}
}
//-->
</script>
<h1><fmt:message key="msg.member.login"/></h1>

<h3><span><fmt:message key="msg.member.login.input"/></span></h3>
<div>
	<form:form action="/member/auth" modelAttribute="MemberAuthForm" onsubmit="encryptPassword(this)" method="post">
		<fieldset>
			<p>
				<form:label	for="loginId" path="loginId" cssErrorClass="error"><fmt:message key="msg.member.login.id"/></form:label><br/>
				<form:input path="loginId" /> <form:errors path="loginId" />	
			</p>
			<p>
				<form:label	for="password" path="password" cssErrorClass="error"><fmt:message key="msg.member.login.password"/></form:label><br/>
				<form:password path="password" /> <form:errors path="password" />	
			</p>
			<p>
				<input type="submit" />
			</p>
		</fieldset>
	</form:form>
</div>