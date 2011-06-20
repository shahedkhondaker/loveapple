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

function submitForm(form, action){
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
<h1><fmt:message key="msg.site.regist"/></h1>

<h3><span><fmt:message key="msg.site.regist.input"/></span></h3>
<div>
	<form:form action="/site/core/registConfirm" modelAttribute="siteForm" method="post">
		<fieldset>
			<form:errors messages="*"  cssErrorClass="error" />
			<p>
				<form:label	for="unixName" path="unixName" cssErrorClass="error"><fmt:message key="msg.site.unixName"/></form:label><br/>
				<form:input path="unixName" /><form:errors path="unixName" />
			</p>
			<p>
				<form:label	for="name" path="name" cssErrorClass="error"><fmt:message key="msg.site.name"/></form:label><br/>
				<form:input path="name" /><form:errors path="name" />
			</p>
			<p>
				<form:label	for="language" path="language" cssErrorClass="error"><fmt:message key="msg.language"/></form:label><br/>
				<form:select path="language" >
				<!-- TODO 国の一覧 -->
					<form:options items="${countryList}" itemValue="code" itemLabel="name"/>
				</form:select>
				<form:errors path="language" />
			</p>
			<p>
				<form:label	for="timeZone" path="timeZone" cssErrorClass="error"><fmt:message key="msg.timeZone"/></form:label><br/>
				<form:input path="timeZone" /><form:errors path="timeZone" />
			</p>
			<p>
				<form:label	for="description" path="description" cssErrorClass="error"><fmt:message key="msg.description"/></form:label><br/>
				<form:input path="description" /><form:errors path="description" />
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
				<a href="#" onclick="javascript:submitForm($('memberForm'));"><spring:message code="msg.confirm" /></a>
				<a href="#" onclick="javascript:$('memberForm').reset();"><spring:message code="msg.reset" /></a>
			</p>
		</fieldset>
	</form:form>
</div>
