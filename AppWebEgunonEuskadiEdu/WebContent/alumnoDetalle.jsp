<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h1>    </h1>
<%Alumno a=(Alumno)request.getAttribute("detalleAlumno"); %>


<form action="alumno" method="get">
    <label name="id">ID</label>
    <input type="text" name="id" disabled value="<%=a.getId()%>"><br/>
    
    <label name="ename">Nombre</label>
    <input type="text" name="name" value="<%=a.getNombre()%>"><br/>
    
    <label name="eapellido">Apellido</label>
    <input type="text" name="apellido" value="<%=a.getApellido()%>"><br/>
    
    <label name="eapellido">Dni</label>
    <input type="text" name="apellido" value="<%=a.getDni()%>"><br/>
    
    <label name="eedad">Edad</label>
    <input type="text" name="edad" value="<%=a.getEdad()%>"><br/>
    
    <label name="eemail">Email</label>
    <input type="text" name="email" value="<%=a.getEmail()%>"><br/>
    
    <input type="submit" name="update" value="Modificar">
    <input type="submit" name="delete" value="Borrar">
    
    




</form>
</html>