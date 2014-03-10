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

import com.ipartek.formacion.egunon.bean.MsgApp;
import com.ipartek.formacion.egunon.bean.enumeracion.TipoMsg;

/**
 * Servlet implementation class LogoutServlet Para deslogear a un cliente de una session
 */
public class LogoutServlet extends MaestroServlet {
	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(LogoutServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Borrar usuario de la session
		HttpSession session = request.getSession();
		// sobra session.setAttribute("login", null);
		session.invalidate();

		// Cargar mensaje y pasar en la request
		MsgApp mensaje = new MsgApp("Te has deslogeado, gracias por tu visita",
				1, TipoMsg.INFO);
		request.setAttribute("msg", mensaje);

		// Obtener dispatcher
		RequestDispatcher dispatcher;

		// redireccionar a la página
		dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log.trace("Init " + getServletName());
	}

	@Override
	public void destroy() {
		super.destroy();
	}

}
