<%@page contentType="text/html;charset=UTF-8"  isELIgnored="false"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>@Controller Example</title>
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/styles/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
	<link rel="stylesheet" href="<c:url value="/styles/blueprint/print.css" />" type="text/css" media="print">	
	<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAffXEJ44AAft-2GxO0Y7AUBRNPnV-9zclWMGZxJcZgIy-mRpIYhTSZWxeWvkYUPy5xudgTizq3Jyv6A"
            type="text/javascript"></script> 
    <script type="text/javascript"> 
    function initialize() {
      var map;
      if (GBrowserIsCompatible()) {
      	var mapOptions = {
      		googleBarOptions : {
      			style : "new"
      		}
      	}
        map = new GMap2(document.getElementById("map_canvas"), mapOptions);
        map.setCenter(new GLatLng(35.664036,139.698212), 13);
        var polyline = new GPolyline([
		<c:forEach varStatus="status" items="${locationList}" var="location">
			new GLatLng(${location.location})
			<c:if test="${status ne last}">,</c:if>
		</c:forEach>
		], "#ff0000", 10);
		map.addOverlay(polyline);
		
		map.setUIToDefault();
        map.enableGoogleBar();
      }
    }  
    </script> 
</head>	
<body onload="initialize()" onunload="GUnload()">
<div class="container">
	<h1>
		<fmt:message key="msg.viewLocation"/>
	</h1>
	<div class="span-12 last">	
		<form:form modelAttribute="sampleForm" method="post">
		  	<fieldset>
				<legend><fmt:message key="msg.location"/></legend>
				<p>
					<form:label	for="name" path="name" cssErrorClass="error">Name</form:label><br/>
					<form:input path="name" /> <form:errors path="name" />			
				</p>
				<p>	
					<form:label for="latitude" path="latitude" cssErrorClass="error">latitude</form:label><br/>
					<form:input path="latitude" /> <form:errors path="latitude" />
				</p>
				<p>
					<form:label for="longitude" path="longitude" cssErrorClass="error">longitude</form:label><br/>
					<form:input path="longitude" /> <form:errors path="longitude" />
				</p>
				<p>	
					<form:label for="accuracy" path="accuracy" cssErrorClass="error">accuracy</form:label><br/>
					<form:input path="accuracy" /> <form:errors path="accuracy" />
				</p>
				<p>
					<form:label for="detail" path="detail" cssErrorClass="error">detail</form:label><br/>
					<form:input path="detail" /> <form:errors path="detail" />
				</p>
				<p>	
					<input type="submit" />
				</p>
			</fieldset>
		</form:form>
	</div>
	<hr>
	<div id="map_canvas" style="width: 500px; height: 300px"></div> 
    <div id="message"></div> 
	<c:forEach items="${locationList}" var="location">
		${location.name}:${location.location} - ${location.accuracy} @ ${location.updateDate}<br>
	</c:forEach>
	
	<ul>
		<li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
	</ul>	
</div>
</body>
</html>