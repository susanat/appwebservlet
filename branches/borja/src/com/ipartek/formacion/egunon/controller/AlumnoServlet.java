package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.pruebas.bbdd.ConnectionFactory;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;
import com.ipartek.pruebas.exception.AlumnoException;
import com.ipartek.pruebas.exception.LibroException;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlumnoServlet() {
		super();

	}

	/**
     * 
     */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(true);
		UserLogin userLogin = (UserLogin) session.getAttribute("login");

		// comprobar usuario

		if (userLogin != null) {

			super.service(request, response);

		} else {

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Obtener dispacher
		RequestDispatcher dispatcher;

		// TODO conectar BBDD Obtener Alumnos

		ArrayList<Alumno> IAlumnos = new ArrayList<Alumno>();
		IAlumnos = ma.getAll();

		// enviar datos en la request a la JSP
		String idAlumno = request.getParameter("id");

		if (idAlumno != null) {
			log.trace("Detalle alumno " + idAlumno);
			dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
			request.setAttribute("Alumno", ConnectionFactory.getInstance()
					.getDAOAlumno().getById(Integer.valueOf(idAlumno)));

		} else {
			log.trace("Listado alumnos");
			log.debug(IAlumnos.size() + " alumnos importados");
			dispatcher = request.getRequestDispatcher("alumnoList.jsp");
			request.setAttribute("listAlumnos", IAlumnos);

		}

		// Redireccionar a la JSP
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtener dispacher
		log.trace("Insertar usuario");
		RequestDispatcher dispatcher;

		Alumno a = null;

		// enviar datos en la request a la JSP
		try {
			a = new Alumno();
			a.setNombre(request.getParameter("nombre"));
			a.setApellido(request.getParameter("apellido"));
			a.setDni(request.getParameter("dni"));
			a.setEdad(Integer.parseInt(request.getParameter("edad")));
			a.setEmail(request.getParameter("email"));

			if (ma.insert(a)) {
				log.info("Se ha insertado correctamente en la BD el alumno: "
						+ a.toString());
				request.setAttribute("Mensaje",
						new Mensaje ("Alumno creado correctamente",200, Mensaje.TIPO_MENSAJE.INFO));
				

			} else {
				log.error("No se ha podido insertar en la BD el alumno"
						+ a.toString());
				request.setAttribute("Mensaje",
						new Mensaje("No se ha podido insertar en la BD el alumno",0,Mensaje.TIPO_MENSAJE.ERROR));
				
			}

		} catch (Exception e) {
           
			log.warn("Datos del alumno no validos: "+e.getMessage());
            request.setAttribute("Mensaje",
					new Mensaje("Datos del alumno no validos",0,Mensaje.TIPO_MENSAJE.ERROR));
		} 
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		request.setAttribute("title","Insertar alumno");
		request.setAttribute("detalleAlumno",a);
		// Redireccionar a la JSP
		dispatcher.forward(request, response);
		log.trace("FIN - Insertar usuario");
	}

}
