package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MiPrimerServlet
 */
public class MiPrimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiPrimerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//tratar la peticion
		String nombre = request.getParameter("izena");
		String apellido = request.getParameter("abizena");
		String cadenaOngiEtorri = getInitParameter("ongietorri");
		//especificar el tipo de respuesta
		response.setContentType("text/Html; charset = utf-8");
		
		apellido = (apellido!=null)? apellido:"";
		nombre = (nombre!=null)? nombre: "Anonimo";
		
		//obtener el Writer
		
		PrintWriter out = response.getWriter();
		
		//Pintar la respuesta
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Ongi Etorri | Egunon Euskadi</title>");
		out.print("<time>"+new Date()+"</time>");
		out.print("</head>");
		out.print("<body>");
		
		out.println("<h1>"+cadenaOngiEtorri+" "+nombre+" "+apellido+" a nuesro primer Servlet</h1>");
		out.print("<footer>");
		out.print("<small>Ipartek©, "+new GregorianCalendar().get(GregorianCalendar.YEAR)+"</small>");
		out.print("</footer>");
		
		out.print("</body>");
		out.print("</html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
