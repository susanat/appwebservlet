<%@page import="com.ipartek.pruebas.bean.Alumno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%
	 String title = (String)request.getAttribute("title");
	 String method = (String)request.getAttribute("method");
	 
	 if (title==null){
		title = "Nuevo Usuario";
		method = "post";
		
	}
	 Alumno a = ((request.getAttribute("Alumno")!=null)?(alumno)request.setAttribute("detalleAlumno",a);;
	 if (a== null) {
		 a = new Alumno();
	 }
	%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=title%>|Egunon Euskadi</title>
</head>
<body>
	<h1><%=title%></h1>

	<form action="alumno" method="<%=method%>">
		<!--  ID del alumno -->
		<label name="id"><span>ID</span></label> <input type="text" name="id"
			disabled value="<%=a.getId()%>"> <br>

		<!--  Nombre del alumno -->
		<label name="nombre"><span>Nombre</span></label> <span><input
			type="text" name="nombre" value="<%=a.getNombre()%>"></span> <br>

		<!--  Apellido del alumno -->
		<label name="apellido"><span>Apellido</span></label> <span><input
			type="text" name="apellido" value="<%=a.getApellido()%>"></span> <br>

		<!--  DNI del alumno -->
		<label name="dni"><span>DNI</span></label> <input type="text"
			name="dni" value="<%=a.getDni()%>"> <br>

		<!-- Edad del alumno -->
		<label name="edad"><span>Edad</span></label> <input type="number"
			name="edad" value="<%=a.getEdad()%>"> <br>

		<!-- Email del alumno -->
		<label name="email"><span>Email</span></label> <input type="text"
			name="email" value="<%=a.getEmail()%>"> <br> <br> <input
			type="submit" name="update" value="Modificar"> <input
			type="submit" name="delete" value="Borrar"> <input
			type="submit" name="create" value="Crear">
	</form>


 				

</body>
</html>