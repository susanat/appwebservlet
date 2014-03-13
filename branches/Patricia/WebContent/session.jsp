<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="js/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>
<script src="js/jquery.dataTables.js"></script>
<title>Sesión | Egunon Euskadi</title>
</head>
<body>
	<%@include file="cabecera.jsp"%>
	<a id="volver" href="index.jsp"><img src="flecha_azul.jpg">Volver</a>
	<h1>Información de usuarios en Sesión</h1>
	<!-- tabla de información de los usuarios en sesion -->
	<%
		//ArrayList<UserLogin> usuariosSesion= (ArrayList<UserLogin>)session.getAttribute("listaSession");
		Set<UserLogin>  usuariosSesion= (Set<UserLogin>) session.getServletContext().getAttribute("listaSession");
		
		out.print("<table summary='informacion de sesión' id='tabla_sesion'>");
		out.print("<caption>Información Usuarios Sesión</caption>");
			out.print("<thead>");
		out.print("<th>Id</th>");
		out.print("<th>Nombre</th>");
		out.print("<th>DNI</th>");
		out.print("<th>Fecha conexión</th>");
		out.print("<th>Tiempo expiración</th>");
		out.print("<th>Ultima conexión</th>");
			out.print("</thead>");
			
			out.print("<tbody>");
			
			if(usuariosSesion!=null){
				for(UserLogin usuarioSesion : usuariosSesion){
					out.print("<tr>");
					out.print("<td>"+usuarioSesion.getId()+"</td>");
					out.print("<td>"+usuarioSesion.getNombre()+"</td>");
					out.print("<td>"+usuarioSesion.getPassword()+"</td>");
					SimpleDateFormat sdf1= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					out.print("<td>"+sdf1.format(new Date(usuarioSesion.getConexion()))+"</td>");
					SimpleDateFormat sdf2= new SimpleDateFormat("hh:mm:ss");
					
					out.print("<td>"+sdf2.format(new Date(usuarioSesion.getExpireTime()))+"</td>");
					out.print("<td>"+sdf1.format(new Date(usuarioSesion.getAnteriorConexion()))+"</td>");
					out.print("</tr>");
				}
			}
			
			out.print("</tbody>");
			out.print("</table>");
			out.print("<br><br>");
		
	%>


</body>
<br><br>
<%@include file="footer.jsp" %>
<script>
	$(document).ready(function() {
		$('#tabla_sesion').dataTable();
	});
</script>
</html>
