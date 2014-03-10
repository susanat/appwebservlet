package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);

	private static final String USER_LOGIN = "abcdef";
	private static final String USER_PASS = "Aa123456";

	private static final String COOKIE_USER_NAME = "cName";
	private static final String COOKIE_USER_PASS = "cPass";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
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
		log.trace("doPost");

		// recoger atributos del formulario
		String name = request.getParameter("user_login");
		String pass = request.getParameter("pass_login");

		// Obtener session
		HttpSession session = request.getSession();

		// Obtener dispatcher
		RequestDispatcher dispatcher = null;

		// TODO validar usuario contra la BBDD

		if (USER_LOGIN.equalsIgnoreCase(name) && USER_PASS.equals(pass)) {
			// Si login TRUE -> redireccionar al index
			// guardar en session
			UserLogin userLogin = new UserLogin(name, pass);
			session.setAttribute("login", userLogin);
			request.setAttribute("msg", new Mensaje(0x0));

			Cookie cName;
			Cookie cPass;

			// CargarCookies
			if ("on".equalsIgnoreCase(request.getParameter("recuerdame"))) {
				cName = new Cookie(COOKIE_USER_NAME, userLogin.getNombre());
				cPass = new Cookie(COOKIE_USER_PASS, userLogin.getPassword());
				// Caducan al de un dia
				cName.setMaxAge(24 * 3600);
				cPass.setMaxAge(24 * 3600);
				log.trace("Guardadas cookies");
			} else {
				cName = new Cookie(COOKIE_USER_NAME, "");
				cPass = new Cookie(COOKIE_USER_PASS, "");
				cName.setMaxAge(0);
				cPass.setMaxAge(0);
				log.trace("Borradas cookies");
			}
			response.addCookie(cName);
			response.addCookie(cPass);

			// redireccionar al index
			dispatcher = request.getRequestDispatcher("index.jsp");
		} else {
			// Si login FALSE -> redireccionar al login.jsp
			log.warn("Intento fallido de login [" + name + "," + pass + "]" + request.getRemoteHost());
			dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("msg", new Mensaje(0x2));
		}

		log.trace("forward");

		dispatcher.forward(request, response);

	}
}
