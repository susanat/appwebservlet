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
import com.ipartek.formacion.egunon.bean.Mensaje.TIPO_MENSAJE;
import com.ipartek.pruebas.log.EjemploLog;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends ServletMaestro {
	
	
	private final static Logger log = Logger.getLogger(LoginServlet.class);
	
	private static final long serialVersionUID = 1L;
       
	private static final String USER_LOGIN = "abcdef";
	private static final String USER_PASS  = "Aa123456";
	
	private static final String COOKIE_USER_NAME = "cName";
	private static final String COOKIE_USER_PASS = "cPass";
	
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
    	
    }
    
    @Override
    public void destroy() {    	
    	super.destroy();
    	log.trace("destroy " + getServletName() );
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
		
		log.trace("dopost");
		//recoger vatributos del formulario
		String name = (String)request.getParameter("user_login");
		String pass = (String)request.getParameter("pass_login");		
		
		//obtener session
		HttpSession session = request.getSession();
		
		//obtener dispatcher
		RequestDispatcher dispatcher; 
		
		//TODO validar usuario contra la BBDD		
		if ( USER_LOGIN.equalsIgnoreCase(name) &&
			 USER_PASS.equals(pass)	)
		{
		//SI LOGIN OK :
			//guardar en session
			UserLogin userLogin = new UserLogin(name, pass);
			session.setAttribute("login", userLogin );
			
			//cargar mensaje y pasar en la request
			Mensaje msg =  new Mensaje("Ongi Etorri ", 200 , TIPO_MENSAJE.INFO );
			request.setAttribute("msg", msg );
			
			//CargaCookies
			Cookie cName;
			Cookie cPass;
			if ( "on".equalsIgnoreCase(request.getParameter("recuerdame")) ){
				cName = new Cookie(COOKIE_USER_NAME, userLogin.getNombre() );
				cPass = new Cookie(COOKIE_USER_PASS, userLogin.getPassword() );
				//caducan al de un dia
				cName.setMaxAge( 60 * 60 * 24 );
				cPass.setMaxAge( 60 * 60 * 24 );
				log.trace("Guardadas cookies");
			}else{
				//Si no quiere ser recordado borramos las Cookies
				cName = new Cookie(COOKIE_USER_NAME, "" );
				cPass = new Cookie(COOKIE_USER_PASS, "" );
				cName.setMaxAge(0);
				cPass.setMaxAge(0);
				log.trace("Borradas cookies");
			}
			response.addCookie(cName);
			response.addCookie(cPass);
			
			//redireccionar al index
			dispatcher = request.getRequestDispatcher("index.jsp");
			
		
		}else{
			//SI LOGIN MAL : redireccionar al login.jsp
			log.warn("Intento fallido de login ["+name+","+pass+"]" + request.getRemoteHost() );
			Mensaje msg =  new Mensaje("Por favor comprueba tu usuario y pass, no los has escrito bien. ", 200 , TIPO_MENSAJE.ERROR );
			request.setAttribute("msg", msg );			
			
			dispatcher = request.getRequestDispatcher("login.jsp");
			
		}
		log.trace("foward");
		dispatcher.forward(request, response);		
	}

}
