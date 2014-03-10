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

import com.ipartek.formacion.egunon.bean.MsgApp;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.formacion.egunon.bean.enumeracion.TipoMsg;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;
import com.ipartek.pruebas.exception.AlumnoException;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends MaestroServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	private ModeloAlumno model;
	private Alumno alumno;
	static final Logger log = Logger.getLogger(AlumnoServlet.class);
	public static final String OP_NUEVO_ALUMNO = "nuevo";
	public static final String OP_MODIFICAR_ALUMNO = "modificar";
	public static final String OP_BORRAR_ALUMNO = "eliminar";
	public static final String OP_DETALLE_ALUMNO = "detalle";
	public static final String OP_LISTAR_ALUMNO = "listar";
	RequestDispatcher dispatcher;
	private static String op;
	private static MsgApp mensaje;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlumnoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Obtener Dispatcher
		log.trace("inicio listado/detalle usuario");
		RequestDispatcher dispatcher;
		String idAlumno;
		// Despues de eliminar, cogemos el id a null para mostrar listado de alumnos
		if (!OP_BORRAR_ALUMNO.equalsIgnoreCase(op)) {
			idAlumno = request.getParameter("id");
		} else {
			idAlumno = null;
			op = "";
		}
		if (idAlumno != null) {
			// detalle
			// **********
			log.trace("Detalle alumno " + idAlumno);
			alumno = model.getAlumnoById(Integer.parseInt(idAlumno));
			// enviar datos en la request a la JSP
			request.setAttribute("detalleAlumno", alumno);
			dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");

		} else {
			// listando
			// ************
			log.trace("Listado de todos los alumnos");
			dispatcher = request.getRequestDispatcher("alumnoList.jsp");
			ArrayList<Alumno> listaAlumno = new ArrayList<Alumno>();
			listaAlumno = model.getAll();
			log.warn(listaAlumno.size() + " alumnos consultados");
			// enviar datos en la request a la JSP
			request.setAttribute("listaAlumnos", listaAlumno);

		}

		// Redireccionar a la JSP
		dispatcher.forward(request, response);
		log.trace("Fin listado/detalle usuario");
	}

	/**
	 * @throws IOException, Exception
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.trace("Inicio doPost");
		dispatcher = null;
		// Recoger operacion a realizar
		op = request.getParameter("op");
		if (OP_NUEVO_ALUMNO.equalsIgnoreCase(op)) {
			crearAlumno(request, response);
		} else if (OP_MODIFICAR_ALUMNO.equalsIgnoreCase(op)) {
			modificarAlumno(request, response);
		} else if (OP_BORRAR_ALUMNO.equalsIgnoreCase(op)) {
			eliminarAlumno(request, response);
		} else {
			log.warn("Operacion a realizar no soportada por la aplicacion "
					+ op);
			throw new ServletException("Operacion no soportada " + op);
		}
		log.trace("Fin doPost");
	}

	/**
	 * Funcion que permite recoger los datos de un alumno del formulario Si se modifica el alumno, recogemos el id, sino será un alumno nuevo
	 * 
	 * @param request
	 * @param response
	 * @return retorna un alumno con los datos del formulario, null en caso de error
	 */
	private Alumno recogerDatos(HttpServletRequest request,
			HttpServletResponse response) {
		log.trace("Init recoger datos alumno");
		Alumno newAlumno = null;
		mensaje = null;
		try {
			newAlumno = new Alumno();
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
		} catch (AlumnoException e) {
			mensaje = new MsgApp("ERROR: Datos de alumno no válidos ["
					+ e.getMensajeError() + "]", e.getCodigoError(),
					TipoMsg.ERROR);
			log.warn("ERROR Datos de alumno no válidos [" + e.getCodigoError()
					+ "]");
			newAlumno = null;
		} catch (Exception e) {
			mensaje = new MsgApp("ERROR: Excepcion General [" + e.getMessage()
					+ "]", 1010, TipoMsg.ERROR);
			log.error("Excepcion General " + e.getMessage() + " "
					+ e.getCause() + " " + e.getLocalizedMessage());
			newAlumno = null;
		}
		log.trace("Retorno de recoger datos alumno");
		return newAlumno;
	}

	private void crearAlumno(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.trace("Inicio insertar usuario");

		Alumno nuevoAlumno;
		// Crear nuevo alumno
		try {
			if ((nuevoAlumno = recogerDatos(request, response)) != null) {
				if (model.insert(nuevoAlumno) != -1) {
					mensaje = new MsgApp("Alumno creado", 11, TipoMsg.INFO);
					alumno = model.getAlumnoById(nuevoAlumno.getId());
					request.setAttribute("detalleAlumno", alumno);
					log.info("Usuario insertado con id [" + nuevoAlumno.getId()
							+ "]");
				} else {
					log.error("Error al insertar el alumno "
							+ nuevoAlumno.toString());
					mensaje = new MsgApp("No se puede insertar alumno", 11,
							TipoMsg.ERROR);
				}
			} else {
				log.error("Error al recoger datos del alumno");
				mensaje = new MsgApp("No se puede recoger datos del alumno",
						11, TipoMsg.ERROR);
			}
		} catch (Exception e) {
			mensaje = new MsgApp("ERROR: Excepcion General [" + e.getMessage()
					+ "]", 1010, TipoMsg.ERROR);
			log.error("Excepcion General " + e.getMessage() + " "
					+ e.getCause() + " " + e.getLocalizedMessage());
		}
		// EXCEPCION ALUMNO *----------------------------------------------------

		request.setAttribute("msg", mensaje);
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		dispatcher.forward(request, response);
		log.trace("Fin insertar usuario");
	}

	private void modificarAlumno(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recoger los datos de la request
		log.trace("Inicio modificar usuario");
		MsgApp mensaje = null;
		Alumno alumnoModificar;

		// Modificar alumno
		try {
			if ((alumnoModificar = recogerDatos(request, response)) != null) {
				if (model.update(alumnoModificar, alumnoModificar.getId())) {
					log.info("Usuario modificado [" + alumnoModificar.getId()
							+ "]");
					mensaje = new MsgApp("Alumno modificado", 1011,
							TipoMsg.INFO);
					alumno = model.getAlumnoById(alumnoModificar.getId());
				} else {
					log.error("Usuario no modificado ["
							+ alumnoModificar.getId() + "] "
							+ alumnoModificar.toString());
					mensaje = new MsgApp(
							"ERROR: no se ha podido modificar el alumno", 1011,
							TipoMsg.ERROR);
				}
			} else {
				log.error("Error al recoger datos del alumno");
				mensaje = new MsgApp("No se puede recoger datos del alumno",
						11, TipoMsg.ERROR);
			}
			// EXCEPCION GENERAL *----------------------------------------------------
		} catch (Exception e) {
			mensaje = new MsgApp("ERROR: Excepcion General [" + e.getMessage()
					+ "]", 1010, TipoMsg.ERROR);
			log.error("Excepcion General : ");
			StackTraceElement[] ste;
			ste = e.getStackTrace();
			for (int i = 0; i < ste.length; i++) {
				log.error("StackTrace " + i + ": " + ste[i]);
			}

		}
		request.setAttribute("msg", mensaje);
		request.setAttribute("detalleAlumno", alumno);
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		dispatcher.forward(request, response);
		log.trace("Fin modificar usuario");
	}

	private void eliminarAlumno(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recoger los datos de la request
		log.trace("Inicio eliminar usuario");
		int idAlumno = Integer.parseInt(request.getParameter("id"));
		MsgApp mensaje = null;
		// Modificar alumno
		try {
			mensaje = new MsgApp("Alumno creado", 11, TipoMsg.INFO);
			if (model.delete(idAlumno)) {
				log.info("Usuario eliminado [" + idAlumno + "]");
				mensaje = new MsgApp("Alumno Eliminado", 1011, TipoMsg.INFO);
			} else {
				log.error("Usuario no borrado [" + idAlumno + "]");
				mensaje = new MsgApp("No se ha podido borrar el alumno", 1011,
						TipoMsg.ERROR);
			}
			// EXCEPCION GENERAL *----------------------------------------------------
		} catch (Exception e) {
			mensaje = new MsgApp("ERROR: Excepcion General [" + e.getMessage()
					+ "]", 1010, TipoMsg.ERROR);
			StackTraceElement[] ste;
			ste = e.getStackTrace();
			for (int i = 0; i < ste.length; i++) {
				log.error("StackTrace " + i + ": " + ste[i]);
			}
		}
		request.setAttribute("msg", mensaje);
		doGet(request, response);
		log.trace("Fin eliminar usuario");

	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		session = request.getSession(true);

		UserLogin userLogin = (UserLogin) session.getAttribute("login");
		if (userLogin != null) {

			super.service(request, response);

		} else {
			// si es null forward a login.jsp
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log.trace("Init " + getServletName());
		model = new ModeloAlumno();
	}

	@Override
	public void destroy() {
		super.destroy();
		model = null;
	}
}
