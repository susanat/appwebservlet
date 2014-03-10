<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page import="com.ipartek.formacion.egunon.bean.enumeracion.TipoMsg"%>
<%@page import="com.ipartek.formacion.egunon.bean.MsgApp"%>
<%@page import="com.ipartek.formacion.egunon.bean.UserLogin"%>
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
			<div id="fecha" align=right>
			 <%
			 Date d = new Date();
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 String fecha = sdf.format(d);
			 out.println("<span>" + fecha + "</span>");
			 %>
			 </div>
			 <div id="mensaje">
				<%@include file="mensaje.jsp"%>
			</div>
			<div id="user_login">
				<%
					// Obtener usuario de session
					UserLogin login = (UserLogin) session.getAttribute("login");
					// Obtener mensaje
					//MsgApp mensaje = (MsgApp) request.getAttribute("msg");
					if (login != null){
						out.print("<span> USUARIO:" + login.getNombre() + "</span> <br/>");
						out.print("<span> Ultima visita: " + login.getAnteriorConexion() + "</span> <br/>");
						out.print("<span> Hora conexión" + login.getConexion() +"<span><br/>");
						%> 
						<span><a href="logout">Desconecta</a></span>
						<%
					} else {
						out.println("Anónimo");
						%>
							<span><a href="login.jsp">Logeate</a></span>
						<%
						
					}				
				%>
			</div>
			<hr>
			<nav>
				<ol>
					<li><a href="egunon">Ongi Etorri</a></li>
					<li><a href="egunon?izena=manola&abizena=Gisasola">Ongi Etorri Manola</a></li>
					<li><a href="RequestInfo">Request Info</a></li>
					<li><a href="RequestHeaderExample">Request Header Example</a></li>
					<li><a href="RequestParameterExample?firstname=&lastname=">Request Parameter Example</a></li>
					<%if(login != null){ %>
					<li><a href="alumno">Alumnos</a></li>
					<%} %>
				</ol>
			</nav>	
		</header>
		<hr>
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
		 <hr>
		 <%
		 GregorianCalendar g = new GregorianCalendar();
		 out.print("<footer>Ipartek" + g.get(GregorianCalendar.YEAR)
				+ "&#169; </footer>");
				 %>
	</body>
</html>