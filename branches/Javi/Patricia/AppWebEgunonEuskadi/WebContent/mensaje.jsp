<%@page import="com.ipartek.formacion.egunon.bean.Mensaje" %>
<!-- Muestra los mensajes -->
<%

	Mensaje mensaje= (Mensaje)request.getAttribute("msg");
		if(mensaje!=null){
			out.print("<br><div id='msg'>");
			out.print("<span id="+mensaje.getTipo().toString().toLowerCase()+">"+ mensaje.getMensaje()+"</span>");
		out.print("</div><br>");
}
				 %>