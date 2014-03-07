<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>
<%@page import="java.util.Date"%>
<%@ page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home | Egunon Euskadi</title>

</head>
<body>
	<header> <c:set var="hoy" value="<%=new Date() %>"></c:set> <fmt:formatDate
		value="${hoy}" pattern="dd-MM-yyyy HH:mm" /> <%@include
		file="mensaje.jsp"%>
	<h3></h3>
	<h3></h3>
	<h3></h3>

	<div id="user_login2">
		<c:if test="${!empty sessionScope.login }">
			<span>Usuario: ${sessionScope.login.nombre}</span>
			<br>
			<span>Ultima visita: ${sessionScope.login.ultimaConexion}</span>
			<br>
			<span>Hora de conexión: ${sessionScope.login.conexion}</span>
			<br>
			<span><a href="logout">Desconectar</a></span>
			<br>
		</c:if>
		<c:if test="${empty sessionScope.login }">
			<a href="login.jsp">Logeate</a>
		</c:if>
	</div>

	<div id="fecha" align="right">
		<%java.util.Date date=new java.util.Date();
	    pageContext.setAttribute("date",date);%>


	</div>

	<nav>
	<ol>
		<li><a href="egunon">Ongi Etorri</a></li>
		<li><a href="egunon?izena=Manola&abizena= Guisasola">Ongi
				Etorri Manola</a></li>
		
		<c:if test="${!empty sessionScope.login }">
		    <li><a href="alumno">Lista Alumno</a></li> 
		</c:if>
		
	
		
	</ol>
	</nav> </header>

	

	<%
//Esto es un bloque de Scriptlet o codigo JAva
out.println("<span>Esto es un Syso en JSP</span>");

//for usando el out de JspWriter

for(int i=1;i<=6;i++) {
	out.println("<h"+i+">Egunon Euskadi</h"+i+">");
}

%>

	<footer> </footer>

</body>
</html>