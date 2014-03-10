package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.formacion.enumeraciones.TIPO_MENSAJE;

/**
 * Servlet implementation class ServletMaestro
 */
public class ServletMaestro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(ServletMaestro.class);
	HttpSession session;

	private final String URL_INDEX = "http://localhost:8080/AppWebEgunonEuskadi/index";
	private final String URL_LOGOUT= "http://localhost:8080/AppWebEgunonEuskadi/logout";
	private final String URL_LOGIN=  "http://localhost:8080/AppWebEgunonEuskadi/login";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletMaestro() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// configracion del log4j
		// Coge una ruta dinámica de donde está el proyecto que en el que se
		// está usando.
		super.init(config);
		String prefix = getServletContext().getRealPath("/");
		String log4jpath = getInitParameter("lo4j-config");

		if (log4jpath != null) {
			PropertyConfigurator.configure(prefix + log4jpath);
		}
		log.trace("Init " + getServletName());
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//super.service(request, response);

		// recuperas el usuario que tiene que estar logeado
		
		// Se establece el timeout de la sesion que se está utilizando. Se
		// indica el tiempo en segundos
		// Para unu unico usuario
		// HttpSessionEvent event = new HttpSessionEvent(session);
		// De esta forma la sesión caducará en un minuto
		// event.getSession().setMaxInactiveInterval(30 * 60);

		String url = request.getRequestURL().toString();
		if (!URL_INDEX.equals(url) && ! URL_LOGIN.equals(url) && !URL_LOGOUT.equals(url)){
			session = request.getSession(true);
			UserLogin userLogin = (UserLogin) session.getAttribute("login");
			if (userLogin != null) {
				// Compruebas que el usuario sea el correcto

				super.service(request, response);

			} else {
				// Redirigir al login pasandole un mensajes que se tiene que
				// logear

				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");

				request.setAttribute("msg", new Mensaje("Antes de acceder a la web debe logearse", "200", TIPO_MENSAJE.INFO));

				dispatcher.forward(request, response);
			}
		}else{
			super.service(request, response);
		}
	}

}
