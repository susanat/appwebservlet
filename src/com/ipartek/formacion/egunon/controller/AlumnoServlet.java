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

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;
import com.ipartek.pruebas.exception.AlumnoException;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(AlumnoServlet.class);
	
	public static final String OP_NUEVO_ALUMNO 		= "nuevo";
	public static final String OP_MODIFICAR_ALUMNO  = "modificar";
	public static final String OP_ELIMINAR_ALUMNO   = "eliminar";
	public static final String OP_DETALLE_ALUMNO    = "detalle";
	public static final String OP_LISTAR_ALUMNO     = "listar";	
	
	ModeloAlumno modelAlumno;
	HttpSession session;
	RequestDispatcher dispatcher = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlumnoServlet() {
		super();		
	}

	
	@Override
	public void init(ServletConfig config) throws ServletException {	
		super.init(config);
		modelAlumno = new ModeloAlumno();
	}
	
	
	@Override
	public void destroy() {	
		super.destroy();
		modelAlumno=null;
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(true);

		UserLogin userLogin = (UserLogin) session.getAttribute("login");
		// comprbar usuario
		if (userLogin != null) {
			super.service(request, response);
		} else {
			// si es null forward a login.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
		log.trace("Put");
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
		log.trace("Delete");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// obtener dispatcher
		RequestDispatcher dispatcher = null;

		// comprobar si es para detalle o listar
		String idAlumno = request.getParameter("id");

		if (null != idAlumno) {
			// detalle
			log.trace("Detalle alumno " + idAlumno);
			dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");

			// obtener Alumnos
			Alumno a = modelAlumno.getAlumnoById(Integer.valueOf(idAlumno));
			// enviar datos en la request a la JSP
			request.setAttribute("detalleAlumno", a);
			// post method
			request.setAttribute("method", "post");
			request.setAttribute("title", "Detalle Alumno");

		} else {
			// listando
			log.trace("Listado Alumnos");
			dispatcher = request.getRequestDispatcher("alumnoList.jsp");

			// conectar BBDD obtener Alumnos
			ArrayList<Alumno> listaAlumnos = modelAlumno.getAll();

			log.debug(listaAlumnos.size() + " alumnos consultados");

			request.setAttribute("listaAlumnos", listaAlumnos);

		}

		// Redirecionar a la JSP
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("doPost");
				
		//recoger Operacion a realizar
		String op = (String) request.getParameter("op");
		if ( OP_NUEVO_ALUMNO.equalsIgnoreCase(op)){
			crearAlumno(request,response);
		}else if ( OP_MODIFICAR_ALUMNO.equalsIgnoreCase(op)){
			modificarAlumno(request,response);
		}else if ( OP_ELIMINAR_ALUMNO.equalsIgnoreCase(op)){
			eliminarAlumno(request, response);
		}else{
			throw new ServletException("Operacion no soportada " + op );
		}
		
	
		log.trace("doPost - Fin ");	
	}


	private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("eliminarAlumno");
		try {
			
			String id = (String) request.getParameter("id");			
			if (!modelAlumno.delete(Integer.parseInt(id))){
				log.error("No se a ha podido Elimnar el Alumno ["+id+"] " );			
				request.setAttribute("msg", new Mensaje("No se a ha podido Elimnar el Alumno, por favor consulte con el Administrador de la AppWeb", 0, Mensaje.TIPO_MENSAJE.ERROR));	
			}else{			
				log.info("Alumno Eliminado ["+id+"]");
				request.setAttribute("msg", new Mensaje("Alumno Eliminado Correctamente", 200, Mensaje.TIPO_MENSAJE.INFO));
			}			
		} catch (Exception e) {
			log.warn("Excepcion general " + e.getMessage());			
			request.setAttribute("msg", new Mensaje("Excepcion general", 0, Mensaje.TIPO_MENSAJE.ERROR));
		}		
		//titulo para la JSP
		request.setAttribute("title", "Listado Alumno");
		
		//Volver a llamar  a doGet sin 'id' para listar
		request.setAttribute("id", null);
		this.doGet(request, response);
		
		log.trace("eliminarAlumno - Fin");
		
		
	}


	private void modificarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("modificarAlumno");	
		Alumno a = null;
		// TODO recoger parametros del formulario
		String nombre = (String) request.getParameter("nombre");
		String id = (String) request.getParameter("id");
		//apellido
		//dni
		//email
		//......etc
		
		
		// Modificar  Alumno
		try {
			a = new Alumno();
			a.setNombre(nombre);
			//Update into DDBB			
			if ( !modelAlumno.update(a, Integer.parseInt(id) )){
				log.error("No se a ha podido modificar el Alumno ["+id+"] " + a.toString() );			
				request.setAttribute("msg", new Mensaje("No se a ha podido modificar el Alumno, por favor consulte con el Administrador de la AppWeb", 0, Mensaje.TIPO_MENSAJE.ERROR));	
			}else{
				log.info("Alumno Modificado " + a.toString());
				request.setAttribute("msg", new Mensaje("Alumno Modificadp", 200, Mensaje.TIPO_MENSAJE.INFO));
			}	
			
		} catch (AlumnoException e) {
			log.warn("Datos del Alumno no validos " + e.getMessage());			
			request.setAttribute("msg", new Mensaje( e.getMensajeError(), e.getCodigoError() , Mensaje.TIPO_MENSAJE.ERROR));
			
		} catch (Exception e) {
			log.warn("Excepcion general " + e.getMessage());			
			request.setAttribute("msg", new Mensaje("Excepcion general", 0, Mensaje.TIPO_MENSAJE.ERROR));
		}
		// enviar alumno a la JSP		
		request.setAttribute("detalleAlumno", a);		
		//titulo para la JSP
		request.setAttribute("title", "Modificar Alumno");
		//dispatcher
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");	
		dispatcher.forward(request, response);
		log.trace("modificarAlumno - Fin");
		
		
	}


	private void crearAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("crearAlumno");	
		Alumno a = null;
		// TODO recoger parametros del formulario
		String nombre = (String) request.getParameter("nombre");
		//apellido
		//dni
		//email
		//......etc
		
		
		// crear Alumno
		try {
			a = new Alumno();
			a.setNombre(nombre);
			// Insert into DDBB			
			modelAlumno.insert(a);
			log.info("Alumno insertado " + a.toString());
			request.setAttribute("msg", new Mensaje("Alumno creado", 200, Mensaje.TIPO_MENSAJE.INFO));
			
		} catch (AlumnoException e) {
			log.warn("Datos del Alumno no validos " + e.getMessage());			
			request.setAttribute("msg", new Mensaje( e.getMensajeError(), e.getCodigoError() , Mensaje.TIPO_MENSAJE.ERROR));
			
		} catch (Exception e) {
			log.warn("Excepcion general " + e.getMessage());			
			request.setAttribute("msg", new Mensaje("Excepcion general", 0, Mensaje.TIPO_MENSAJE.ERROR));
		}
		// enviar alumno a la JSP		
		request.setAttribute("detalleAlumno", a);		
		//titulo para la JSP
		request.setAttribute("title", "Insertar Alumno");
		//dispatcher
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");	
		dispatcher.forward(request, response);
		log.trace("crearAlumno - Fin");
		
		
	}

}
