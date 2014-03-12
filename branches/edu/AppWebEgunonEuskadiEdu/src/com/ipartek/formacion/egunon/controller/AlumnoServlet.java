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
import com.ipartek.pruebas.bean.Calificacion;
import com.ipartek.pruebas.exception.AlumnoException;
import com.ipartek.pruebas.exception.LibroException;

/**
 * Servlet implementation class AlumnoServlet
 */
public class AlumnoServlet extends ServletMaestro {
	
	private static final long serialVersionUID = 1L;
	private final static Logger log=Logger.getLogger(AlumnoServlet.class);
	private RequestDispatcher dispatcher;
	public static final String OP_MODIFICAR_ALUMNO="modificar";
	public static final String OP_BORRAR_ALUMNO="borrar";
	public static final String OP_NUEVO_ALUMNO="nuevo";
	public static final String OP_DETALLE_ALUMNO="detalle";
	public static final String OP_LISTAR_ALUMNO="listar";
	private String title="";
	private ArrayList<Calificacion> calificaciones;
	
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
		ArrayList<Calificacion> calificaciones=new ArrayList<Calificacion>();
		
		
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
		String ac=null;
		//comprobar si es para detalle o listar		
		String idAlumno = request.getParameter("id");
		ac=request.getParameter("ac");
		ModeloAlumno modelAlumno=new ModeloAlumno();
		
		if("calif".equals(ac)){
			//Ver calificaciones
			int id=Integer.parseInt(idAlumno);
			log.trace("Ver calificaciones alumno " + idAlumno);
		    calificaciones=modelAlumno.getAlumnoById(id).getCalificaciones();
		    request.setAttribute("calificaciones", calificaciones);
		    title="Detalle Alumno";
			request.setAttribute("title",title);
			dispatcher = request.getRequestDispatcher("calificacionesAlumno.jsp");
		}else if ( null != idAlumno ){
			//detalle
			int id=Integer.parseInt(idAlumno);
			log.trace("Detalle alumno" + idAlumno);
			
			
			//TODO conectar BBDD obtener Alumnos
			
			
			Alumno alumno=modelAlumno.getAlumnoById(id);
			
			//enviar datos en la request a la JSP
			
			request.setAttribute("detalleAlumno", alumno );
			title="Detalle Alumno";
			request.setAttribute("title",title);
			dispatcher = request.getRequestDispatcher("alumnoDetalle.jsp");
			
		}else{
			//listando
			log.trace("Listado alumnos");
			dispatcher = request.getRequestDispatcher("alumnoList.jsp");
			
			//TODO conectar BBDD obtener Alumnos	
			
			ArrayList<Alumno>listaAlumnos=modelAlumno.getAll();
			//enviar datos en la request a la JSP
			request.setAttribute("listaAlumnos", listaAlumnos );
			
		}
		
