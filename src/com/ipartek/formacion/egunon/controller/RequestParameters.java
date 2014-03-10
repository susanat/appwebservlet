package com.ipartek.formacion.egunon.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestParameters extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    
    {
    	String firstName= request.getParameter("firstname");
    	String lastName= request.getParameter("lastname");
    	
    	
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        	out.println("<head>");
        		out.println("<title>Request Parameters Example</title>");
        		out.print("<link href='css/style.css' rel='stylesheet' type='text/css' >");
        	out.println("</head>");
        	out.println("<body>");
        		request.getRequestDispatcher("/cabecera.jsp").include(request, response);
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
        		out.print("requestParameters\" ");
        		out.println("method=POST>");
        		out.println("First Name:");
        		out.println("<input type=text size=20 name=firstname>");
        		out.println("<br>");
        		out.println("Last Name:");
        		out.println("<input type=text size=20 name=lastname>");
        		out.println("<br>");
        		out.println("<input type=submit>");
        	out.println("</form>");
        request.getRequestDispatcher("/footer.jsp").include(request, response);
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}
