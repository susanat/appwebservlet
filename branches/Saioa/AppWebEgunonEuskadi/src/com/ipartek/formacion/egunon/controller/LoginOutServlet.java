package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.Mensaje.TIPO_MENSAJE;

/**
 * Servlet implementation class LoginOutServlet
 */
public class LoginOutServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	private final static Logger log=Logger.getLogger(LoginOutServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        HttpSession sesion = request.getSession();
        RequestDispatcher dispatcher;
           //Cerrar sesion
        //sesion.invalidate();
        sesion.setAttribute("login", null);
        //mensaje
        Mensaje msg=new Mensaje("Gracias por visitarnos,gero arte" ,200,TIPO_MENSAJE.INFO);
        request.setAttribute("msg", msg);
        	//Redirecciono a index.jsp
        //response.sendRedirect("index.jsp");
        dispatcher=request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
