package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Formulario
 */
public class Formulario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Formulario() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// Obtener el writer, para escribir la respuesta y poder hacer los sysos
		PrintWriter out = response.getWriter();

		// Pintar la respuesta
		out.print("<!DOCTYPE html>");
			out.print("<html>");
				// cabecera
				out.print("<head>");
					out.print("<title>Ongi Etorri | Formulario</title>");
					out.print("<link href='css/style.css' rel='stylesheet' type='text/css' >");
					
				out.print("</head>");
				// body
				out.print("<body>");
				request.getRequestDispatcher("/cabecera.jsp").include(request, response);   
					out.print("<div>");
					out.print("<form method='post' action='info'");
							out.print("<label for='izena'>Nombre: </label>");
							out.print("<input type='text' name='izena' value='' />");
							out.print("<input type='submit' value='Enviar' />");
						out.print("</form>");
						
					out.print("</div>");
					request.getRequestDispatcher("/footer.jsp").include(request, response);
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
