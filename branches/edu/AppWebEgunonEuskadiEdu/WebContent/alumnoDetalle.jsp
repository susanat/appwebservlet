<%@page import="com.ipartek.formacion.egunon.controller.AlumnoServlet"%>
<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%
//Obtener el alumno para la jsp
  Alumno a=(Alumno)request.getAttribute("detalleAlumno");
  String titulo=(String)request.getAttribute("title");
  
  
  
  
  
 
	  
%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
   

</script>
<title><%=titulo%></title>
</head>
<body>
  <h1><%=titulo%></h1>
  <%@include file="mensaje.jsp" %>

  <a href="alumno">Volver al listado</a>
 


<form action="alumno" method="post">
    <label name="id">ID</label>
    <input type="text" name="id" disabled value="<%=a.getId()%>"><br/>
    <input type="hidden" name="id_oculto" value="<%=a.getId()%>">
    
    <label name="ename">Nombre</label>
    <input type="text" name="name" required pattern="[A-Za-z\s-'áéíóúñ]{2,}" value="<%=a.getNombre()%>"><br/>
    
    <label name="eapellido">Apellido</label>
    <input type="text" name="apellido" required value="<%=a.getApellido()%>"><br/>
    
    <label name="eapellido">Dni</label>
    <input type="text" name="dni" required value="<%=a.getDni()%>"><br/>
    
    <label name="eedad">Edad</label>
    <input type="text" name="edad" required value="<%=a.getEdad()%>"><br/>
    
    <label name="eemail">Email</label>
    <input type="text" name="email" required value="<%=a.getEmail()%>"><br/>
    
    <input type="submit" name="op" value="<%=AlumnoServlet.OP_MODIFICAR_ALUMNO%>">
    <input type="submit" name="op" value="<%=AlumnoServlet.OP_BORRAR_ALUMNO%>"
     onClick="if(!confirm('¿Seguro que deseas eliminar el registro?')){return false;}">
    
    




</form>
</html>