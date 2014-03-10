package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.MsgApp;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.formacion.egunon.bean.enumeracion.TipoMsg;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends MaestroServlet {
	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(LoginServlet.class);

	private static final String USER_LOGIN = "abcdef";
	private static final String USER_PASS = "Aa123456";

	private static final String COOKIE_USER_NAME = "cName";
	private static final String COOKIE_USER_PASS = "cPass";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.trace("doPost");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// recoger los atributos del formulario
		String name = request.getParameter("user_login");
		String pass = request.getParameter("pass_login");

		// Obtener session
		HttpSession session = request.getSession();

		// Obtener dispatcher
		RequestDispatcher dispatcher;
		// TODO validar usuario contra la BBDD

		if (USER_LOGIN.equalsIgnoreCase(name) && USER_PASS.equals(pass)) {
			// SI EL LOGIN OK:
			// guardar en sesion
			UserLogin userLogin = new UserLogin(name, pass);
			session.setAttribute("login", userLogin);
			MsgApp mensaje = new MsgApp("Te has logeado", 1, TipoMsg.INFO);
			request.setAttribute("msg", mensaje);

			// cargar cookies
			Cookie cName;
			Cookie cPass;
			if ("on".equalsIgnoreCase(request.getParameter("recuerdame"))) {
				cName = new Cookie(COOKIE_USER_NAME, userLogin.getNombre());
				cPass = new Cookie(COOKIE_USER_PASS, userLogin.getPassword());
				log.warn("Cookies guardadas");
				// Caducan al de 1 día
				cName.setMaxAge(60 * 60 * 24);
				cPass.setMaxAge(60 * 60 * 24);
			} else {
				// Si no quiere ser recordado borraremos las cookies
				cName = new Cookie(COOKIE_USER_NAME, "");
				cPass = new Cookie(COOKIE_USER_PASS, "");
				log.warn("Cookies eliminadas");
				cName.setMaxAge(0);
				cPass.setMaxAge(0);
			}
			response.addCookie(cName);
			response.addCookie(cPass);
			// redireccionar al index.jsp
			dispatcher = request.getRequestDispatcher("index.jsp");
		} else {
			// SI EL LOGIN MAL: redireccionar al login.jsp
			log.warn("Intento fallido de login [" + name + "," + pass + "] "
					+ request.getLocalAddr());
			MsgApp mensaje = new MsgApp(
					"Clave de usuario o contraseña incorrecta", 1,
					TipoMsg.ERROR);
			session.invalidate();
			request.setAttribute("msg", mensaje);
			dispatcher = request.getRequestDispatcher("login.jsp");
		}
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
