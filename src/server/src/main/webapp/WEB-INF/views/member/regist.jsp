<%@ include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript">
<!--
function encryptPassword(form){
	if(form.password.value){
		if(form.password.value.length < 6 || 20 < form.password.value.length ){
		}else{
			data = utf16to8(form.password.value);
			form.password.value = MD5_hexhash(data);
		}
	}
	if(form.passwordConfirm.value){
		if(form.passwordConfirm.value.length < 6 || 20 < form.passwordConfirm.value.length ){
		}else{
			data = utf16to8(form.passwordConfirm.value);
			form.passwordConfirm.value = MD5_hexhash(data);
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
<h1><fmt:message key="msg.member.join"/></h1>

<h3><span><fmt:message key="msg.member.join.input"/></span></h3>
<div>
	<form:form action="/member/registConfirm" modelAttribute="memberForm" method="post">
		<fieldset>
			<form:errors messages="*"  cssErrorClass="error" />
			<p>
				<form:label	for="mail" path="mail" cssErrorClass="error"><fmt:message key="msg.member.mail"/></form:label><br/>
				<form:input path="mail" /><form:errors path="mail" />
			</p>
			<p>
				<form:label	for="name" path="name" cssErrorClass="error"><fmt:message key="msg.member.name"/></form:label><br/>
				<form:input path="name" /><form:errors path="name" />
			</p>
			<p>
				<form:label	for="password" path="password" cssErrorClass="error"><fmt:message key="msg.member.password"/></form:label><br/>
				<form:password path="password" /><form:errors path="password" />
			</p>
			<p>
				<form:label	for="passwordConfirm" path="passwordConfirm" cssErrorClass="error"><fmt:message key="msg.member.passwordConfirm"/></form:label><br/>
				<form:password path="passwordConfirm" /><form:errors path="passwordConfirm" />
			</p>
			<p>
				<form:label	for="qqId" path="qqId" cssErrorClass="error"><fmt:message key="msg.member.qqId"/></form:label><br/>
				<form:input path="qqId" /><form:errors path="qqId" />
			</p>
			<p>
				<form:label	for="qqAuthKey" path="qqAuthKey" cssErrorClass="error"><fmt:message key="msg.member.qqAuthKey"/></form:label><br/>
				<form:input path="qqAuthKey" /><form:errors path="qqAuthKey" />
			</p>
			<p>
				<form:label	for="longitude" path="longitude" cssErrorClass="error"><fmt:message key="msg.member.longitude"/></form:label><br/>
				<form:input path="longitude" /><form:errors path="longitude" />
			</p>
			<p>
				<form:label	for="latitude" path="latitude" cssErrorClass="error"><fmt:message key="msg.member.latitude"/></form:label><br/>
				<form:input path="latitude" /><form:errors path="latitude" />
			</p>
			<p>
				<form:label	for="lastAccuracy" path="lastAccuracy" cssErrorClass="error"><fmt:message key="msg.member.lastAccuracy"/></form:label><br/>
				<form:input path="lastAccuracy" /><form:errors path="lastAccuracy" />
			</p>
			<p>
				<a href="#" onclick="javascript:submit($('memberForm'));"><spring:message code="msg.confirm" /></a>
				<a href="#" onclick="javascript:$('memberForm').reset();"><spring:message code="msg.reset" /></a>
			</p>
		</fieldset>
	</form:form>
</div>
