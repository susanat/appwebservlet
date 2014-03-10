package com.ipartek.formacion.egunon.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestHeader extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Enumeration e = request.getHeaderNames();
		out.println("<head>");
			out.println("<title>Request Information Example</title>");
			out.print("<link href='css/style.css' rel='stylesheet' type='text/css' >");
		out.println("</head>");
		out.println("<body>");
			out.print("<div>");
				request.getRequestDispatcher("/cabecera.jsp").include(request, response);
				out.println("<h3>Request Header Example</h3>");
				while (e.hasMoreElements()) {
					String name = (String) e.nextElement();
					String value = request.getHeader(name);
					out.println("<div>" + name + " = " + value + "</div>");
				}
			out.print("</div><br>");
			request.getRequestDispatcher("/footer.jsp").include(request, response);
			
		out.println("</body>");

		out.println("</html>");

	}
}
