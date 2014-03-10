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
public class MiPrimerServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MiPrimerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// recoger parametros de inicio
		String cadenaOngiEtorri = getInitParameter("ongietorri") + " ";

		// Tratar la petición
		String nombre = request.getParameter("izena");
		String apellido = request.getParameter("abizena");

		nombre = (nombre == null) ? "Anonimo" : nombre;
		apellido = (nombre == null) ? "" : apellido;

		// especificar tipo de respuesta
		response.setContentType("text/html;charset=UTF-8");

		// Obtener el Writer
		PrintWriter out = response.getWriter();

		// Pintar la respuesta
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		// cabecera
		out.print("<head>");
		out.print("<title>Ongi Etorri | Egunon Euskadi</title>");
		out.print("</head>");
		// body
		out.print("<body>");
		out.print("<h1>" + cadenaOngiEtorri + nombre + " " + apellido + "</h1>");
		out.print("</body>");

		out.print("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
