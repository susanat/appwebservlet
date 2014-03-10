<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.ipartek.formacion.egunon.bean.MsgApp"%>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logeo</title>
</head>
<body>
	<h1>Login AppWebEgunon Euskadi</h1>
	<form action="login" method="post">
		
		<input type="text" name="user_login" size="40" placeholder = "Nombre Usuario (minimo 6)"
			value="${cookie['cName'].value}" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{5,20}$" required><br/>
		
		<input type="password" name="pass_login" size="40" placeholder = "Password Usuario (Mayus, Minus y Numero)" 
			value="${cookie['cPass'].value}" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" required><br/>
		
		
		<%@include file="mensaje.jsp"%>
		
		<label name="recuerdame">Recordar usuario</label>
		
		<c:if test="${!empty cookie['cName']}">
			<input type="checkbox" name="recuerdame"  checked>
		</c:if>
		
		<c:if test="${empty cookie['cName']}">
			<input type="checkbox" name="recuerdame">
		</c:if>
		
		<input type="submit" value="Login"> 
		
		
	</form>
	
	
</body>
</html>