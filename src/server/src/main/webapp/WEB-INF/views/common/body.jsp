<%@ include file="../include.jsp" %>
<div class="container">  
	<h1>
		<fmt:message key="welcome.title"/>
	</h1>
	<p>
		Locale = ${pageContext.response.locale}
	</p>
	<hr>	
	<ul>
		<li> <a href="?locale=en_us">us</a> |  <a href="?locale=en_gb">gb</a> | <a href="?locale=es_es">es</a> | <a href="?locale=de_de">de</a> </li>
	</ul>
	<ul>
		<li><a href="sample">@Controller Example</a></li>
	</ul>
</div>