<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>
<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<input type="text" name="dni" required pattern="[0-9]{8}[A-Z]" value="<%=alumno.getDni()%>"></input>
		<br>
		<label name="nombre">Nombre</label>
		<input type="text" name="nombre" required pattern="[A-za-z\s-'áéíóúñ]{2,}"  value="<%=alumno.getNombre()%>"></input>
		<br>
		<label name="apellido">Apellido</label>
		<input type="text" name="apellido" required pattern="[A-za-z\s-'áéíóúñ]{2,}" value="<%=alumno.getApellido()%>"></input>
		<br>
		<label name="edad">Edad</label>
		<!-- <input type="number" name="edad" required size="2" min="18" max="99" value="<%=alumno.getEdad()%>"></input>-->
		<input type="text" name="edad" required pattern="[0-9]{2}" value="<%=alumno.getEdad()%>"></input>
		
		<br>
		<label name="email">Email</label>
		<input type="email" name="email" required value="<%=alumno.getEmail()%>"></input>
		<br>
		<br>
		<% if (nuevo){ %>
			<input type="submit" name="action" value="<%=AlumnoServlet.OP_NUEVO_ALUMNO%>">
		<% } else { %>
			<input type="submit" name="action" value="<%=AlumnoServlet.OP_MODIFICAR_ALUMNO%>">
			<input type="submit" name="action" value="<%=AlumnoServlet.OP_ELIMINAR_ALUMNO%>"  
			onClick="if(!confirm('¿Seguro que deseas eliminar el registro?')){return false;}" >
		<% } %>
		
	</form>
	
	<span>Calificaciones</span>
	<br>
	<c:forEach var="calificacion" items="${requestScope.detalleALumn.listaCal}">
		<span>${calificacion.calificacion}</span><br>
	</c:forEach>
	
	
</body>
</html>