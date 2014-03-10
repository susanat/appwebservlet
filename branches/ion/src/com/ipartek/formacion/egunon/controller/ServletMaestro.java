package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class ServletMaestro
 */
public class ServletMaestro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
	@Override
	public void init(ServletConfig config) throws ServletException {	
		super.init(config);
		// configuracion basica del log
    	String prefix = getServletContext().getRealPath("/");
    	String log4jpath = getInitParameter("log4j-config");
		if (log4jpath != null) {
			PropertyConfigurator.configure(prefix+log4jpath);
		}		
		
	}

}
