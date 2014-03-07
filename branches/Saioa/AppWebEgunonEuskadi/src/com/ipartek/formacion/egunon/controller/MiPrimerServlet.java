package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		//Recoger parametro de inicio
		String cadenaOngiEtorri=getInitParameter("ongietorri");
		
		
		//tratar la peticion
		String nombre=request.getParameter("izena");
		String apellido=request.getParameter("abizena");
		if(nombre==null){
			nombre="Anonimo";
			}
		//Es como el if de nombre hace lo mismo
		apellido=(apellido!=null)?apellido:"";
		
		//especificar tipo de respuesta
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out= response.getWriter();
		//Pintar la respuesta
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		//cabecera
		out.print("<head>");
			out.print("<title>Ongi Etorri | Egunon Euskadi</title>");
		out.print("</head>");
		//cuerpo
		out.print("<body>");
		
		out.print("<h1>"+cadenaOngiEtorri + ""+ nombre +"" + apellido +"</h1>");
		out.print("<form method='post' action='Request.ParamExample'>");
		out.print("<label for='izena'");
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
