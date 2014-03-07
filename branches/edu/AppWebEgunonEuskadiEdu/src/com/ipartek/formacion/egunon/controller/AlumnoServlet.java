package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.pruebas.bbdd.ConnectionFactory;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends ServletMaestro {
	
	private static final long serialVersionUID = 1L;
	private final static Logger log=Logger.getLogger(AlumnoServlet.class);
    HttpSession session;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlumnoServlet() {
		super();
		
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session=request.getSession(true);
		UserLogin userLogin=(UserLogin) session.getAttribute("login");
		//comprobar usuario
		if (userLogin!=null){
			super.service(request, response);
		}
		//si no es null redirigir a login.jsp
		else{
			RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
		    dispatcher.forward(request, response);
		}
			
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//obtener dispatcher
		RequestDispatcher dispatcher = null;
		
		//comprobar si es para detalle o listar		
		String idAlumno = request.getParameter("id");
		
		if ( null != idAlumno ){
			//detalle
			log.trace("Detalle alumno" + idAlumno);
			
			
			//TODO conectar BBDD obtener Alumnos
			
			
			//enviar datos en la request a la JSP
			ModeloAlumno modelAlumno= new ModeloAlumno();
			int id=Integer.parseInt(idAlumno);
			Alumno alumno=modelAlumno.getAlumnoById(id);
			request.setAttribute("detalleAlumno", alumno );
			dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
			
		}else{
			//listando
			log.trace("Listado alumnos");
			dispatcher = request.getRequestDispatcher("alumnoList.jsp");
			
			//TODO conectar BBDD obtener Alumnos	
			ModeloAlumno modelAlumno= new ModeloAlumno();
			ArrayList<Alumno>listaAlumnos=modelAlumno.getAll();
			
			
			ArrayList <String> lAlumnos = new ArrayList<String>();
			for(int i=0;i<100;i++){
				lAlumnos.add("Alumno" +i);
				
			}
			
			
			//enviar datos en la request a la JSP
			request.setAttribute("listaAlumnos", listaAlumnos );
			
		}
		
		//Redirecionar a la JSP
		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
	}

}
