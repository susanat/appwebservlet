<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date" %>


<%	
	Date fecha1= new Date();
	SimpleDateFormat sdf1= new SimpleDateFormat("yyyy");
	String anio= sdf1.format(fecha1);

%>

<footer>
		<small>Ipartek <%=anio %> © Copyright </small>
	
	</footer>
