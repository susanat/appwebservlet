<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
	<%@include file="mensaje.jsp" %>
		
	<form action="login" method ="post">
		<input type="text" name="user_login" size="20" placeholder="Nombre Usuario (minimo 6)" required
			value="${cookie['cName'].value}" >
		<br>
		<input type="password" name="pass_login" size="20" placeholder="Documento de Identidad D.N.I"
			value="${cookie['cPass'].value}" pattern="[0-9]{8}[a-zA-Z]" required>
		<br>
		<label name="recuerdame">Recordar usuario</label>
		<c:if test="${!empty cookie['cName'] }">
			<input type="checkbox" name="recuerdame" checked/>
		</c:if>
		<c:if test="${empty cookie['cName'] }">
			<input type="checkbox" name="recuerdame"/>
		</c:if>
		
		<br>
		<input type="submit" value="Login">
	</form>
	
</body>
</html>