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

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.formacion.enumeraciones.TIPO_MENSAJE;

/**
 * Servlet implementation class Login
 */
public class LoginServlet extends ServletMaestro {
	
	private final static Logger log = Logger.getLogger(LoginServlet.class);
	
	private static final long serialVersionUID = 1L;
	
	
	private static final String USER_LOGIN="abcdef";

	private static final String USER_PASS="Aa123456";
	
	private static final String COOKIE_USER_NAME="cName";
	private static final String COOKIE_USER_PASS="cPass";
		
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();

	}

@Override
	public void init(ServletConfig config) throws ServletException {
		//super.init(config);
}
	
	@Override
	public void destroy() {
	
		super.destroy();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		super.service(request, response);
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
		log.trace("doPost");
		// Recoger atributos del formulario
		String name =(String)request.getParameter("user_login");
		String pass=(String)request.getParameter("pass_login");
		// Obetner session
		HttpSession session = request.getSession();
		
		//Obtener Dispatcher
		RequestDispatcher dispatcher;
		
		//TODO validar usuario contra la BBDD
		if ( USER_LOGIN.equalsIgnoreCase(name)&& USER_PASS.equals(pass)) {
				UserLogin userLogin= new UserLogin(name, pass);
				
					// Guardar en sesión los datos del usuario
					session.setAttribute("login", userLogin);
					Cookie cName;
					Cookie cPass;
					// Cargar Cookies
					if("on".equalsIgnoreCase(request.getParameter("recuerdame"))){
						cName = new Cookie(COOKIE_USER_NAME, userLogin.getNombre() );
						cPass =new Cookie(COOKIE_USER_PASS, userLogin.getPassword() );
						
						// Caducan al de un día
						
						cName.setMaxAge(60 * 60 * 24);
						cPass.setMaxAge(60 * 60 * 24);
						
						// Añades las cookies
						response.addCookie(cName);
						response.addCookie(cPass);
						log.trace("Guardadas Cookies");
					}else{
						// Si no quieres ser recodado, borramos las cookies.
						cName = new Cookie(COOKIE_USER_NAME,"");
						cPass =new Cookie(COOKIE_USER_PASS,"");
						
						cName.setMaxAge(0);
						cPass.setMaxAge(0);
						
						response.addCookie(cName);
						response.addCookie(cPass);
						log.trace("Borradas Cookies");
					}
					
					// SI LOGIN OK: redireccionar al index.jsp 
					dispatcher= request.getRequestDispatcher("index.jsp");
					// una vez que se halla hecho todo correctamete, mostrar el mensaje de que todo ha ido correctamente
					request.setAttribute("msg", new Mensaje("Ongi Etorri, te has logeado correctamente", "1", TIPO_MENSAJE.INFO));
					
				} else {
				
					// SI LOGIN NO OK: redireccionar al login.jsp
					
					log.warn("Intento fallido de login [" + name+" , "+ pass+"]"+ request.getRemoteAddr());
					dispatcher = request.getRequestDispatcher("login.jsp");
					//Establecer el mensaje, sólo si hay datos introducidos por el usuario
					if(name!=null && pass!=null){
						request.setAttribute("msg", new Mensaje("Error al logearse, comprueba por favor el usuario y contraseña", "1", TIPO_MENSAJE.ERROR));
					}
				}
		log.trace("forward");
		dispatcher.forward(request, response);
	}

}
