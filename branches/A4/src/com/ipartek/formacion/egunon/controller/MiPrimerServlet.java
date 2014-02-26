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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String cadenaOngietorri = getInitParameter("ongietorri");

		// Tratar la petición
		String izena = request.getParameter("izena");
		String abizena = request.getParameter("abizena");
		if (izena == null) {
			izena = "Anónimo";
		}
		abizena = (abizena != null) ? abizena : "";

		String nombreCompleto = izena + " " + abizena;

		// Especificar tipo de respuesta
		response.setContentType("text/html;charset=UTF-8");
		// Obtener el writer
		PrintWriter out = response.getWriter();
		// Pintar la respuesta
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			// head
			out.println("<head>");
			out.println("<title>Ongi Etorri | Egunon Euskadi</title>");
			out.println("</head>");
			// body
			out.println("<body>");
			out.println("<h1>" + cadenaOngietorri + " " + nombreCompleto
					+ "</h1>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
