<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%Alumno alumn = (Alumno) request.getAttribute("detalleAlumno");
if(alumn != null ) {
%>
<title>Detalle Alumno | Egunon Euskadi</title>
<%} else { %>

<title>Nuevo Alumno | Egunon Euskadi</title>
<%} %>
</head>
<body>

	<%if(alumn != null ) {
	%>
		<h1>Detalle Alumno</h1>
	<%} else { 
		alumn = new Alumno();
		%>
		<h1>Nuevo Alumno</h1>
	<%} %>
	<!-- MOSTRAR MENSAJE -->
	<div id="mensaje">
		<%@include file="mensaje.jsp"%>
	</div>
		
	
		<form action="alumno" method="post">
			<table border="1">
				<%--
				if(alumn != null ) {
				--%>
				<tr>
				<!-- ID -->
					<td><label name="id">ID</label></td>
					<td><input type="text" name="id" disabled value="<%=alumn.getId()%>"></td>
					<td><input type="hidden" name="id" value="<%=alumn.getId()%>"></td>
				</tr>
				<tr>
				<!-- NOMBRE -->
					<td><label name="id">Nombre</label></td>
					<td><input type="text" name="nombre" value="<%=alumn.getNombre()%>"></td>
				</tr>
				<tr>
				<!-- APELLIDO -->
					<td><label name="id">Apellido</label></td>
					<td><input type="text" name="apellido" value="<%=alumn.getApellido()%>"></td>
				</tr>
				<tr>
				<!-- DNI -->
					<td><label name="id">DNI</label></td>
					<td><input type="text" name="dni" value="<%=alumn.getDni()%>"></td>
				</tr>
				<tr>
				<!-- EDAD -->
					<td><label name="id">Edad</label></td>
					<td><input type="text" name="edad" value="<%=alumn.getEdad()%>"></td>
				</tr>
				<!-- EMAIL -->
					<td><label name="id">Email</label></td>
					<td><input type="text" name="email" value="<%=alumn.getEmail()%>"></td>
				</tr>
				<tr>
				<%if(alumn != null ) {%>
					<td align="center" colspan="2"><input type="submit" value=<%=AlumnoServlet.OP_MODIFICAR_ALUMNO%> name="op">
					<input type="submit" value=<%=AlumnoServlet.OP_BORRAR_ALUMNO%> name="op" onClick="{if(!confirm('Estás seguro de que deseas eliminar el registro?')){return false;}}"></td>
				<%} else { %>
					<td colspan="2" align="center"><input type="submit" value=<%=AlumnoServlet.OP_NUEVO_ALUMNO%> name="op"></td>
				<%} %>
				</tr>
			<%--
			// si el parametro ID está vació, mostramos los campos vacíos y el botón crear.
			} else {
			
				<tr>
				<!-- ID -->
					<td align="right"><label name="id">ID</label></td>
					<td><input type="text" name="id" disabled></td>
				</tr>
				<tr>
				<!-- NOMBRE -->
					<td align="right"><label name="id">Nombre</label></td>
					<td><input type="text" name="nombre"></td>
				</tr>
				<tr>
				<!-- APELLIDO -->
					<td align="right"><label name="id">Apellido</label></td>
					<td><input type="text" name="apellido"></td>
				</tr>
				<tr>
				<!-- DNI -->
					<td align="right"><label name="id">DNI</label></td>
					<td><input type="text" name="dni"></td>
				</tr>
				<tr>
				<!-- EDAD -->
					<td align="right"><label name="id">Edad</label></td>
					<td><input type="text" name="edad"></td>
				</tr>
				<tr>
				<!-- EMAIL -->
					<td align="right"><label name="id">Email</label></td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value=<%=AlumnoServlet.OP_NUEVO_ALUMNO%> name="op"></td>
				</tr>
		<%--} //close else --%>
			
		</table>
	</form>
	<hr/>
	<div id="volver_anterior">
		<span><a href="alumno"><button>Volver a listado</button></a></span>
	</div>

</body>
</html>
