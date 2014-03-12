<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logeo</title>
</head>
<body>

	<h1>Login AppWeb Egunon Euskadi</h1>

	<%@include file="mensaje.jsp" %>	
	
	
	<%  
		/*
		//recuperar Cookies del usuario
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        out.println(cookie);
		     }
		}
		*/
		
	%>
	
	
	
	
	
	

	<form action="login" method="post">
	
		<input type="text" name="user_login" size="40" placeholder="Nombre Usuario (minimo 6)" required
			  value="${cookie['cName'].value}"	pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{2,20}$">
		<br>		
		
		<input type="password" name="pass_login" size="40" placeholder="Documento de Identidad D.N.I"
			    value="${cookie['cPass'].value}"	pattern="[0-9]{8}[a-zA-Z]" required >
		<br>		
		
		<label name="recuerdame">Recordar usuario</label>
		
		<c:if test="${!empty cookie['cName']}">		
			<input type="checkbox" name="recuerdame" checked >
		</c:if>	 
		
		<c:if test="${empty cookie['cName']}">		
			<input type="checkbox" name="recuerdame"  >
		</c:if>
		
		<br>
		<input type="submit" value="Login">
	
	</form>


</body>
</html>