		//Redirecionar a la JSP
		dispatcher.forward(request, response);
	    log.trace("dopost-Fin");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.trace("dopost");
		String operacion=request.getParameter("op");
		if (OP_NUEVO_ALUMNO.equalsIgnoreCase(operacion)){
			crearAlumno(request,response);
		}else if (OP_MODIFICAR_ALUMNO.equalsIgnoreCase(operacion)){
			modificarAlumno(request,response);
		}
		else if (OP_BORRAR_ALUMNO.equalsIgnoreCase(operacion)){
			borrarAlumno(request,response);
		}
		
		
			
		
	}
	
	

	private void borrarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Eliminar Alumno
				String id=request.getParameter("id_oculto");
				title="Listado Alumnos";
				request.setAttribute("title",title);
				Mensaje msg=null;
				try {
					
				    ModeloAlumno model=new ModeloAlumno();
				    int id_oculto=Integer.parseInt(id);
				    if (model.delete(id_oculto)){
				    	log.info("Alumno eliminado" + id_oculto);
				    	msg=new Mensaje("Usuario eliminado",703,TIPO_MENSAJE.INFO);
					    request.setAttribute("msg", msg);
					    
					    
				    }else{
				    	log.error("No se ha podido eliminar el alumno" + id_oculto + ". Consultelo con el administrador del sistema" );
				    	
				    }
				    		
				} catch (Exception e2) {
					log.error("Excepcion general");
					msg=new Mensaje("Excepcion general" + e2.getMessage(),700,TIPO_MENSAJE.INFO);
					
				}
				finally{
					request.setAttribute("msg", msg);
					dispatcher= request.getRequestDispatcher("alumnoDetalle.jsp");
					doGet(request, response);
					
					
				}
				
		
	}

	private void modificarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Modificar Alumno
		String nombre=request.getParameter("name");
		String apellido=request.getParameter("apellido");
		String edad=request.getParameter("edad");
		String dni=request.getParameter("dni");
		int e=Integer.parseInt(edad);
		String email=request.getParameter("email");
		title="Modificación Alumnos";
		request.setAttribute("title",title);
		Alumno a;
		Mensaje msg=null;
		try {
			a = new Alumno();
			a.setNombre(nombre);
			a.setApellido(apellido);
			a.setDni(dni);
		    a.setEmail(email);
		    a.setEdad(e);
		    ModeloAlumno model=new ModeloAlumno();
		    String id=request.getParameter("id_oculto");
		    int id_oculto=Integer.parseInt(id);
		    if (model.update(a,id_oculto)){
		    	log.info("Alumno modificado");
		    	a.setId(id_oculto);
		    	msg=new Mensaje("Usuario modificado",703,TIPO_MENSAJE.INFO);
			    request.setAttribute("msg", msg);
			    request.setAttribute("detalleAlumno", a);
			    
		    }else{
		    	log.error("No se ha podido modificar el alumno" + id_oculto + "[" + a.toString() + "]. Consultelo con el administrador del sistema" );
		    	
		    }
		    	
		    
		    
		    
		} catch (AlumnoException e1) {
			log.error("Error al modificar el alumno" + e1.getMensajeError());
			msg=new Mensaje("Usuario No valido" + e1.getMensajeError(),e1.getCodigoError(),TIPO_MENSAJE.INFO);
		    
		    
			
		} catch (Exception e2) {
 			log.error("Excepcion general");
			msg=new Mensaje("Excepcion general" + e2.getMessage(),700,TIPO_MENSAJE.INFO);
			
		}
		finally{
			request.setAttribute("msg", msg);
			dispatcher= request.getRequestDispatcher("alumnoDetalle.jsp");
			dispatcher.forward(request, response);	
			
		}
		
	}

	private void crearAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Insertar Alumno
				String nombre=request.getParameter("name");
				String apellido=request.getParameter("apellido");
				String edad=request.getParameter("edad");
				String dni=request.getParameter("dni");
				int e=Integer.parseInt(edad);
				String email=request.getParameter("email");
				Alumno a;
				Mensaje msg=null;
				try {
					a = new Alumno();
					a.setNombre(nombre);
					a.setApellido(apellido);
					a.setDni(dni);
				    a.setEmail(email);
				    a.setEdad(e);
				    ModeloAlumno model=new ModeloAlumno();
				    if (model.insert(a)!=-1){
				    	log.info("Alumno insertado");
				    	msg=new Mensaje("Usuario Creado",703,TIPO_MENSAJE.INFO);
					    request.setAttribute("msg", msg);
					    request.setAttribute("alumno", a);
					    
				    }
				    	
				    
				    
				    
				} catch (AlumnoException e1) {
					log.error("Error al introducir el alumno");
					msg=new Mensaje("Usuario No valido" + e1.getMensajeError(),e1.getCodigoError(),TIPO_MENSAJE.INFO);
				    
				    
					
				} catch (Exception e2) {
					log.error("Excepcion general");
					msg=new Mensaje("Excepcion general" + e2.getMessage(),700,TIPO_MENSAJE.INFO);
					
				}
				finally{
					request.setAttribute("msg", msg);
					dispatcher= request.getRequestDispatcher("nuevoAlumno.jsp");
					dispatcher.forward(request, response);	
					
				}
				
		
	}

}
