package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ipartek.pruebas.bbdd.model.ModeloAlumno;

/**
 * Servlet implementation class ServletMaestro
 */
public class ServletMaestro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ModeloAlumno ma;
	
	protected final Logger log = Logger.getLogger(getClass().getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMaestro() {
        super();
       
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
     
    	super.init(config);
    	ma = new ModeloAlumno();
    	String prefix = getServletContext().getRealPath("/");
    	String log4jpath = getInitParameter("log4j-config");
        if (log4jpath != null)
        {    	
        	PropertyConfigurator.configure(prefix+log4jpath);
        }
    	log.trace("Init "+getServletName());
    	
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
