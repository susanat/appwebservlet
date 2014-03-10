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
 * Servlet implementation class RequestParameterExample
 */
public class RequestParameterExample extends MaestroServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestParameterExample() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String firstName;
		String lastName;

		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String fecha = sdf.format(d);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (!request.getParameter("firstname").isEmpty()) {
			firstName = request.getParameter("firstname");
		} else {
			firstName = null;
		}
		if (!request.getParameter("lastname").isEmpty()) {
			lastName = request.getParameter("lastname");
		} else {
			lastName = null;
		}

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Request Parameters Example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<header align=right>" + fecha + "</header>");
		out.println("<hr>");
		out.println("<h3>Request Parameters Example</h3>");
		out.println("Parameters in this request:<br>");
		if (firstName != null || lastName != null) {
			out.println("First Name:");
			out.println(" = " + firstName + "<br>");
			out.println("Last Name:");
			out.println(" = " + lastName);
		} else {
			out.println("No Parameters, Please enter some");
		}
		out.println("<P>");
		out.print("<form action=\"");
		out.print("RequestParameterExample\" ");
		out.println("method=POST>");
		out.println("First Name:");
		out.println("<input type=text size=20 name=firstname>");
		out.println("<br>");
		out.println("Last Name:");
		out.println("<input type=text size=20 name=lastname>");
		out.println("<br>");
		out.println("<input type=submit>");
		out.println("</form>");
		out.println("<hr>");
		out.println("<footer>Ipartek" + g.get(GregorianCalendar.YEAR)
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
		// String firstname = request.getParameter("firstname");
		// String lastname = request.getParameter("lastname");
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
