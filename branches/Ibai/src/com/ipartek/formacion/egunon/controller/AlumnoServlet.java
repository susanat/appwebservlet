package com.ipartek.formacion.egunon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.egunon.bean.Mensaje;
import com.ipartek.formacion.egunon.bean.UserLogin;
import com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE;
import com.ipartek.pruebas.bbdd.ConnectionFactory;
import com.ipartek.pruebas.bbdd.DAOAlumno;
import com.ipartek.pruebas.bbdd.model.ModeloAlumno;
import com.ipartek.pruebas.bean.Alumno;
import com.ipartek.pruebas.exception.AlumnoException;
import com.ipartek.pruebas.exception.LibroException;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends ServletMaestro {
	private static final long serialVersionUID = 1L;
	protected static final Logger log = Logger.getLogger(AlumnoServlet.class);
	public static final String OP_NUEVO_ALUMNO = "Crear";
	public static final String OP_MODIFICAR_ALUMNO = "Modificar";
	public static final String OP_ELIMINAR_ALUMNO = "Borrar";
	public static final String OP_DETALLE_ALUMNO = "detalle";
	public static final String OP_LISTAR_ALUMNO = "listar";
       
	RequestDispatcher dispatcher = null;
	ModeloAlumno modeloALumno;
	String id;
	HttpSession session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	modeloALumno = new ModeloAlumno();
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    	modeloALumno = null;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		id = request.getParameter("id");
		RequestDispatcher dispatcher = null;

		if (id == null)
		{
			log.trace("Lista Alumnos");
			//obtener dispatcher
			dispatcher = request.getRequestDispatcher("alumnoList.jsp");
			
			
		/*	ArrayList<Alumno> lAlumnos = new ArrayList<Alumno>();
			
			ModeloAlumno modeloALumno = new ModeloAlumno();
			lAlumnos = modeloALumno.getAll();*/
			
			
			ArrayList<Alumno> listaAlumnos  = modeloALumno.getAll();
			
			log.debug(listaAlumnos.size() + " alumnos consultados");
			
			//enviamos datos a la request
			request.setAttribute("listaAlumnos", listaAlumnos);
		}else{
			log.trace("Detalle alumno " + id);
			//obtener dispatcher
			dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
			
			ModeloAlumno modeloALumno = new ModeloAlumno();
			Alumno alumno = modeloALumno.getAlumnoById(Integer.parseInt(id));
			
			//enviamos datos a la request
			request.setAttribute("detalleALumno", alumno);
			request.setAttribute("method", "post");
			request.setAttribute("title", "Detalle Alumno");
		}	
		
		
		//redirecionar a la JSP
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Haciendo post");
		//recoger operacion a realizar
		String op = request.getParameter("action");
				
		
		if (OP_NUEVO_ALUMNO.equalsIgnoreCase(op)) {
			crearAlumno(request, response);
		}else if (OP_MODIFICAR_ALUMNO.equalsIgnoreCase(op)) {
			modificarAlumno(request, response);
		}else if (OP_ELIMINAR_ALUMNO.equalsIgnoreCase(op)) {
			eliminarAlumno(request, response);
		}else{
			throw new ServletException("Operacion no soportada " + op);
		}
		log.trace("doPost - Fin");
	}

	private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Eliminar alumno");
		int idAlumno = Integer.parseInt(request.getParameter("id"));

		if (modeloALumno.delete(idAlumno)){
			dispatcher = request.getRequestDispatcher("alumnoList.jsp");
			request.setAttribute("msg", new Mensaje("Alumno eliminado", 2, TIPO_MENSAJE.INFO));
			log.info("Alumno borrado");
		}else{
			//dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
			//request.setAttribute("detalleALumno", idAlumno);
			request.setAttribute("msg", new Mensaje("No se ha podido eliminar el alumno", 2, TIPO_MENSAJE.ERROR));	
			log.error("Error al eliminar el alumno");
		}
		
		// Volver a llamar a doGet
		this.doGet(request, response);
		//dispatcher = request.getRequestDispatcher("alumno");
		log.trace("Fin eliminar alumno");
	}

	private void modificarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Modificar alumno");
		//obtenemos datos del formulario
		int idAlumno = Integer.parseInt(request.getParameter("id"));
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		int edad = Integer.parseInt(request.getParameter("edad"));
		String email = request.getParameter("email");
		
		Alumno a = null;
		
		//guardamos el alumno
		try {
			a = new Alumno();
			a.setDni(dni);
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setEdad(edad);
			a.setEmail(email);
			
			if (!modeloALumno.update(a, idAlumno)){
				log.error("Error al modificar el alumno ["+idAlumno+"] "+ a.toString());
				request.setAttribute("msg", new Mensaje("No se ha podido modificar el alumno", 200, TIPO_MENSAJE.ERROR));
			}

			log.warn("Alumno modificado");
			request.setAttribute("msg", new Mensaje("Alumno modificado", 200, TIPO_MENSAJE.INFO));

		}catch (AlumnoException e) {
			request.setAttribute("msg", new Mensaje(e.getMensajeError(), e.getCodigoError(), TIPO_MENSAJE.ERROR));
			log.warn(e.getCause() + " " + e.getMessage());
		} catch (Exception e){
			request.setAttribute("msg", new Mensaje("Excepcion general", 0, TIPO_MENSAJE.ERROR));
			log.warn("Exception general " + e.getCause() + " " + e.getMessage());
		}
		a.setId(Integer.parseInt(id));
		//redirecionar a la JSP
		request.setAttribute("detalleALumno", a);
		request.setAttribute("title", "Detalles del alumno modificado");
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		dispatcher.forward(request, response);
		log.trace("Fin Modificar alumno");
	}

	private void crearAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("Crear alumno");
		//obtenemos datos del formulario
		int idAlumno = Integer.parseInt(request.getParameter("id"));
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		int edad = Integer.parseInt(request.getParameter("edad"));
		String email = request.getParameter("email");
		
		Alumno a = null;
		
		//guardamos el alumno
		try {
			a = new Alumno();
			a.setDni(dni);
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setEdad(edad);
			a.setEmail(email);
			
			int id =	modeloALumno.insert(a);

			if (id == -1){
				log.warn("Error al insertar el alumno");
				request.setAttribute("msg", new Mensaje("Error al insertar alumno", 200, TIPO_MENSAJE.ERROR));
			}else{
				log.info("Alumno creado " + a.toString());
				request.setAttribute("msg", new Mensaje("Alumno Creado", 200, TIPO_MENSAJE.INFO));
			}
		} catch (AlumnoException e) {
			request.setAttribute("msg", new Mensaje(e.getMensajeError(), e.getCodigoError(), TIPO_MENSAJE.ERROR));
			log.warn(e.getCause() + " " + e.getMessage());
		} catch (Exception e){
			request.setAttribute("msg", new Mensaje("Excepcion general", 0, TIPO_MENSAJE.ERROR));
			log.warn("Exception general " + e.getCause() + " " + e.getMessage());
		}
		//redirecionar a la JSP
		request.setAttribute("detalleALumno", a);
		request.setAttribute("title", "Detalles del alumno insertado");
		dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
		dispatcher.forward(request, response);
		log.trace("Fin crear alumno");
	}

}
