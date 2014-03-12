<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Incluir jquery y datatables -->
<script src="js/jquery.js"></script>
<script src="js/jquery.dataTables.min.js"></script>

<link href="js/css/jquery.dataTables.css" rel="stylesheet">


</head>
<body>

	<h1>Listado de todos los Alumnos</h1>

	<%@ include file="mensaje.jsp" %>

	<a href="alumnoDetalle.jsp" > Crear nuevo Usuario</a>	
	
<table summary="Análisis de ventas anuales" id="tabla_alumno">
  <caption>Cabecera o Caption</caption>
  <thead>
    <tr>          
      <th scope="col">Nombre</th>
      <th scope="col">Apellido</th>
      <th scope="col">DNI</th>
      <th scope="col">operaciones</th>
    </tr>
  </thead>
 
  
  <tbody>
  
  	<!-- 
  	<c:forEach var="alumno" items="${requestScope.listaAlumnos}">
  		${alumno}
  	</c:forEach>
  	 -->
  		

  	<%
		ArrayList <Alumno> lAlumnos = (ArrayList<Alumno>) request.getAttribute("listaAlumnos");
  		Alumno a; 
		for ( int i=0; i<lAlumnos.size();i++){
			a = lAlumnos.get(i);
			%>
			  <tr>
		     	 <td><%=a.getNombre()%></td>
		    	  <td><%=a.getApellido()%></td>
		   	 	  <td><%=a.getDni()%></td>
		   	 	  <td><a href="alumno?id=<%=a.getId()%>">detalle</a></td>
		    </tr>   
			<%		
		}	
	%>
	
  
  
  
  </tbody>
</table>


<script>

	//comentario de linea

	/* Espera q que el documento de HTML este totalmente cargado */	
	$(document).ready(function(){

	   console.debug('Esto es un mensaje de DEBUG');	
	   console.info ('Esto es un mensaje de INFO');
	   console.error('Esto es un mensaje de ERROR');
	  //libreria o plugin de datatble
	   $('#tabla_alumno').dataTable();
	});

</script>
	
	
	
	


</body>
</html>