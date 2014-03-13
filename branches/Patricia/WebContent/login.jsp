
<%@page import="com.ipartek.formacion.egunon.bean.Mensaje" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logeo | Egunon Euskadi</title>
<link href="css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
	<%@include file="cabecera.jsp" %>
	
	<%
		// Recuperar cookies del usuario
		//Cookie[] cookies = request.getCookies();
		//if(cookies!=null){
			//for(Cookie cookie: cookies){
				//out.println(cookie);
			//}
	//	}
	%>
	<hr>
	
	<br>
	
	<h1>Login AppWeb Egunon Euskadi</h1>
		<div id="logerse">
			<form method="POST" action="login">
				<input type="text" name="user_login" size="40"  placeholder="Nombre Usuario (minimo 5)" 
					value="${cookie['cName'].value}"  pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{4,20}$" required />
				<br>
				
				<!--  <input type="password" name="pass_login" size="40" placeholder="Password Usuario(Mayus, minus y numero)" 
				pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" required value="${cookie['cPass'].value}" />-->
				<input type="password" name="pass_login" size="40" placeholder="Documento de Identidad D.N.I." required pattern="[0-9]{8}[a-zA-Z]" value="${cookie['cPass'].value}" />
				
				<br>
				
				<label name="recuerdame">Recordar usuario</label>
				
				<!--<c:if test="${!empty cookie['cName'] }">
					<input type="checkbox" name="recuerdame" checked />
				 </c:if>
				 <c:if test="${empty cookie['cName'] }">
				 	<input type="checkbox" name="recuerdame" />
				 </c:if>-->
				 
				 <c:choose>
				 	<c:when test="${!empty cookie['cName'] }">
				 		<input type="checkbox" name="recuerdame" checked />
				 	</c:when>
				 	<c:when test="${empty cookie['cName'] }">
				 		<input type="checkbox" name="recuerdame" />
				 	</c:when>
				 </c:choose>
				<br>
				<%@include file="mensaje.jsp" %>	
						
				<input type="submit" value="Login"/>
			</form>
		</div>
	
	<%@include file="footer.jsp" %>
</body>
</html>