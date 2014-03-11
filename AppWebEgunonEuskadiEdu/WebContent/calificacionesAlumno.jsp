<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.pruebas.bean.Calificacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Listado de todos las calificaciones</title>
</head>
<body>
   <h1>Listado de todos las calificaciones</h1><br/><br/>
   <%@include file="mensaje.jsp" %> 
   	<table summary="Listado Alumnos" id="tabla_calificaciones">
	  <caption></caption>
	   <thead>
	    <tr>
	      <th scope="col">Calificacion</th>
	      
	      
	    </tr>
	  </thead>
	  <tbody>
	   
	  	<%
			ArrayList<Calificacion> lCalif = (ArrayList<Calificacion>) request.getAttribute("calificaciones");	
	  	    Calificacion c;
			for ( int i=0; i<lCalif.size();i++){
				c=lCalif.get(i);
				%>
				  <tr>
			     	 <td><%=c.getCalificacion()%></td>
			    	  
			       </tr>   
				<%
			
			}
			
		
		%>
	  
	  
	  
	  </tbody>
	</table>
	<script>
		//comentario de linea
		/*Esto es un comentario bloque */
		$(document).ready(function(){
		  console.debug('Esto es un mensaje debug');
		  console.info('Esto es un mensaje info');
		  console.error('Esto es un mensaje error');
		  //cargar libreria o pluging jquery
		  $('#tabla_calificaciones').dataTable();
		});
	
	</script>
</body>
</html>