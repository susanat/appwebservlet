<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logeo</title>
</head>
<body>
	<h1>Login AppWeb Egunon Euskadi</h1>
	<%@include file ="mensaje.jsp"%>
	
	<form action="login" method="post">
	<input type="text" name="user_login" size="40" placeholder="Nombre Usuario" value= "${cookie['cName'].value}" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" required>
	<input type="password" name="pass_login" size="40" placeholder="Password Usuario" value= "${cookie['cPass'].value}" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" required>
	<label name="recuerdame">Recuerdame</label>
	<c:if test="${!empty cookie['cName']}"> 
  		
  		<input type="checkbox" name="recuerdame" checked/>
  		
	</c:if>
	<c:if test="${empty cookie['cName']}"> 
  		
  		<input type="checkbox" name="recuerdame" />
  		
	</c:if>
	
	<input type="submit" value="login">
	
	</form>

</body>
</html>