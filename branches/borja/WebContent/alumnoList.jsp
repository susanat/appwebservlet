<!--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>-->
<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Incluir jquery y datatables -->
<script src= "js/jquery.js"></script>
<script src= "js/dataTable/jquery.dataTables.min.js"></script>

<style type="text/css">
		
	@import "WebContent/css/jquery-ui-1.10.4.custom"

</style>
<link href="jquery-ui-1.10.4.custom.css" type="text/css" />
</head>
<body>
   <h1>Listado de todos los alumnos</h1>
   <a href="alumnoDetalle.jsp" >Crear nuevo alumno</a>
   <table summary="Alumnos matriculados" style="width:300px" id="tabla_alumno">
    <thead>
   		<tr>
  			<th>Nombre</th>
  			<th>Apellido</th>		
  			<th>Edad</th>
 			<th>DNI</th>
  			<th>Email</th>
  			<th>Detalle</th>
  		</tr>
  	</thead>
  	<tbody>
  	<!--
  		<c:forEach var="alumno" items="${requestScope.listAlumnos}">
  			<tr>
  			<td>${alumn.nombreo}</td>
  			<td>${alumno.apellido}</td>
  			<td>${alumno.dni}</td>
  			 <c:out value="${alumno}"/><p>
  			 </td>
  			 </tr>
		</c:forEach>
	-->	

   <%
   		ArrayList<Alumno> IAlumnos = (ArrayList<Alumno>)request.getAttribute("listAlumnos");
   		Alumno a;
   		for (int i = 0; i < IAlumnos.size();i++){
   			a = IAlumnos.get(i);
   			%>
   			<tr>
   				<td>
   					<%=a.getNombre()%>
   				</td>
   				<td>
   					<%=a.getApellido()%>
   				</td>
   				<td>
   					<%=a.getEdad()%>
   				</td>
   				<td>
   					<%=a.getDni()%></a>
   			    </td>
   				
   				<td>
   					<%=a.getEmail()%>
   				</td>
   				<td>
   					<a href="alumno?id=<%=a.getId()%>">Ver</a>
   			    </td>
   			</tr>
   		<% }
   %>
  </tbody>
</table>
<script>
//comentario de linea
/*Espera que el documento de html este totalmente cargado*/
$(document).ready(function(){
    $('#tabla_alumno').dataTable();
});
/*Esto es un comentario de bloque*/

</script>
</body>
</html>