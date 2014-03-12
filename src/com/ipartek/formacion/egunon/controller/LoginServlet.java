package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;

/**
 * Servlet implementation class ServletLogin
 */
public class LoginServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;

	private static final String COOKIE_USER_NAME= "cName";
	private static final String COOKIE_USER_PASS= "cPass";
	private final static Logger log=Logger.getLogger(LoginServlet.class);
	
	static ArrayList<UserLogin>listaUsuarios;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		if (listaUsuarios==null){
			listaUsuarios=new ArrayList<UserLogin>();
		}		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		log.trace("destroying");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recoger atributos del formulario
		log.trace("doPost");
		String name=(String) request.getParameter("user_login");
		String pass=(String) request.getParameter( "pass_login");
        
		//borrar usuario de session
		HttpSession session=request.getSession();
		//TODO Invalidar session correctamente
		//invalidar session
		//session.invalidate();
		
		RequestDispatcher dispatcher;
		
		//recoger usuario de la BBDD por su DNI
		ModeloAlumno model = new ModeloAlumno();
		Alumno alumnoBaseDatos = model.getAlumnoByDni(pass);
		
		//validar usuario contra la BBDD
		if(alumnoBaseDatos.getNombre().equalsIgnoreCase(name)&& alumnoBaseDatos.getDni().equals(pass)){

			UserLogin userLogin =new UserLogin(name,pass);
			session.setAttribute("login", userLogin);

			listaUsuarios.add(userLogin);
			

			Mensaje msg=new Mensaje("Saludos " + name ,200,TIPO_MENSAJE.INFO);
			request.setAttribute("msg", msg);

			//Si login ok: guardar en sesion, redirecionas al index

			//CargaCookies
			Cookie cName = null;
			Cookie cPass = null;
			if ("on".equalsIgnoreCase(request.getParameter("recuerdame"))){
				cName=new Cookie("COOKIE_USER_NAME",userLogin.getNombre());
				cPass= new Cookie("COOKIE_USER_PASS",userLogin.getPassword());
				//caducan al de un dia
				cName.setMaxAge(60*60*24);
				cPass.setMaxAge(60*60*24);
				log.trace("Guardadas cookies");
				
			}else{
				//Si no quiere ser recordado borramos las cookies
				cName=new Cookie("COOKIE_USER_NAME","");
				cPass= new Cookie("COOKIE_USER_PASS","");
				cName.setMaxAge(0);
				cPass.setMaxAge(0);
				
			}
			response.addCookie(cName);
			response.addCookie(cPass);
            //REDIRECCIONAR AL INDEX
			dispatcher= request.getRequestDispatcher("index.jsp");

		}else{
			Mensaje msg=new Mensaje("Error de contraseña o usuario " + name ,200,TIPO_MENSAJE.INFO);
			request.setAttribute("msg", msg);
			//Si Login mal: rediccionar al login.jsp
			log.warn("Intento fallido de login ["+name + "," + pass + "]" + request.getRemoteHost() );
			dispatcher= request.getRequestDispatcher("login.jsp");
		}
		log.trace("forward");

		dispatcher.forward(request, response);
	}

}
