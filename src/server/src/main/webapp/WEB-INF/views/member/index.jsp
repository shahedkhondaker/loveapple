<%@ include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript" src="/javascript/prototype.js"></script>
<script type="text/javascript" src="/javascript/md5.js"></script>
<script type="text/javascript">
<!--
function encryptPassword(form){
	if(form.password.value){
		if(form.password.value.length() < 6 || 20 < form.password.value.length() ){
			$("p.errors").innerHTML="<spring:message code='loveappleErrors.Size' arguments='6,20' argumentSeparator=',' javaScriptEscape='true' />";
			return null;
		}else{
			data = utf16to8(form.password.value);
			form.password.value = MD5_hexhash(data);
		}
	}
	return form;
}

function submit(form, action){
	form = encryptPassword(form);
	if(action){
		form.action = action;
	}
	if(form != null){
		form.submit();
	}
}
//-->
</script>
<h1><fmt:message key="msg.member.login"/></h1>

<h3><span><fmt:message key="msg.member.login.input"/></span></h3>
<div>
	<fieldset>
		<form:form action="/member/auth" modelAttribute="memberAuthForm" method="post">
		<form:errors message="*"  cssErrorClass="error" />
		<p>
			<form:label	for="mail" path="mail" cssErrorClass="error"><fmt:message key="msg.member.mail"/></form:label><br/>
			<form:input path="mail" /><form:errors path="mail" />
		</p>
		<p>
			<form:label	for="password" path="password" cssErrorClass="error"><fmt:message key="msg.member.password"/></form:label><br/>
			<form:password path="password" /><form:errors path="password" /><div id="p.errors" />
		</p>
		</form:form>
		<p>
			<input type="button" onclick="javascript:submit($('memberAuthForm'));" value="<spring:message code="msg.member.login" />" />
			<input type="reset" onclick="javascript:$('memberAuthForm').reset" />
		</p>
	</fieldset>
</div>
