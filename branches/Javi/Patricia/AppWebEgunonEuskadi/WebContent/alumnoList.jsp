<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.pruebas.bean.Alumno" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado Alumnos | Egunon Euskadi</title>
<script src="js/jquery.js"></script>
<script src="js/jquery.dataTables.js"></script>

<link href="css/style.css" rel="stylesheet" type="text/css" />	
<link href="js/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<%@include file="cabecera.jsp" %>
	<h1>Listado de todos los Alumnos</h1>
	
	<a id="nuevo" href="alumnoDetalle.jsp">Crear Nuevo Usuario</a><br>
	<br>
	<%@include file="mensaje.jsp" %>
	<%
		ArrayList<Alumno> lalumnos=	(ArrayList<Alumno>) request.getAttribute("listaAlumnos");
		
		 
		out.print("<table summary='Listado de alumnos' id='tabla_alumno'>");
		out.print("<caption>Listado de Alumnos</caption>");
			out.print("<thead>");
				out.print("<th>Nombre</th>");
				out.print("<th>Apellido</th>");
				out.print("<th>DNI</th>");
				out.print("<th>Email</th>");
				//out.print("<th>Edad</th></tr>");
				out.print("<th>operaciones</th>");
			out.print("</thead>");
			
			%>
		<!-- <tfoot>
	    <tr>
	      <th colspan="5" scope="col">Pie de Página</th>
	    </tr>
	  </tfoot> -->
		<%out.print("<tbody>");
		out.print("<tr>");
		
		%>
		
		<c:forEach var="alumno" items="${requestScope.listaAlumnos}">
			<td><span><c:out value="${alumno.nombre}"/></span></td>
    		<td><span><c:out value="${alumno.apellido}"/></span></td>
    		<td><span><c:out value="${alumno.dni}"/></span></td>
    		<td><span><c:out value="${alumno.email}"/></span></td>
    		<!-- <td><span><c:out value="${alumno.edad}"/></span></td> -->
    		<td><a href='alumno?id=${alumno.id}'><c:out value="detalle"/></a></td>
    		</tr>
    	</c:forEach>
		
		<% 
			/*for(int i=0; i< lalumnos.size(); i++){
				out.print("<tr>");
					out.print("<td>"+lalumnos.get(i).getNombre()+"</td>"); 
					out.print("<td><span>"+ lalumnos.get(i).getApellido()+"</span></td>");
					out.print("<td><span>"+ lalumnos.get(i).getDni()+"</span></td>");
					out.print("<td><span>"+ lalumnos.get(i).getEmail()+"</span></td>");
					out.print("<td><span>"+ String.valueOf(lalumnos.get(i).getEdad())+"</span></td>");
					out.print("<td><a href='alumno?id="+ lalumnos.get(i).getId()+"'>Detalle</a></td>");
				out.print("</tr>");
			}*/
			out.print("</tbody>");
			out.print("</table><br>");
			

			
			%>
	<br>
	<%@include file="footer.jsp" %>
	
	
		<!-- 
		//comentario de linea
		/* Esto es un 
				comentario de bloque */
		/*
			Espera a que el documento html este totalmente cargado
		
		$(document).ready(function(){
			
			// cargar libraria o plugin de datatable
			//console.debug('Esto es un mensaje de DEBUG');
			//console.info('Esto es un mensaje de INFO');
			//console.error('Esto es un mensaje de ERROR');
			
			$('#tabla_alumno').dataTable();
		});*/
		 --> 
	<script>	
		$(document).ready(function(){
		    $('#tabla_alumno').dataTable();
		});	
		
	</script>
	
	
</body>
</html>