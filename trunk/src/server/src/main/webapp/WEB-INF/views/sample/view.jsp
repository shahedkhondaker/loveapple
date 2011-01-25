<%@ include file="../include.jsp" %>
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