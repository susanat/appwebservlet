<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <!-- Esto sirve para indicarle la pagina de error a la que tiene que ir si se produce un error -->
     <%@page errorPage="error.jsp" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home | Egunon Euskadi</title>
<link href="css/style.css" rel="stylesheet" type="text/css" >	

</head>
<body>
<%@include file="cabecera.jsp" %>
	<header>
	<%
		//String cadena=null;
		//cadena.toString();
	%>
			
	<div id="user_login">
	<!-- Usando expresiones EL, es lo mismo que poner session.getAttribute
	 -->
	 <!-- en este caso se visualizan el get nombre del formato java 
	  -->
	<div id="user_login">
		<c:if test="${!empty sessionScope.login }">
		<span>Usuario: ${sessionScope.login.nombre}</span>
		<br><span>Ultima visita: ${sessionScope.login.anteriorConexion}</span><br>
		<span>Hora conexión: ${sessionScope.login.conexion}</span>
		<br><a href="logout">Desconectar</a>
		</c:if>
		<c:if test="${empty sessionScope.login} }">
			<a href="login.jsp">Logeate</a>
		</c:if>

	</div>
	
			<%
			// obtener usuario logeado en sesión
			UserLogin login= (UserLogin)session.getAttribute("login");
				
				if(login!=null){
				out.print("<span>Usuario: "+ login.getNombre()+"</span><br>");
				out.print("<span>Ultima visita: "+login.getAnteriorConexion()+"</span><br>");
				out.print("<span>Hora conexión: "+login.getConexion()+"</span><br>");
				out.print("<a href='logout'>Desconectar</a>");
			}else{
				out.print("Anonimo");
		%>
				
					<a href="login">Logeate</a>
		<%
			}
				
		%>
<%@include file="mensaje.jsp" %>		
	
	</div>
	
	<!-- nav sirve para crear un menú de navegación -->
		<nav>
			<ol>
				<!-- Menu público -->
				<li><a href="egunon">Ongi Etorri</a></li>
				<li><a href="egunon?izena=Manola&abizena=Gisasola">Ongi Etorri Manola</a></li>
				<li><a href="requestInfo">Request info</a></li>
				<li><a href="requestHeader">Request Header</a></li>
				<li><a href="requestParameters">Request Parameters</a></li>
				<li><a href="formulario">Formulario</a></li>
				<!-- Menú Administración -->
				<c:if test="${!empty sessionScope.login}" >
					<li><a href="alumno">Alumnos </a></li>
					<li><a href="session">Usuarios Conectados</a></li>
				</c:if>
				
			</ol>
		</nav>
	</header>
	<hr>
	<!-- Para añadir código Java -->
	<% for(int i=1; i<=6; i++){%>
	<h<%=i%>>Egunon Euskadi <%=i %> </h<%=i %>>
	<%	}// End for%>
	
	<%
	// Esto es un bloque de Scriptlet o código Java
	out.println("<span>Esto es un Syso en JSP</span>");
	out.println("<hr>");
	// crear un for usando el out de JSPWriter		
			for(int i=1; i<=6; i++){
				out.println("<h"+ i + "> Egunon Euskadi " + i + " </h"+i+">");
			}
	%>
	<%@include file="footer.jsp" %>
</body>
</html>