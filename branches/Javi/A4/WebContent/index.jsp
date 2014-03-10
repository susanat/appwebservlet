<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Home | Egunon Euskadi</title>
	</head>
	<body>
		
		<header>
			<nav>
				<ol>
					<li><a href="egunon">Ongi Etorri</a></li>
					<li><a href="egunon?izena=manola&abizena=Gisasola">Ongi Etorri Manola</a></li>
					<li><a href="RequestInfo">Request Info</a></li>
				</ol>
			</nav>	
		</header>
		<!-- el codigo de java se escribe entre < % % > -->
		<% for (int i = 1; i <= 6; i++) { %>
			<h<%=i%>>Egunon Euskadi <%=i%></h<%=i%>> <!-- < %  =i %> no va con ; como en java -->
		<% } // END FOR %>
		
		
		 <%
		 	//Esto es un bloque de Scriptlet o codigo Java
		 	out.print("<span>Esto es un SYSO en JSP<span>");
		 	out.println("<hr>");
		 	//For usando el out de JspWriter
		 	for (int i = 1; i <= 6; i++) {
		 		out.println("<h" + i + "> Egunon Euskadi </h"+i+">");
		 	}
		 %>
		 
		 <%DateFormat df;
		 
		 %>
		 
	</body>
</html>