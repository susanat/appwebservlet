package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.enumeraciones.TIPO_MENSAJE;

/**
 * Servlet implementation class DesconectarServlet
 */
public class LogoutServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(LogoutServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Establecer la session a null, borrar la sesión del usuario
		HttpSession session = request.getSession();

		// session.setAttribute("login", null);
		session.invalidate();

		// Cargar mensage
		request.setAttribute("msg", new Mensaje("Gracias por visitarnos, gero arte", "2", TIPO_MENSAJE.INFO));

		// Redirigir al index
		RequestDispatcher dispatcher = dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
