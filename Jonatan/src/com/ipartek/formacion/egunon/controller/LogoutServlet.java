package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LogoutServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener sesion
		HttpSession session = request.getSession();

		// Invalidar sesión
		session.invalidate();

		// Obtener dispatcher
		RequestDispatcher dispatcher = null;
		request.setAttribute("msg", new Mensaje("Gracias por visitarnos, gero arte", 200, TIPO_MENSAJE.INFO));
		dispatcher = request.getRequestDispatcher("login.jsp");

		dispatcher.forward(request, response);
	}

}
