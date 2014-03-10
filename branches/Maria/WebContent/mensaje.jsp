
<!--  MUESTRA LOS MENSAJES  -->
<%@page import="com.ipartek.formacion.egunon.bean.enumeracion.TipoMsg"%>
<%@page import="com.ipartek.formacion.egunon.bean.MsgApp"%>
<% if ( null != request.getAttribute("msg")) {
	out.print("<div id='msg'>");
	MsgApp mensaje = (MsgApp) request.getAttribute("msg");
	if (mensaje.getTipo() == TipoMsg.INFO)
		out.print("<span style='color:green'>" + mensaje.getMensaje() + "</span>");	
	if (mensaje.getTipo() == TipoMsg.WARN)
		out.print("<span style='color:orange'>" + mensaje.getMensaje() + "</span>");
	if (mensaje.getTipo() == TipoMsg.ERROR)
		out.print("<span style='color:red'>" + mensaje.getMensaje() + "</span>");
	out.print("</div>");
}
%>