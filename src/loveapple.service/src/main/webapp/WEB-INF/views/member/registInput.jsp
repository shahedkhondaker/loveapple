<%@page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Create Account</title>
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/styles/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">	
</head>	
<body>
<div class="container">
	<h1>
		会員登録
	</h1>
	<div class="span-12 last">	
		<form:form action="/member/confirm" modelAttribute="memberForm" method="post">
		  	<fieldset>		
				<legend><fmt:message key="msg.location"/></legend>
				<p>
					<form:label	for="familyName" path="familyName" cssErrorClass="error">familyName</form:label><br/>
					<form:input path="familyName" /> <form:errors path="familyName" />			
				</p>
				<p>	
					<form:label for="firstName" path="firstName" cssErrorClass="error">firstName</form:label><br/>
					<form:input path="firstName" /> <form:errors path="firstName" />
				</p>
				<p>
					<form:label for="nickName" path="nickName" cssErrorClass="error">nickName</form:label><br/>
					<form:input path="nickName" /> <form:errors path="nickName" />
				</p>
				<p>	
					<form:label for="password" path="password" cssErrorClass="error">password</form:label><br/>
					<form:input path="password" /> <form:errors path="password" />
				</p>
				<p>
					<form:label for="sexStr" path="sexStr" cssErrorClass="error">sexStr</form:label><br/>
					<form:input path="sexStr" /> <form:errors path="sexStr" />
				</p>
				<p>	
					<input type="submit" />
				</p>
			</fieldset>
		</form:form>
	</div>
	<hr>	
	<ul>
		<li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
	</ul>	
</div>
</body>
</html>