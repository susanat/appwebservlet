<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page errorPage="error.jsp" %>
<!DOCTYPE>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home | Egunon Euskadi</title>
</head>

<body>

	<header>
		<div>
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${now}" />
		</div>
		
	
		<%@include file="mensaje.jsp" %>
		<br>
	
		<div id="user_login2">
			<c:if test="${!empty sessionScope.login }">
				<span>Usuario: ${sessionScope.login.nombre}</span><br>
				<%
					UserLogin login = (UserLogin)session.getAttribute("login");
					Date anterior = new Date(login.getAnteriorConexion());
				%>
				<c:set var="anterior" value="<%=anterior%>" />
				<span>Ultima visita: </span><fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${anterior}" /><br>
				<!-- <span>Hora de conexión: ${sessionScope.login.conexion}</span><br>-->
				<%
					Date ultima = new Date(login.getConexion());
					
				%>
				<c:set var="ultima" value="<%=ultima%>" />
				<span>Hora de conexión: </span><fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${ultima}" /><br>
				<span><a href='logout'>Desconectar</a></span><br>
			</c:if>
			<c:if test="${empty sessionScope.login }">
				<a href="login.jsp">Logeate</a>
			</c:if>
		</div>

	
		<nav>
			<ol>
				<!-- Menu publico -->
				<li><a href="egunon">Ongi Etorri</a></li>
				<li><a href="egunon?izena=Manola&abizena=Gisasola">Ongi Etorri Manola</a></li>
				<li><a href="requestinfo">Request info</a></li>
				<li><a href="requestheader">Request headers</a></li>
				<li><a href="requestraram?izena=Manola&abizena=Gisasola">Request parameters</a></li>
				
				<!--  Menu administracion -->
				<c:if test="${!empty sessionScope.login }">
					<li><a href="alumno">Alumnos</a></li>
				</c:if>
			</ol>
		</nav>
	</header>

	<form method="post" action="egunon">
		<label for="izena">Nombre: </label>
		<input type="text" name="izena"/>
		<input type="submit" value="Enviar" />
	</form>


	<% for(int i =1; i<=6; i++){ %>
		<h<%=i%>>Egunon Euskadi</h<%=i%>>
	<% }//end for  %>
		
		
	<% 
		//Esto es un bloque de Scriptlet o codigo Java
		out.println("<span>Esto es un syso en jsp</span>");
		out.println("<hr>");	
		//for usando el out de JspWriter
		 for(int i =1; i<=6; i++){
			 out.println("<h"+i+">Egunon Euskadi</h"+i+">");
		 }
	%>
	
	<footer>
		Ipartek <fmt:formatDate type="date"  value="${now}" /> ©
	</footer>
	
</body>

</html>