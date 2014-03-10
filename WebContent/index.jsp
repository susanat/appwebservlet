<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE"%>
<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home | Egunon Euskadi</title>
</head>
<%
// 		String value = null;
// 		value.toString();
	%>
<body>
	<header>
		<%@include file="mensaje.jsp"%>
		<div>
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<span><fmt:formatDate pattern="yyyy-MM-dd" value="${now}" /></span>
		</div>
		<div id="user_login">
			<c:if test="${!empty sessionScope.login}">
				<span>Usuario: ${sessionScope.login.nombre}</span>
				<br />

				<span>Ultima visita: ${sessionScope.login.anteriorConexion}</span>
				<br />
				<span>Hora conexion: ${sessionScope.login.conexion}</span>
				<br />
				<form action="logout" method="post">
					<input type="submit" value="Logout">
				</form>
			</c:if>

			<c:if test="${empty sessionScope.login}">
				<form action="login.jsp" method="post">
					<input type="submit" value="Login">
				</form>
			</c:if>
		</div>
		<nav>
			<ol>
				<!-- Menu publico -->
				<li><a href="egunon">Ongi Etorri</a></li>
				<li><a href="egunon?izena=Manola&abizena=Gisasola">Ongi
						Etorri Manola</a></li>
				<li><a href="requestinfo">RequestInfo</a></li>
				<li><a href="requestheader">RequestHeader</a></li>
				<li><a href="requestparam">RequestParam</a></li>
				<!-- Menu administracion -->
				<c:if test="${!empty sessionScope.login}">
					<li><a href="alumno">Alumnos</a></li>
				</c:if>
			</ol>
		</nav>
	</header>
	<%
		for (int i = 1; i <= 6; i++) {
	%>
	<h <%=i%>>Egunon Euskadi</h<%=i%>>
	<%
		}//end for
	%>


	<%
		//Esto es un bloque de Scriptlet o codigo Java
		out.println("<span>Esto es un syso en jsp</span>");
		out.println("<hr>");
		//for usando el out de JspWriter
		for (int i = 1; i <= 6; i++) {
			out.println("<h" + i + ">Egunon Euskadi</h" + i + ">");
		}
	%>
	<footer>
		Ipartek
		<fmt:formatDate pattern="yyyy" value="${now}" />
		©
	</footer>
</body>

</html>