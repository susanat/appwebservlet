package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE;

/**
 * Servlet implementation class ServletMaestro
 */
public class ServletMaestro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	protected static final Logger log = Logger.getLogger(ServletMaestro.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMaestro() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	//configuracion del logs
    	String prefix = getServletContext().getRealPath("/"); //obtiene la ruta del peoyecto
    	String log4jpath = getInitParameter("log4j-config"); //obtiene el nombre del archivo
    	if (log4jpath != null){
        	PropertyConfigurator.configure(prefix+log4jpath);
    	}
    }
    
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Enumeration e = request.getHeaderNames();
    	String[] value = request.getRequestURI().split("/");
    	String aux = value[value.length-1];
    	log.trace("Vengo de " + aux);
    	if (aux.equalsIgnoreCase("login") || aux.equalsIgnoreCase("login.jsp") || aux.equalsIgnoreCase("index.jsp") )
    	{
    		super.service(request, response);
    	}else{
    		session = request.getSession(true);
    		UserLogin login = (UserLogin) session.getAttribute("login");
    		//comprobamos el usuario
    		if (login != null){
    			super.service(request, response);
    		}else{
    			request.setAttribute("msg", new Mensaje("Tienes que estar logueado", 2, TIPO_MENSAJE.INFO));
    			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    			dispatcher.forward(request, response);
    		}
    	}
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
