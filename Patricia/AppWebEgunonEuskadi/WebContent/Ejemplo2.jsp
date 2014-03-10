<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<!-- importación de la libreria de Java java.util.Date -->
<%@page import="java.util.Date" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ejemplo 2 | Egunon Euskadi</title>


</head>
<body>
	<!-- Se incluye un fichero jsp sin tener uqe repetir su contenido en varias paginas -->
	<%@include file="cabecera.jsp" %>
	<!-- Muestra la fecha del sistema a través de la libreria java.util.Date que se ha importado antes -->
	<h1>La fecha generada por el servidor es: <%out.print(new Date()); %></h1>
	<%Date fecha= new Date(); 
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
		String fechaFormateada= sdf.format(fecha);
	%>
		
	
	<h3>la fecha formateada es : <%=fechaFormateada %></h3>
	
</body>
</html>