<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de error</title>
	<h1>ERROR - Lamentamos las molestias</h1>
	
	<ol>
		<li><b>Causa</b><%=exception.getCause()%></li>
		<li><b>Message</b><%=exception.getMessage()%></li>
		<li><b>Causa</b><textarea rows="25" cols="100">
			<%=exception.getStackTrace().toString()%>
		</textarea></li>
		<image src="images/error.jpg"></image>
		
	</ol>
</head>
<body>

</body>
</html>