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
	// Para cuando crea el .class darle un numero identificativo y mejorar el
	// compilador
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MiPrimerServlet() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recoger parametros de inicio
		String cadenaOngiEtorri= getInitParameter("ongietorri");
		// Cuando envíe la petición que pinte un html
		
		//Tratar la petición
		String nombre= request.getParameter("izena");
		String apellido= request.getParameter("abizena");
		
		if(nombre==null){
			nombre = "Anonimo";
		}
		apellido=(apellido!=null)?apellido:" ";
		
		// Especificar el tipo de respuesta
		response.setContentType("text/html;charset=UTF-8");

		// Obtener el writer, para escribir la respuesta y poder hacer los sysos
		PrintWriter out = response.getWriter();

		// Pintar la respuesta
		out.print("<!DOCTYPE html>");
			out.print("<html>");
				// cabecera
				out.print("<head>");
					out.print("<title>Ongi Etorri | Egunon Euskadi</title>");
					out.print("<link href='css/style.css' rel='stylesheet' type='text/css' >");
					
				out.print("</head>");
				// body
				out.print("<body>");
				request.getRequestDispatcher("/cabecera.jsp").include(request, response);   
					out.print("<div>");
						out.print("<h1>"+ cadenaOngiEtorri+ " " + nombre +" "+apellido + "</h1>");
					out.print("</div>");
					
					request.getRequestDispatcher("/footer.jsp").include(request, response);
				out.print("</body>");
		out.print("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Hacer que el doget y el dopost hagan lo mismo.
		doGet(request, response);
	}

}
