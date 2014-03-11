<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>
<%@page import="com.ipartek.pruebas.exception.AlumnoException"%>
<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%

	final String pattern_name = "[A-Za-z\\s-'áéíóúñ]{2,}";

	//Obtener el titulo para la JSP	
	String title = (String) request.getAttribute("title");
	String method = (String) request.getAttribute("method");
	if (title == null) {
		title = "Nuevo Usuario";
		method = "post";
	}
	//Si no existe es nuevo alumno a crear	 
	//Alumno a = ( (request.getAttribute("detalleAlumno")!=null)?(Alumno)request.getAttribute("detalleAlumno"):new Alumno() );
	Alumno a = null;
	boolean nuevo = false;
	if (request.getAttribute("detalleAlumno") == null) {
		a = new Alumno();
		nuevo = true;
	} else {
		a = (Alumno) request.getAttribute("detalleAlumno");
	}
%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=title%> | Egunon Euskadi</title>
</head>
<body>

	<h1><%=title%></h1>

	<a href="alumno">Volver Listado</a>
	<br>
	<br>

	<%@ include file="mensaje.jsp"%>

	<form action="alumno" method="<%=method%>">

		<!--  ID  -->
		<label name="id">ID</label> 
		<input type="text" name="id_show"	value="<%=a.getId()%>" disabled> 
		<input type="hidden"	name="id" value="<%=a.getId()%>"> <br>
		<!--  Name  -->
		<label name="nombre">Nombre</label>
		<input type="text" name="nombre" required placeholder="nombre alumno" pattern="<%=pattern_name %>" value="<%=a.getNombre()%>"> <br>
		<!-- APELLIDO -->
		<label name="id">Apellido</label> 
		<input type="text" name="apellido" required pattern="<%=pattern_name %>"
			value="<%=a.getApellido()%>"> <br>

		<!-- DNI -->
		<label name="id">DNI</label> 
		<input type="text" name="dni" required pattern="[0-9]{8}[A-Z]"
			value="<%=a.getDni()%>"> <br>

		<!-- EDAD -->
		<label name="id">Edad</label> 
		<!--  <input type="number" name="edad" required size="2" min="18" max="99" -->
		 <input type="text" name="edad" required pattern="[0-9]{2}"
			value="<%=a.getEdad()%>"> 
		<br>
		<!-- EMAIL -->
		<label name="id">Email</label> 
		<input type="email" name="email" required
			value="<%=a.getEmail()%>"> <br>

		<%
			if (nuevo) {
		%>
		<input type="submit" name="op"
			value="<%=AlumnoServlet.OP_NUEVO_ALUMNO%>">
		<%
			} else {
		%>
		<input type="submit" name="op"
			value="<%=AlumnoServlet.OP_MODIFICAR_ALUMNO%>"> <input
			type="submit" name="op" value="<%=AlumnoServlet.OP_ELIMINAR_ALUMNO%>"
			onClick="if(!confirm('¿Seguro que deseas eliminar el registro?')){return false;}">
		<%
			}
		%>



	</form>





</body>
</html>