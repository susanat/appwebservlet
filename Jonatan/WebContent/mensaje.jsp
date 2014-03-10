<%@page import="com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE"%>
<%@page import="com.ipartek.formacion.egunon.bean.Mensaje"%>
<%
	String msgValue = "";
	Mensaje msg = (Mensaje) request.getAttribute("msg");
	if (msg != null) {
		if (msg.getTipo().equals(TIPO_MENSAJE.ERROR)) {
			msgValue = msg.getCodigo() + ": " + msg.getMsg();
		} else {
			msgValue = msg.getMsg();
		}
%><script type="text/javascript">
<%-- 					 alert('<%=msgValue%>'); --%>
				</script>
<%
	}
	out.print("<span>" + msgValue + "</span>");
%>