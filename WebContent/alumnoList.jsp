<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Incluir librerias jquery -->
<script type="text/JavaScript" src="js/jquery.js"></script>
<script type="text/JavaScript" src="js/jquery.dataTables.min.js"></script>
<link href="js/css/jquery.dataTables.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<c:set var="title" value='<%=request.getAttribute("title")%>' />
	<h1>"${title} | Egunon Euskadi" </h1>
	
	<a href="alumnoDetalle.jsp">Crear</a>
	<table id="tabla">
		<caption>Lista de Alumnos</caption>
		<thead>
			<tr>
				<th scope="col">Nombre</th>
				<th scope="col">Apellido</th>
				<th scope="col">DNI</th>
				<th scope="col">Edad</th>
				<th scope="col">Email</th>
				<th scope="col">operaciones</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th colspan="6" scope="col">Pie de pagina</th>
			</tr>
		</tfoot>
		<tbody>
			<ul>
				<c:forEach var="alumno" items="${requestScope.listaAlumnos}">
					<tr>
						<td>${alumno.nombre}</td>
						<td>${alumno.apellido}</td>
						<td>${alumno.dni}</td>
						<td>${alumno.edad}</td>
						<td>${alumno.email}</td>
						<td><a href="alumno?id=${alumno.id}">Ver</td>
					</tr>
				</c:forEach>
			</ul>
		</tbody>
	</table>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#tabla').dataTable();
			console.log("PRbando");
		});
	</script>
</body>

</html>