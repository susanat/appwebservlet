package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class ServletMaestro
 */
public class ServletMaestro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected ResourceBundle  messages;
	private String language;
	private Locale locale;
	private final static Logger log = Logger.getLogger(ServletMaestro.class);
	
       
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
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Locale por defecto Español
		locale = new Locale("es_ES");
		
		//obtener lenguaje de la session del usuario
		language = (String) request.getSession().getAttribute("language");
		
		if ( language != null ){
		 locale = new Locale(language);
		}
		log.debug("language: " + language + " locale: " + locale);
		//Cargar resourceBundle o properties dependiente del idioma		
		messages = ResourceBundle.getBundle("com.ipartek.formacion.egunon.controller.i18nmessages", locale );
		super.service(request, response);
	}
	

}
