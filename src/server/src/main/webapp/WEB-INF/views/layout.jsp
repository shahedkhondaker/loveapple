<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale}" lang="${pageContext.response.locale}">
<head>
	<link type="text/css" rel="stylesheet" href="/css/style.css" />
	<meta name="google-site-verification" content="XXpESrFHRzmSA0I-6i_c95oAYEzaKCzLLUAaELnLGOQ" />
	<meta name="author" content="loveapple" />
	<tiles:insertAttribute name="meta" ignore="true" />
</head>
<body>
<div id="page">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody><tiles:insertAttribute name="header" /></tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr valign="top">
			<td style="width: 155px; background: #FFFEFE;"><tiles:insertAttribute name="leftMenu" /></td>
			<td style="padding: 7px" id="main"><div id="body"><tiles:insertAttribute name="body" /></div></td>
			<td style="width: 125px; padding-top: 1px;"><tiles:insertAttribute name="rightMenu" /></td>
		</tr>
	</tbody>
</table>
<tiles:insertAttribute name="footer" />
</div>
</body>
</html>
