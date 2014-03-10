package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class MaestroServlet
 */
public class MaestroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// HttpSession session;
	static final Logger log = Logger.getLogger(MaestroServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaestroServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @throws
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// configuracion básica del log
		// recoge la ruta dinámicamente hasta donde se encuentra el proyecto
		String prefix = getServletContext().getRealPath("/");
		// recoger el nombre del parametro init
		String log4jPath = getInitParameter("log4j-config");
		if (log4jPath != null) {
			PropertyConfigurator.configure(prefix + log4jPath);
		}
		log.trace("Init " + getServletName());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	/*
	 * @Override protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 * 
	 * session = request.getSession(true);
	 * 
	 * UserLogin userLogin = (UserLogin) session.getAttribute("login"); // comprbar usuario // TODO if (userLogin != null) {
	 * 
	 * super.service(request, response);
	 * 
	 * } else { // si es null forward a login.jsp RequestDispatcher dispatcher = request .getRequestDispatcher("login.jsp");
	 * dispatcher.forward(request, response); } }
	 */
}
