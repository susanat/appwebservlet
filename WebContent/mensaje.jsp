<!--  Muestra los mensajes  -->

<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%
	Mensaje msg = (Mensaje)request.getAttribute("msg");
	if (msg != null){
		out.println("<div id='msg'>");
		out.println("<span>"+msg.getMsg()+"</span>");
		out.println("</div>");
	}	
	
%>