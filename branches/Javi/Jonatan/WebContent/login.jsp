<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE"%>
<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
</head>
<body>
	<h1>Login AppWeb Egunon Euskadi</h1>
	<%@include file="mensaje.jsp"%>

	<form action="login" method="post">

		<input type="text" name="user_login" size="20"
			placeHolder="Nombre Usuario" required
			pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{4,20}$"
			value="${cookie['cName'].value}"> <br /> <input
			type="password" required=""
			pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" value=""
			name="pass_login"
			placeholder="Password Usuario (Mahus, minus, numerico)"> <br />

		<label name="recuerdame">Recordar usuario</label>
		<c:if test="${!empty cookie['cName']}">
			<input type="checkbox" name="recuerdame" checked>
		</c:if>
		<c:if test="${empty cookie['cName']}">
			<input type="checkbox" name="recuerdame">
		</c:if>

		<br /> <input type="submit" value="login">
	</form>

</body>
</html>