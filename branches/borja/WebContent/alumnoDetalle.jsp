<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>
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
<title><%=title%>|Egunon Euskadi</title>
</head>
<body>
	<h1><%=title%></h1>

	<a href="alumno">Volver Listado</a>
	<br>

	<form action="alumno" method="<%=method%>">

		<!--  ID del alumno -->
		<label name="id">ID</label> <input type="text" name="id_show"
			value="<%=a.getId()%>" disabled> <input type="hidden"
			name="id" value="<%=a.getId()%>"> <br>

		<!--  Nombre del alumno -->
		<label name="nombre"><span>Nombre</span></label> <span><input
			type="text" name="nombre" required placeholder="nombre alumno"
			pattern="<%=pattern_name%>" value="<%=a.getNombre()%>"></span> <br>

		<!--  Apellido del alumno -->
		<label name="apellido"><span>Apellido</span></label> <span><input
			type="text" name="apellido" required pattern="<%=pattern_name%>"
			value="<%=a.getApellido()%>"></span> <br>

		<!--  DNI del alumno -->
		<label name="dni">DNI</label> <input type="text" name="dni" required
			pattern="[0-9]{8}[A-Z]" value="<%=a.getDni()%>"> <br>

		<!-- Edad del alumno -->
		<label name="edad">Edad</label> <input type="text" name="edad"
			required pattern="[0-9]{2}" value="<%=a.getEdad()%>"> <br>

		<!-- Email del alumno -->
		<label name="email">Email</label> <input type="text" name="email"
			required value="<%=a.getEmail()%>"> <br>
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