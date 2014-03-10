<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Crear Alumno | Egunon Euskadi</title>
<link href="css/style.css" rel="stylesheet" type="text/css" >
<!-- Incluir jquery y datatables -->

</head>
<body>
<%@include file="cabecera.jsp" %>
	
	<a class="derecha" id="volver" href="alumno" ><img src="flecha_azul.jpg">Volver</a><br>
	<br>
	<h1>Crear alumno nuevo</h1>
		
	<br>
	
	<%@include file="mensaje.jsp" %>
	
	<form method="POST" action="alumno">
		<!-- ID -->
		
				
		<%
			String nombre=(request.getAttribute("nombre")!=null)?(String)request.getAttribute("nombre"):"";
			String apellido=(request.getAttribute("apellido")!=null)?(String)request.getAttribute("apellido"):"";
			String dni=(request.getAttribute("dni")!=null)?(String)request.getAttribute("dni"):"";
			String email=(request.getAttribute("email")!=null)?(String)request.getAttribute("email"):"";
			String edad=(request.getAttribute("edad")!=null)?(String)request.getAttribute("edad").toString():"0";
		
		%>
		
		<!-- Nombre -->
		<label name="id">Nombre</label>
		<input class="modificar" type="text" name="nombre" value="<%=nombre %>" />
		<br>
		<label name="apellido">Apellido</label>
		<input class="modificar" type="text" name="apellido" value="<%=apellido %>" />
		<br>
		<label name="dni">DNI</label>
		<input class="modificar" type="text" name="dni" value="<%=dni %>" />
		<br>
		<label name="email">Email</label>
		<input class="modificar" type="text" name="email" value="<%=email %>" />
		<br>
		<label name="edad">Edad</label>
		<input class="modificar" type="text" name="edad" value="<%=edad %>" />
		<br><br>
		
		<input type="submit" name="create" value="Crear" />
		<input type="submit" name="cancel" value="Cancelar" />
		<!--<input type="submit" name="update" value="Modificar" />
		  <input type="submit" name="delete" value="Borrar" />-->
	</form>
<%@include file="footer.jsp" %>	
</body>
</html>

