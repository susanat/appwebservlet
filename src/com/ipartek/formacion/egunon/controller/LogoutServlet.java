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
import com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	protected static final Logger log = Logger.getLogger(LogoutServlet.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	
		//borrar usuario de session
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		log.debug("borrado login");
		//Sobra session.setAttribute("login", null);	
		//session.invalidate();
			
		//cargar mensaje y pasar
		request.setAttribute("msg", new Mensaje("Gracias por visitarnos, gero arte", 200, TIPO_MENSAJE.INFO));
		
		//redirecionar a index.jsp
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
