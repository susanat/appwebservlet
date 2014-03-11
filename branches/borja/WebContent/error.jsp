<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de error</title>
</head>
<body>
<h1>Error - Lamentamos las molestias</h1>
	<ol>
	<li><b>Causa </b><%=exception.getCause() %></li>
	<li><b>Message </b><%=exception.getMessage() %></li>
	
	</ol>
	<textarea rows="30" cols = "50"><%=exception.fillInStackTrace() %>
	</textarea>
	
	

</body>
</html>