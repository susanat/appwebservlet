<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>JSP page | Egunon Euskadi</title>
</head>
<body>
	<h1>El nombre del usuario almacenado en sesión es: ${sessionScope.usuario} </h1>
	
	<h1> El nombre recogido de la request : ${param.nombre}</h1>
	
	<h1> El apellido recogido de la request : ${param.apellido}</h1>

</body>
</html>