<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
  
    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home | Egun on Euskadi</title>
</head>
<body>
	
	
	<header>
	
		<%@include file="mensaje.jsp" %>	
			
		<c:set var="hoy" value="<%=new Date()%>"></c:set>
		<fmt:formatDate value="${hoy}" pattern="dd-MM-yyyy HH:mm"/>	
	
		<div id="user_login">	
			<c:if test="${!empty sessionScope.login}">		
				<span>Usuario: ${sessionScope.login.nombre}</span><br>					
				<span>Ultima visita: ${sessionScope.login.anteriorConexion} </span><br>
				<span>Hora conexión: ${sessionScope.login.conexion}</span><br>
				<span><a href="logout">Desconectar</a></span><br>
			</c:if>
			
			<c:if test="${empty sessionScope.login}">
				<a href="login.jsp">Logeate</a>
			</c:if>
		</div>
	
		
	
		
	
		<nav>
			<ol>
				<!-- Menu publico--> 
				<li><a href="egunon">Ongi Etorri</a></li>
				<li><a href="egunon?izena=manola&abizena=Guisasola">Ongi Etorri Manola</a></li>
				
				<!-- Menu Administracion-->
				<c:if test="${!empty sessionScope.login}">
					<li><a href="alumno">Alumnos</a></li>
				</c:if>	
			</ol>
		</nav>
	</header>

	
	<% for(int i=1; i<=6; i++) { %>
		<h<%=i%>>Egunon Euskadi <%=i%> </h<%=i%>>
	<% }//end for %>






</body>
</html>