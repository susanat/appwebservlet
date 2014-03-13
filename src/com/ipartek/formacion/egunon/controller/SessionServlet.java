package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.UserLogin;

/**
 * Servlet implementation class SessionServlet
 */
public class SessionServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	
	private final static Logger log = Logger.getLogger(SessionServlet.class);
	
	private static RequestDispatcher dispatcher;
	
	private static ArrayList<UserLogin> usuariosSesion= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
    }

    
    @Override
    public void init(ServletConfig config) throws ServletException {
        	super.init(config);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
			dispatcher= request.getRequestDispatcher("session.jsp");
			// Redirección a la jsp correspondiente
			dispatcher.forward(request, response);
	}

}
