package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestParamExample
 */
public class RequestParamExample extends ServletMaestro {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestParamExample() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		
		response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Request Parameters Example</title>");
	        out.println("</head>");
	        out.println("<body>");
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
	        out.print("requestraram\" ");
	        out.println("method=POST>");
	        out.println("First Name:");
	        out.println("<input type=text size=20 name=firstname>");
	        out.println("<br>");
	        out.println("Last Name:");
	        out.println("<input type=text size=20 name=lastname>");
	        out.println("<br>");
	        out.println("<input type=submit>");
	        out.println("</form>");
	        out.println("</body>");
	        out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
