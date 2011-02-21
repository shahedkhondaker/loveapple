<%@ include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript" src="/javascript/md5.js"></script>
<script type="text/javascript">
<!--
function encryptPassword(form){
	if(form.password.value){
		data = utf16to8(form.password.value);
		form.password.value = MD5_hexhash(data);
	}
	if(form.passwordConfirm.value){
		data = utf16to8(form.password.value);
		form.password.value = MD5_hexhash(data);
	}
}
//-->
</script>
<h1><fmt:message key="msg.member.join"/></h1>

<h3><span><fmt:message key="msg.member.join.input"/></span></h3>
<div>
	<form:form action="/member/registConfirm" modelAttribute="memberForm" onsubmit="encryptPassword(this)" method="post">
		<fieldset>
			<form:errors path="*"  cssErrorClass="error" />
			<p>
				<form:label	for="loginId" path="loginId" cssErrorClass="error"><fmt:message key="msg.member.id"/></form:label><br/>
				<form:input path="loginId" />
			</p>
			<p>
				<form:label	for="password" path="password" cssErrorClass="error"><fmt:message key="msg.member.password"/></form:label><br/>
				<form:password path="password" />	
			</p>
			<p>
				<form:label	for="passwordConfirm" path="passwordConfirm" cssErrorClass="error"><fmt:message key="msg.member.passwordConfirm"/></form:label><br/>
				<form:password path="passwordConfirm" />	
			</p>
			<p>
				<form:label	for="gmailId" path="gmailId" cssErrorClass="error"><fmt:message key="msg.member.gmailId"/></form:label><br/>
				<form:input path="gmailId" />	
			</p>
			<p>
				<form:label	for="gmailPassword" path="gmailPassword" cssErrorClass="error"><fmt:message key="msg.member.gmailPassword"/></form:label><br/>
				<form:password path="gmailPassword" />	
			</p>
			<p>
				<form:label	for="name" path="name" cssErrorClass="error"><fmt:message key="msg.member.name"/></form:label><br/>
				<form:input path="name" />	
			</p>
			<p>
				<form:label	for="qqId" path="qqId" cssErrorClass="error"><fmt:message key="msg.member.qqId"/></form:label><br/>
				<form:input path="qqId" />	
			</p>
			<p>
				<form:label	for="qqAuthKey" path="qqAuthKey" cssErrorClass="error"><fmt:message key="msg.member.qqAuthKey"/></form:label><br/>
				<form:input path="qqAuthKey" />	
			</p>
			<p>
				<form:label	for="longitude" path="longitude" cssErrorClass="error"><fmt:message key="msg.member.longitude"/></form:label><br/>
				<form:input path="longitude" />	
			</p>
			<p>
				<form:label	for="latitude" path="latitude" cssErrorClass="error"><fmt:message key="msg.member.latitude"/></form:label><br/>
				<form:input path="latitude" />	
			</p>
			<p>
				<form:label	for="lastAccuracy" path="lastAccuracy" cssErrorClass="error"><fmt:message key="msg.member.lastAccuracy"/></form:label><br/>
				<form:input path="lastAccuracy" />	
			</p>
			<p>
				<input type="submit" />
				<input type="reset"/> 
			</p>
		</fieldset>
	</form:form>
</div>
