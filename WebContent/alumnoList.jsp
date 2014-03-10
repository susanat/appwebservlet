<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@page import="java.util.ArrayList"%>
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
	<h1>LISTADO DE TODOS LOS ALUMNOS</h1>
	<div id="msg">
		<%@include file="mensaje.jsp"%>
	</div>	
	<div id="nuevaEntrada" align="right">		
		<span><a href="alumnoDetalle.jsp">CREAR NUEVA ENTRADA</a></span>
	</div>
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
			<%
				ArrayList<Alumno> lista = (ArrayList<Alumno>) request.getAttribute("listaAlumnos");
				if (lista.size() > 0){
					for(int i = 0 ; i < lista.size() ; i++ ) {
						%>
						  <tr>
					    	  <td><%=lista.get(i).getNombre() %></td>
					   	 	  <td><%=lista.get(i).getApellido() %></td>
					   	 	  <td><%=lista.get(i).getDni() %></td>
					   	 	  <td><a href="alumno?id=<%=lista.get(i).getId()%>">detalle</a></td>
					    </tr>   
						<%	
					}
				} else {
					out.println("No hay datos en la base de datos");
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