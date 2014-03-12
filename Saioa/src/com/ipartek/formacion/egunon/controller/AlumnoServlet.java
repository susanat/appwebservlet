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
import com.ipartek.formacion.egunon.bean.Mensaje.TIPO_MENSAJE;
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
	private final static Logger log=Logger.getLogger(AlumnoServlet.class);
	public static final String OP_NUEVO_ALUMNO="nuevo";
	public static final String OP_MODIFICAR_ALUMNO="modificar";
	public static final String OP_ELIMINAR_ALUMNO="eliminar";
	public static final String OP_DETALLE_ALUMNO="detalle";
	public static final String OP_LISTAR_ALUMNO="listar";
	private RequestDispatcher dispatcher;
	ModeloAlumno modelAlumno;
    HttpSession session;
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
					
			//enviar datos en la request a la JSP
			ModeloAlumno modelAlumno =new ModeloAlumno();
			
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

	private void eliminarAlumno(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.trace("Eliminar Alumno");
		Alumno a=null;
		//Modificar Alumno
		String id=(String)request.getParameter("id");
		//Modificar
		Mensaje msg=null;
		try {
			a = new Alumno();
		
		    ModeloAlumno model=new ModeloAlumno();
		    if (!model.delete(Integer.parseInt(id))){
		    log.error("No se ha podido eliminar el alumno. Consulte con su administrador");
		    request.setAttribute("msg", new Mensaje("Alumno Modificado",200,Mensaje.TIPO_MENSAJE.ERROR));
		    }
		    log.info("Alumno Eliminado[" + id + "]" );
		    	msg=new Mensaje("Usuario Modificado",703,TIPO_MENSAJE.INFO);
		    	request.setAttribute("msg", new Mensaje("Alumno Eliminado",200,Mensaje.TIPO_MENSAJE.INFO));
		    
		} catch (AlumnoException e1) {
			log.error("Error al introducir el alumno");
			msg=new Mensaje("Usuario No valido",999,TIPO_MENSAJE.INFO);

		} catch (Exception e1) {
			log.warn("Excepcion general" + e1.getMessage());
			 request.setAttribute("msg", new Mensaje("Alumno Modificado",200,Mensaje.TIPO_MENSAJE.ERROR));
		}
		
			request.setAttribute("msg", msg);
			RequestDispatcher dispatcher= request.getRequestDispatcher("alumnoDetalle.jsp");
			dispatcher.forward(request, response);
		log.trace("Eliminar Alumno- Fin");
		
	}

	private void modificarAlumno(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.trace("modificarAlumno");	
		Alumno a = null;
		String nombre=(String)request.getParameter("nombre");
		String id=(String)request.getParameter("id");
		String apellido=(String)request.getParameter("apellido");
		String edad=(String)request.getParameter("edad");
		String dni=(String)request.getParameter("dni");
		int e=Integer.parseInt(edad);
		String email=(String)request.getParameter("email");
		
		// Modificar  Alumno
		try {
			a = new Alumno();
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setDni(dni);
			a.setEdad(Integer.parseInt(edad));
			a.setEmail(email);
			
			//Update into DDBB			
			if ( !modelAlumno.update(a, Integer.parseInt(id) )){
				log.error("No se a ha podido modificar el Alumno ["+id+"] " + a.toString() );			
				request.setAttribute("msg", new Mensaje("No se a ha podido modificar el Alumno, por favor consulte con el Administrador de la AppWeb", 0, Mensaje.TIPO_MENSAJE.ERROR));	
			}else{
				log.info("Alumno Modificado " + a.toString());
				request.setAttribute("msg", new Mensaje("Alumno Modificadp", 200, Mensaje.TIPO_MENSAJE.INFO));
			}	
			
		} catch (AlumnoException e1) {
			log.warn("Datos del Alumno no validos " + e1.getMessage());			
			request.setAttribute("msg", new Mensaje( e1.getMensajeError(), e1.getCodigoError() , Mensaje.TIPO_MENSAJE.ERROR));
			
		} catch (Exception ex) {
			log.warn("Excepcion general " + ex.getMessage());			
			request.setAttribute("msg", new Mensaje("Excepcion general", 100, Mensaje.TIPO_MENSAJE.ERROR));
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

	private void crearAlumno(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.trace("crearAlumno");	
		Alumno a = null;
		String nombre=(String)request.getParameter("nombre");
		String id=(String)request.getParameter("id");
		String apellido=(String)request.getParameter("apellido");
		String edad=(String)request.getParameter("edad");
		String dni=(String)request.getParameter("dni");
		int ed=Integer.parseInt(edad);
		String email=(String)request.getParameter("email");
		
		// crear Alumno
		try {
			a = new Alumno();
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setDni(dni);
			a.setEdad(Integer.parseInt(edad));
			a.setEmail(email);
			// Insert into DDBB			
			modelAlumno.insert(a);
			log.info("Alumno insertado " + a.toString());
			request.setAttribute("msg", new Mensaje("Alumno creado", 200, Mensaje.TIPO_MENSAJE.INFO));
			
		} catch (AlumnoException e) {
			log.warn("Datos del Alumno no validos " + e.getMessage());			
			request.setAttribute("msg", new Mensaje( e.getMensajeError(), e.getCodigoError() , Mensaje.TIPO_MENSAJE.ERROR));
			
		} catch (Exception e) {
			log.warn("Excepcion general " + e.getMessage());			
			request.setAttribute("msg", new Mensaje("Excepcion general",100, Mensaje.TIPO_MENSAJE.ERROR));
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
