<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
	<!--  Incluir librerias jsQuery -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	
	<link type="text/css" rel="stylesheet" href="js/css/jquery.dataTables.css">
	
</head>
<body>
	<h1>Listado de todos los Alumnos</h1>
	
	
	<a href="alumnoDetalle.jsp">Nuevo alumno</a>
	<table id="tabla_alumno">
		<caption>Alumnos</caption>
		<thead>
		<tr>
			<th scope="col">Nombre</th>
			<th scope="col">Apellido</th>
			<th scope="col">DNI</th>
			<th scope="col">Operaciones</th>
		</tr>
		</thead>
		
		<tbody>	
			<c:forEach var="alumno" items="${requestScope.listaAlumnos}">
				<tr>
				<td>${alumno.nombre}</td>
				<td>${alumno.apellido}</td>
				<td>${alumno.dni}</td>
				<td><a href='alumno?id=${alumno.id}'>Ver detalle</a></td>
				</tr>
			</c:forEach>		
		</tbody>
	</table>
	

	<script type="text/JavaScript">
		$(document).ready(function() {
			console.debug('Estoy funcionando');
		    $('#tabla_alumno').dataTable();
		} );
	</script>
	
</body>


</html>