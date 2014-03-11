<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.ipartek.formacion.egunon.bean.Mensaje.TIPO_MENSAJE"%>
<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ page import="java.util.Date"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home | Egunon Euskadi</title>
</head>
<body>



	
    <%@include file ="mensaje.jsp"%>
	
	<h3>Usuario: ${sessionScope.login.nombre} </h3>
	<div id="user_login">
	
	
	
	
		
		<%
			//obtener usuario logeado en session
			UserLogin login = (UserLogin)session.getAttribute("login");
			Mensaje msg = (Mensaje)request.getAttribute("msg");
		    //Message msg = (Message)session.getAttribute("msg");
		    if ( login != null ){
		    	out.print("<span>Usuario:"+login.getNombre()+"</span><br>");
		    	out.print("<span>Ultima visita: "+login.getAnteriorConexion()+"</span><br>");
		    	out.print("<span>Hora conexion: "+login.getConexion()+"</span><br>");
		    	//out.print("<span>"+msg.getMsg()+"</span><br>");
		    	
		    	out.print("<span><a hrep='logout'>Desconectar</span><br>");
		    	 %>
		    	 <form action="logout" method="post">
		        	<input type="submit" value="logout">
		        </form>
		        <%
		    }else{
		    	msg = new Mensaje ("Bienvenido Anonimo. Logeate", 210, TIPO_MENSAJE.INFO);
		    	out.print("<span>"+msg.getMsg()+"</span>");
		        %>
		        	<a href="login.jsp">Logeate</a>
		        <% 
		    }
		%>
	</div>
	<header>
	<nav>
	<ol> 
	<li><a href="egunon">Ongi Etorri</a></li>
	<li><a href="egunon?izena=manola&abizena=gisasola">Ongi Etorri</a></li>
	<li><a href="alumno">Alumnos</a></li>
	</ol>
	</nav>
	</header>

</body>
</html>