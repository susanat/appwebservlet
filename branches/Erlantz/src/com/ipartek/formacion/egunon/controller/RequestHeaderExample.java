package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestHeaderExample
 */
public class RequestHeaderExample extends MaestroServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestHeaderExample() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String fecha = sdf.format(d);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Enumeration e = request.getHeaderNames();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title>Request Header Example Example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<header align=right>" + fecha + "</header>");
		out.println("<hr>");
		out.println("<h3>Request Header Example Example</h3>");
		out.println("<p>");
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = request.getHeader(name);
			out.println(name + " = " + value + "<br/> ");
		}
		out.println("</p>");
		out.println("<hr>");
		out.println("<footer>Ipartek" + g.get(GregorianCalendar.YEAR)
				+ "&#169; </footer>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log.trace("Init " + getServletName());
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
