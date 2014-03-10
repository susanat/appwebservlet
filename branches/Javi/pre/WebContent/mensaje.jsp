		
		<!--  Muestra los mensajes  -->
		<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
		<%
				if ( null != request.getAttribute("msg")){
					Mensaje msg = (Mensaje)request.getAttribute("msg");
					out.print("<div id='msg'>");
						out.print( msg.getMsg() );
					out.print("</div>");
				}
			
			%>	