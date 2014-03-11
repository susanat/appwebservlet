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
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.pruebas.bbdd.DAOAlumno;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;

/**
 * Servlet implementation class ServletLogin
 */
public class LoginServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
      
	private static final String USER_LOGIN = "abcdef";
	private static final String USER_PASSWORD = "Aa123456";
	private static final String COOKIE_USER_NAME = "cName";
	private static final String COOKIE_USER_PASS = "cPass";
	protected static final Logger log = Logger.getLogger(LoginServlet.class);

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	log.trace("Init " + getServletName());
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    	log.trace("Destroy " + getServletName());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		log.trace("Atendiendo peticion");
		//recoger atributos del formulario
		String name = (String) request.getParameter("user_login");
		String pass = (String) request.getParameter("pass_login");
		//obtener session
		HttpSession session = request.getSession();
		//ontenemos el dipatcher
		RequestDispatcher dispatcher;
		
		ModeloAlumno modeloAlumno = new ModeloAlumno();
		Alumno a = modeloAlumno.getAlumnoByDni(pass);
		
		//validamos el usuario
		if (a!= null  && a.getNombre().equals(name)){
			//SI LOGIN OK:
			//guardar session
			UserLogin userLogin = new UserLogin(name, pass, session);
			session.setAttribute("login", userLogin);
			
			//cargar mensaje
			request.setAttribute("msg", new Mensaje(0));
			
			//cargar cookies
			Cookie cName;
			Cookie cPass;
			if ("on".equalsIgnoreCase(request.getParameter("recuerdame"))){
				cName = new Cookie(COOKIE_USER_NAME, userLogin.getNombre());
				cPass = new Cookie(COOKIE_USER_PASS, userLogin.getPassword());
				//caducan al de un día.
				cName.setMaxAge(60*60*24);
				cPass.setMaxAge(60*60*24);
				log.trace("Guardadas cookies");
			}else{
				cName = new Cookie(COOKIE_USER_NAME, "");
				cPass = new Cookie(COOKIE_USER_PASS, "");
				cName.setMaxAge(0);
				cPass.setMaxAge(0);
				log.trace("Borradas cookies");
			}
			response.addCookie(cName);
			response.addCookie(cPass);
			
			
			//redireciono a index.html
			dispatcher = request.getRequestDispatcher("index.jsp");
		}else{
			//SI LOGIN NO OK: redireccion a login.jsp
			log.warn("Intento fallido de login ["+name+", "+pass+"]" + request.getRemoteHost());
			request.setAttribute("msg", new Mensaje(2));
			dispatcher = request.getRequestDispatcher("login.jsp");
		}

		log.trace("Forward");
		//redirecionar a la JSP
		dispatcher.forward(request, response);
	}

}
