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
	private static Logger log = Logger.getLogger(AlumnoServlet.class);

	public static final String OP_NUEVO_ALUMNO = "nuevo";
	public static final String OP_MODIFICAR_ALUMNO = "modificar";
	public static final String OP_ELIMINAR_ALUMNO = "borrar";

	private ModeloAlumno modeloAlumno;

	private HttpSession session = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlumnoServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.modeloAlumno = new ModeloAlumno();
		log.trace("init " + getServletName());
	}

	@Override
	public void destroy() {
		log.trace("destroy " + getServletName());
		super.destroy();
		this.modeloAlumno = null;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// @Override
	// protected void service(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // Obtener session
	// this.session = request.getSession(true);
	// UserLogin userLogin = (UserLogin) this.session.getAttribute("login");
	// if (userLogin == null) {
	// RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	// dispatcher.forward(request, response);
	// } else {
	// super.service(request, response);
	// }
	// }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// obtener dispatcher
		RequestDispatcher dispatcher = null;

		// comprobar si es para detalle o listar
		String idAlumno = request.getParameter("id");
		boolean mostrarLista = false;
		try {
			if (request.getAttribute("mostrarLista") != null) {
				mostrarLista = ((boolean) request.getAttribute("mostrarLista"));
			}
		} catch (Exception e) {
		}

		if ((idAlumno == null) || mostrarLista) {
			log.trace("Listado Alumno");
			request.setAttribute("title", "Lista Alumnos");

			dispatcher = request.getRequestDispatcher("alumnoList.jsp");

			ArrayList<Alumno> listaAlumnos = this.modeloAlumno.getAll();
			log.debug(listaAlumnos.size() + " alumnos consultados");
			// enviar datos en la request ala JSP
			request.setAttribute("listaAlumnos", listaAlumnos);
		} else {
			log.trace("Detalle alumno " + idAlumno);
			request.setAttribute("title", "Nuevo Alumno");

			int id = Integer.parseInt(idAlumno);
			if (id >= 0) {
				request.setAttribute("detalle", ConnectionFactory.getInstance().getDAOAlumno().getById(id));
				request.setAttribute("isNombreValid", true);
				request.setAttribute("isApellidoValid", true);
				request.setAttribute("isEmailValid", true);
				request.setAttribute("isEdadValid", true);
				request.setAttribute("isDniValid", true);
			}
			dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		}

		// Redireccionar a la jsp
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op = request.getParameter("op");

		if (OP_NUEVO_ALUMNO.equalsIgnoreCase(op)) {
			crearAlumno(request, response);

		} else if (OP_MODIFICAR_ALUMNO.equalsIgnoreCase(op)) {
			modificarAlumno(request, response);
		} else if (OP_ELIMINAR_ALUMNO.equalsIgnoreCase(op)) {
			borrarAlumno(request, response);

			return;
		}

	}// end doPost

	private void crearAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		Alumno a = null;
		a = validateValues(request, response, false);
		if (a != null) {
			this.modeloAlumno.insert(a);
		}
		log.debug("Creado alumno " + a.getId());

		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		dispatcher.forward(request, response);

	}

	private void modificarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		Alumno a = null;
		a = validateValues(request, response, true);
		if (a != null) {
			// if (!modeloAlumno.update(a, a.getId())) {
			if (ConnectionFactory.getInstance().getDAOAlumno().update(a, a.getId())) {
				log.debug("Se ha modificado el alumno " + a.getId());
			} else {
				log.warn("No se ha podido modificar el alumno " + a.getId());
			}
		}
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		dispatcher.forward(request, response);

	}

	private void borrarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Alumno a = null;
		int id = parseIdToInt(request);
		if (this.modeloAlumno.delete(id)) {
			log.debug("Se ha borrado el alumno " + id);
		} else {
			log.warn("No se ha podido borrar el alumno " + id);
		}
		request.setAttribute("mostrarLista", true);
		doGet(request, response);
	}

	/**
	 * Valida un alumno. Añade al request el Alumno generado con el nombre
	 * "detalle". También se añaden al request si se ha podido meter bien el
	 * parametro o no.
	 * 
	 * Atributos añadidos al request
	 * <ul>
	 * <li>detalle -> Objeto de tipo alumno</li>
	 * <li>isNombreValid -> [false] si ha surgido algun error al settear el
	 * nombre, [true] si todo ha ido bien</li>
	 * <li>isApellidoValid -> [false] si ha surgido algun error al settear el
	 * apellido, [true] si todo ha ido bien</li>
	 * <li>isEmailValid -> [false] si ha surgido algun error al settear el
	 * email, [true] si todo ha ido bien</li>
	 * <li>isEdadValid -> [false] si ha surgido algun error al settear el edad,
	 * [true] si todo ha ido bien</li>
	 * <li>isDniValid -> [false] si ha surgido algun error al settear el dni,
	 * [true] si todo ha ido bien</li>
	 * </ul>
	 * 
	 * @param request
	 * @param response
	 * @return devuelve un alumno validado, si algun parametro ha dado error al
	 *         settear devuelve null
	 */
	private Alumno validateValues(HttpServletRequest request, HttpServletResponse response, boolean isUpdate) {
		boolean isNombreValid = false;
		boolean isApellidoValid = false;
		boolean isEmailValid = false;
		boolean isEdadValid = false;
		boolean isDniValid = false;

		Alumno a = null;

		try {
			a = new Alumno();
			a.setId(parseIdToInt(request));
		} catch (AlumnoException | LibroException e) {
			e.printStackTrace();
		}

		try {
			a.setNombre(request.getParameter("nombre"));
			isNombreValid = true;
		} catch (AlumnoException e) {
		}

		try {
			a.setApellido(request.getParameter("apellido"));
			isApellidoValid = true;
		} catch (AlumnoException e) {
		}

		try {
			a.setEmail(request.getParameter("email"));
			isEmailValid = true;
		} catch (AlumnoException e) {
		}

		try {
			a.setEdad(Integer.parseInt(request.getParameter("edad")));
			isEdadValid = true;
		} catch (Exception e) {
			// Al parsear "" lanza otra excecpion, por eso no usamos
			// AlumnoException
		}

		try {
			a.setDni(request.getParameter("dni"));
			isDniValid = true;
		} catch (AlumnoException e) {
		}

		request.setAttribute("detalle", a);
		request.setAttribute("isNombreValid", isNombreValid);
		request.setAttribute("isApellidoValid", isApellidoValid);
		request.setAttribute("isEmailValid", isEmailValid);
		request.setAttribute("isEdadValid", isEdadValid);
		request.setAttribute("isDniValid", isDniValid);

		if (!isNombreValid || !isApellidoValid || !isEmailValid || !isEdadValid || !isDniValid) {
			if (isUpdate) {
				a.setId(-1);
			}
			return null;
		}

		return a;
	}

	private int parseIdToInt(HttpServletRequest request) {
		int id = -1;
		try {
			id = (Integer.parseInt(request.getParameter("id")));
		} catch (Exception e) {
		}
		return id;
	}
}
