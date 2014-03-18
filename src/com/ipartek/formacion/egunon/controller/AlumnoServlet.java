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

import com.google.protobuf.Message;
import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;
import com.ipartek.pruebas.exception.AlumnoException;
import com.ipartek.pruebas.exception.LibroException;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(AlumnoServlet.class);

	
	public static final String OP_NUEVO_ALUMNO = "nuevo";
	public static final String OP_MODIFICAR_ALUMNO = "modificar";
	public static final String OP_ELIMINAR_ALUMNO = "eliminar";
	public static final String OP_DETALLE_ALUMNO = "detalle";
	public static final String OP_LISTAR_ALUMNO = "listar";

	//parametros de la request
	private static String idAlumno; // ID Alumnoa modificar
	private static String op; // Operacion a realizar
	
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
		modelAlumno = null;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(true);

		UserLogin userLogin = (UserLogin) session.getAttribute("login");
		// comprbar usuario
		if (userLogin != null) {
			// recoger Operacion a realizar
			op = (String) request.getParameter("op");
			idAlumno = (String) request.getParameter("id");
			// realizar servicio
			super.service(request, response);
		} else {
			// si es null forward a login.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// obtener dispatcher

		if (null != idAlumno) {
			if (!OP_ELIMINAR_ALUMNO.equalsIgnoreCase(op)) {
				detalleAlumno(request, response);
			} else {
				listarAlumnos(request, response);
			}
		} else {
			listarAlumnos(request, response);
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
		if (OP_NUEVO_ALUMNO.equalsIgnoreCase(op)) {
			crearAlumno(request, response);
		} else if (OP_MODIFICAR_ALUMNO.equalsIgnoreCase(op)) {
			modificarAlumno(request, response);
		} else if (OP_ELIMINAR_ALUMNO.equalsIgnoreCase(op)) {
			eliminarAlumno(request, response);
		} else {
			throw new ServletException("Operacion no soportada " + op);
		}

		log.trace("doPost - Fin ");
	}
	
	
	

	private void listarAlumnos(HttpServletRequest request, HttpServletResponse response) {
		// listando
		log.trace("Listado Alumnos");
		dispatcher = request.getRequestDispatcher("alumnoList.jsp");

		// conectar BBDD obtener Alumnos
		ArrayList<Alumno> listaAlumnos = modelAlumno.getAll();

		log.debug(listaAlumnos.size() + " alumnos consultados");

		request.setAttribute("listaAlumnos", listaAlumnos);

		if (OP_ELIMINAR_ALUMNO.equalsIgnoreCase(op)) {
			request.setAttribute("msg", new Mensaje("Alumno Eliminado Correctamente", 200, Mensaje.TIPO_MENSAJE.INFO));
		}

	}

	private void detalleAlumno(HttpServletRequest request, HttpServletResponse response) {
		// detalle
		log.trace("Detalle alumno " + idAlumno);
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");

		// obtener Alumnos
		Alumno a = modelAlumno.getAlumnoByDni(idAlumno);
		// enviar datos en la request a la JSP
		request.setAttribute("detalleAlumno", a);
		// post method
		request.setAttribute("method", "post");
		request.setAttribute("title", "Detalle Alumno");

	}

	

	private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("eliminarAlumno");
		try {

			String id = (String) request.getParameter("id");
			if (!modelAlumno.delete(Integer.parseInt(id))) {
				log.error("No se a ha podido Elimnar el Alumno [" + id + "] ");
				request.setAttribute("msg", new Mensaje("No se a ha podido Elimnar el Alumno, por favor consulte con el Administrador de la AppWeb",
						0, Mensaje.TIPO_MENSAJE.ERROR));
			} else {
				log.info("Alumno Eliminado [" + id + "]");
				
				//request.setAttribute("msg", new Mensaje("Alumno Eliminado Correctamente", 200, Mensaje.TIPO_MENSAJE.INFO));
				
				request.setAttribute("msg", new Mensaje(messages.getString("alumno.eliminado"), 200, Mensaje.TIPO_MENSAJE.INFO));
				
				
				
			}
		} catch (Exception e) {
			log.warn("Excepcion general " + e.getMessage());
			request.setAttribute("msg", new Mensaje("Excepcion general", 0, Mensaje.TIPO_MENSAJE.ERROR));
		}
		// titulo para la JSP
		request.setAttribute("title", "Listado Alumno");

		// Volver a llamar a doGet
		this.doGet(request, response);

		log.trace("eliminarAlumno - Fin");

	}

	private void modificarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("modificarAlumno");
		Alumno a = null;		
		try {
			a = recogerDatos(request, response);			
			// Update into DDBB
			if (!modelAlumno.update(a, Integer.parseInt(idAlumno))) {
				log.error("No se a ha podido modificar el Alumno [" + idAlumno + "] " + a.toString());
				request.setAttribute("msg", new Mensaje(
						"No se a ha podido modificar el Alumno, por favor consulte con el Administrador de la AppWeb", 0, Mensaje.TIPO_MENSAJE.ERROR));
			} else {
				log.info("Alumno Modificado " + a.toString());
				request.setAttribute("msg", new Mensaje("Alumno Modificadp", 200, Mensaje.TIPO_MENSAJE.INFO));
			}

		} catch (AlumnoException e) {
			log.warn("Datos del Alumno no validos " + e.getMessage());
			request.setAttribute("msg", new Mensaje(e.getMensajeError(), e.getCodigoError(), Mensaje.TIPO_MENSAJE.ERROR));

		} catch (Exception e) {
			log.warn("Excepcion general " + e.getMessage());
			request.setAttribute("msg", new Mensaje("Excepcion general", 0, Mensaje.TIPO_MENSAJE.ERROR));
		}
		// enviar alumno a la JSP
		request.setAttribute("detalleAlumno", a);
		// titulo para la JSP
		request.setAttribute("title", "Modificar Alumno");
		// dispatcher
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		dispatcher.forward(request, response);
		log.trace("modificarAlumno - Fin");

	}

	private void crearAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("crearAlumno");
		Alumno a = null;
		// crear Alumno
		try {
			a = recogerDatos(request, response);		
			// Insert into DDBB
			modelAlumno.insert(a);
			log.info("Alumno insertado " + a.toString());
			request.setAttribute("msg", new Mensaje("Alumno creado", 200, Mensaje.TIPO_MENSAJE.INFO));

		} catch (AlumnoException e) {
			log.warn("Datos del Alumno no validos " + e.getMessage());
			request.setAttribute("msg", new Mensaje(e.getMensajeError(), e.getCodigoError(), Mensaje.TIPO_MENSAJE.ERROR));

		} catch (Exception e) {
			log.warn("Excepcion general " + e.getMessage());
			request.setAttribute("msg", new Mensaje("Excepcion general", 0, Mensaje.TIPO_MENSAJE.ERROR));
		}
		// enviar alumno a la JSP
		request.setAttribute("detalleAlumno", a);
		// titulo para la JSP
		request.setAttribute("title", "Insertar Alumno");
		// dispatcher
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		dispatcher.forward(request, response);
		log.trace("crearAlumno - Fin");

	}

	/**
	 * Funcion que permite recoger los datos de un alumno del formulario Si se
	 * modifica el alumno, recogemos el id, sino será un alumno nuevo
	 * 
	 * @param request
	 * @param response
	 * @return retorna un alumno con los datos del formulario
	 * 
	 * @throws AlumnoException
	 * @throws LibroException 
	 */
	private Alumno recogerDatos(HttpServletRequest request, HttpServletResponse response) throws AlumnoException, LibroException {
		log.trace("Init recoger datos alumno");
		Alumno newAlumno =  new Alumno();
		if (OP_MODIFICAR_ALUMNO.equalsIgnoreCase(op)) {
			int idAlumno = Integer.parseInt(request.getParameter("id"));
			newAlumno.setId(idAlumno);
		}
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String dni = request.getParameter("dni");
		String email = request.getParameter("email");
		int edad = Integer.parseInt(request.getParameter("edad"));
		newAlumno.setNombre(nombre);
		newAlumno.setApellido(apellido);
		newAlumno.setDni(dni);
		newAlumno.setEdad(edad);
		newAlumno.setEmail(email);
		log.trace("Retorno de recoger datos alumno");
		return newAlumno;
	}

}
