<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    %>
    <%@page import="com.ipartek.pruebas.bean.Alumno"%>
    <%@page import="java.util.ArrayList"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
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
<h1>Listado de todos los Alumnos</h1><br/><br/>

  <a href="detalleAlumno.jsp">Crear Alumno</a>
    
    

	<table summary="Análisis de ventas anuales" id="tabla_alumno">
	  <caption>Cabecera o Caption</caption>
	   <thead>
	    <tr>
	      <th scope="col">Nombre</th>
	      <th scope="col">Apellido</th>
	      <th scope="col">Dni</th>
	      <th scope="col">Edad</th>
	      <th scope="col">Sexo</th>
	      <th scope="col">Email</th>
	      
	    </tr>
	  </thead>
	  <tbody>
	   
	  	<%
			ArrayList <Alumno> lAlumnos = (ArrayList<Alumno>) request.getAttribute("listaAlumnos");	
	  	    Alumno a;
			for ( int i=0; i<lAlumnos.size();i++){
				a=lAlumnos.get(i);
				%>
				  <tr>
			     	 <td><%=a.getNombre()%></td>
			    	  <td><%=a.getApellido()%></td>
			    	  <td><%=a.getDni()%></td>
			    	  <td><%=a.getEdad()%></td>
			    	  <td><%=a.getSexo()%></td>
			    	  <td><%=a.getEmail()%></td>
			    	  
			    	  <td><a href="alumno?id=<%=a.getId()%>">detalle</a></td>
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