<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Incluir librerias jquery -->
<script type="text/JavaScript" src="js/jquery.js"></script>
<title>Detalle Alumno</title>
</head>
<body>
	<c:set var="alumno" value='<%=request.getAttribute("detalle")%>' />
	<c:set var="isNombreValid"
		value='<%=request.getAttribute("isNombreValid")%>' />
	<c:set var="isApellidoValid"
		value='<%=request.getAttribute("isApellidoValid")%>' />
	<c:set var="isEdadValid"
		value='<%=request.getAttribute("isEmailValid")%>' />
	<c:set var="isEmailValid"
		value='<%=request.getAttribute("isEdadValid")%>' />
	<c:set var="isDniValid" value='<%=request.getAttribute("isDniValid")%>' />
	<c:set var="title" value='<%=request.getAttribute("title")%>' />

	<h1>"${title} | Egunon Euskadi"</h1>

	<a href="alumno">Volver al Listasdo</a>

	<form id="formulario" action="alumno" method="post"">

		<!-- ID -->
		<label name="id">ID</label> <input type="text" name="idN" disabled
			value="${(alumno.id >= 0) ? alumno.id:0}"> <br> <input
			type="hidden" name="id" value="${alumno.id}">

		<!-- Name -->
		<label name="nombre">Nombre</label> <input type="text" name="nombre"
			value="${(isNombreValid) ? alumno.nombre:''}"> <br>

		<!-- Apellido -->
		<label name="apellido">Apellido</label> <input type="text"
			name="apellido" value="${(isApellidoValid) ? alumno.apellido:''}">
		<br>

		<!-- Edad -->
		<label name="edad">Edad</label> <input type="text" name="edad"
			value="${(isEdadValid) ? alumno.edad:''}"> <br>

		<!-- Email -->
		<label name="email">Email</label> <input type="text" name="email"
			value="${(isEmailValid) ? alumno.email:''}"> <br>

		<!-- DNI -->
		<label name="dni">DNI</label> <input type="text" name="dni"
			value="${(isDniValid) ? alumno.dni : ''}"> <br>

		<c:if test="${alumno.id >= 0 }">
			<input type="submit" name="op"
				value="<%=AlumnoServlet.OP_MODIFICAR_ALUMNO%>"
				onClick="if(!confirm('¿Seguro que deseas modificar el registro?')){return false;}">
			<input type="submit" name="op"
				value="<%=AlumnoServlet.OP_ELIMINAR_ALUMNO%>"
				onClick="if(!confirm('¿Seguro que deseas eliminar el registro?')){return false;}">
		</c:if>
		<c:if test="${empty alumno || alumno.id == -1}">
			<input type="submit" name="op"
				value="<%=AlumnoServlet.OP_NUEVO_ALUMNO%>">
		</c:if>
	</form>
	<script type="text/javascript">
		// 	$(document).ready(function() {
		function onSubmit(form) {
			var formData = new FormData(form);
			console.log("form.name " + formData.name);
			console.log("form.toString() " + formData);
			console.log("form.target.value " + form.target.value);
			return false;
			if (form.name == "op"
					&& form.value ==
	<%=AlumnoServlet.OP_MODIFICAR_ALUMNO%>
		)
				return confirm("¿Desea modificar el alumno ${alumno.id}?");
			else if (form.name == "op"
					&& form.value ==
	<%=AlumnoServlet.OP_ELIMINAR_ALUMNO%>
		)
				return confirm("¿Desea borrar el alumno?");
			else
				//si no es ninguna de las opciones anteriores se manda un true, como que se ha aceptado el onSubmit
				return true;
		}
		// 	});
	</script>
</body>
</html>