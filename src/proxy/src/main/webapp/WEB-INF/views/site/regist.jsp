<%@ include file="/WEB-INF/views/common/include.jsp" %>
<h1><fmt:message key="msg.site.regist"/></h1>

<h3><span><fmt:message key="msg.site.regist.input"/></span></h3>
<div>
	<%-- 表示用場所関連のリストを作成する --%>
	<util:setLocaleList countryList="countryList" languageList="languageList" timezoneList="timezoneList" />
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
					<form:options items="${languageList}" itemValue="code" itemLabel="label"/>
				</form:select>
				<form:errors path="language" />
			</p>
			<p>
				<form:label	for="timeZone" path="timeZone" cssErrorClass="error"><fmt:message key="msg.timeZone"/></form:label><br/>
				<form:select path="timeZone" >
					<form:options items="${timezoneList}" itemValue="code" itemLabel="label"/>
				</form:select>
				<form:errors path="timeZone" />
			</p>
			<p>
				<form:label	for="description" path="description" cssErrorClass="error"><fmt:message key="msg.description"/></form:label><br/>
				<form:input path="description" /><form:errors path="description" />
			</p>
			<p>
				<form:label	for="logo" path="logo" cssErrorClass="error"><fmt:message key="msg.site.logo"/></form:label><br/>
				<form:input path="logo" /><form:errors path="logo" />
			</p>
			<p>
				<a href="#" onclick="javascript:$('siteForm').submit();"><spring:message code="msg.confirm" /></a>
				<a href="#" onclick="javascript:$('siteForm').reset();"><spring:message code="msg.reset" /></a>
			</p>
		</fieldset>
	</form:form>
</div>
