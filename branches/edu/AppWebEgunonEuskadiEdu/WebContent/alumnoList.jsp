<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    <%@page import="com.ipartek.pruebas.bean.Alumno"%>
    <%@page import="java.util.ArrayList"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Incluir JQuery y data table -->
<script src="js/jquery.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="js/css/jquery.dataTables.css">
</head>
<body>
<h1>Listado de todos los Alumnos</h1>

	<table summary="Análisis de ventas anuales" id="tabla_alumno">
	  <caption>Cabecera o Caption</caption>
	   <thead>
	    <tr>
	      <th scope="col">Nombre</th>
	      <th scope="col">Apellido</th>
	      <th scope="col">Dni</th>
	      <th scope="col">Operaciones</th>
	    </tr>
	  </thead>
	  <tbody>
	   
	  	<%
			ArrayList <String> lAlumnos = (ArrayList<String>) request.getAttribute("listaAlumnos");		
			for ( int i=0; i<lAlumnos.size();i++){
				%>
				  <tr>
			     	 <td><%=lAlumnos.get(i)%></td>
			    	  <td>Garibolo</td>
			   	 	  <td>022741254</td>
			   	 	  <td><a href="alumno?id=<%=i%>">detalle</a></td>
			    </tr>   
				<%
			
			}
			
		
		%>
	  
	  
	  
	  </tbody>
	</table>
	<script>
		//comentari de linea
		/*Esto e sun comentario bloque */
		$(document).ready(function(){
		  console.debug('Esto es un mensaje debug');
		  console.info('Esto es un mensaje info');
		  console.error('Esto es un mensaje error');
		  //cargar libreria o pluging jquery
		  $('#tabla_alumno').dataTable();
		});
	
	</script>
</body>
</html>