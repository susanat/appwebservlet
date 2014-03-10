<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date" %>
<%
	Date fecha= new Date();
	SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	String fechaFormateada= sdf.format(fecha);
%>
 <c:set var="hoy" value="<%=new Date()%>"></c:set>

		<div class="fecha"><%=fechaFormateada%> </div>
		<div class="fecha"><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${hoy}" /></div>


	