<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page isErrorPage="true" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página de error</title>
</head>
<body>
		
		
		<h1> Error- Lamentamos las molestias</h1>
		<div ><img class="error" src="img/error.jpg" ></div>
		<ul>
			<li><b>Causa: </b><span> <%=exception.getCause()%></span></li>
			<li><b>Mensaje: </b><span> <%=exception.getMessage()%></span></li>
			<li><b>Causa: </b> 
				<textarea rows="50" cols="50">
					<%=exception.getStackTrace()%>
				</textarea>
			</li>
		
		</ul>
		
</body>
</html>