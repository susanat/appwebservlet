<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>
<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    	//Obtener un titulo de la jsp
    	String title = (String)request.getAttribute("title");
    	String method = (String)request.getAttribute("method");
    	Alumno alumno = (Alumno)request.getAttribute("detalleALumno");
	   	if(title == null){
	    	title ="Nuevo Usuario";
	    	method = "post";
	    }
	   	boolean nuevo = false;
	   	if (alumno == null){
	   		alumno = new Alumno();
	   		nuevo = true;
	   	}
    %>
    
<!DOCTYPE">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><%=title %> | Egunon Euskadi</title>
</head>

<body>
	<a href="alumno">Volver</a>
	<h1><%=title %></h1>
	<%@include file="mensaje.jsp" %>

	<form action="alumno" method="<%=method%>">
		<label name="id">ID</label>
		<input type="text" name="idvisible" disabled value="<%=alumno.getId()%>"></input>
		<input type="hidden" name="id" value="<%=alumno.getId()%>"></input>
		<br>
		<label name="dni">DNI</label>
		<input type="text" name="dni" value="<%=alumno.getDni()%>"></input>
		<br>
		<label name="nombre">Nombre</label>
		<input type="text" name="nombre" value="<%=alumno.getNombre()%>"></input>
		<br>
		<label name="apellido">Apellido</label>
		<input type="text" name="apellido" value="<%=alumno.getApellido()%>"></input>
		<br>
		<label name="edad">Edad</label>
		<input type="text" name="edad" value="<%=alumno.getEdad()%>"></input>
		<br>
		<label name="email">Email</label>
		<input type="text" name="email" value="<%=alumno.getEmail()%>"></input>
		<br>
		<% if (nuevo){ %>
			<input type="submit" name="action" value="<%=AlumnoServlet.OP_NUEVO_ALUMNO%>">
		<% } else { %>
			<input type="submit" name="action" value="<%=AlumnoServlet.OP_MODIFICAR_ALUMNO%>">
			<input type="submit" name="action" value="<%=AlumnoServlet.OP_ELIMINAR_ALUMNO%>"  
			onClick="if(!confirm('¿Seguro que deseas eliminar el registro?')){return false;}" >
		<% } %>
		
	</form>
	
</body>
</html>