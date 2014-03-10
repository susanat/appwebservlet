package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestInfo
 */
public class RequestInfo extends MaestroServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestInfo() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String fecha = sdf.format(d);
		String initParametro = getInitParameter("ipartek");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title>Request Information Example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<header align=right>" + fecha + "</header>");
		out.println("<hr>");
		out.println("<h3>Request Information Example</h3>");
		out.println("<p>Method: " + request.getMethod());
		out.println("<br/>Request URI: " + request.getRequestURI());
		out.println("<br/>Protocol: " + request.getProtocol());
		out.println("<br/>PathInfo: " + request.getPathInfo());
		out.println("<br/>Remote Address: " + request.getRemoteAddr());
		out.println("</p>");
		out.println("<hr>");
		out.println("<footer>" + initParametro + g.get(GregorianCalendar.YEAR)
				+ "&#169; </footer>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
