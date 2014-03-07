<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>


<!DOCTYPE >
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
   <h1> Error-Lamentamos las molestias</h1>
   <ol>
     <li><b>Causa:</b><%=exception.getCause() %></li>
     <li><b>Mensaje</b><%=exception.getMessage() %></li>
     <textarea rows="150" cols="150">
        <%=exception.getStackTrace()%>
     </textarea>
     
   
   </ol>


</body>
</html>