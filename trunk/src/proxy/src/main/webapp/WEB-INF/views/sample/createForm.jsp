<%@ include file="/WEB-INF/views/common/include.jsp" %>
<script type="text/javascript"><!--
window.onload = function(){
    navigator.geolocation.watchPosition(update);
}
// 位置が検出されたら緯度、経度、誤差と時間を表示
function update(position){
    var lat = position.coords.latitude;
    var lng = position.coords.longitude;
    var acc = position.coords.accuracy;
    document.getElementById("latitude").value = lat;
    document.getElementById("longitude").value = lng;
    document.getElementById("accuracy").value = acc;
}
// -->
</script>
<div class="container">
	<h1>
		<fmt:message key="msg.createLocation"/>
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
	<ul>
		<li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
	</ul>	
</div>