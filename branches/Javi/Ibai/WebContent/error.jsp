<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de Error</title>
</head>
<body>
	<h1>Error - Lamentamos las molestias</h1>
	
	<ol>
		<li><b>Causa: </b><%=exception.getCause()%></li>
		<li><b>Mensaje: </b><%=exception.getMessage()%></li>
	</ol>
	
	<textarea rows="25" cols="100">
		<%=exception.getStackTrace()%>
	</textarea>
	
	
</body>
</html>