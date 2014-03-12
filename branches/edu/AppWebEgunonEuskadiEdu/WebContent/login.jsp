 <%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Logeo</title>
</head>
<body>
<h1>Login AppWeb Egunon Euskadi</h1>

<%@include file="mensaje.jsp" %>
<%
  //recuperar cookies del usuario
   /* Cookie[] cookies=request.getCookies();
  if (cookies!=null){
	  for(Cookie cookie: cookies){
		  out.println(cookie);
	  }
  } */
 


%>
<hr/>


<form action="login" method="post">
<input type="text" name="user_login" value="${cookie['cName'].value}" size="40" placeholder="Nombre de Usuario (minimo 3)" required/><br/>
<input type="password" value="${cookie['cPass'].value}" pattern="[0-9]{8}[a-zA-Z]" required="required" name="pass_login" size="40" placeholder="Documento de identidad DNI">
<label name="recuerdame">Recordar usuario:</label>

<c:if test="${!empty cookie['cName']}" >
  <input type="checkbox" name="recuerdame" checked="checked" />
</c:if>

<c:if test="${empty cookie['cName']}"> 
  <input type="checkbox" name="recuerdame" />
</c:if>
<input type="submit" value="login">
</form>
</body>
</html>