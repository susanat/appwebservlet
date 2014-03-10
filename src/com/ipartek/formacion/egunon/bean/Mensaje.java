package com.ipartek.formacion.egunon.bean;

import com.ipartek.formacion.enumeraciones.TIPO_MENSAJE;

/**
 * Encapsula los mensajes para mostrar en el front-end de la AppWeb
 * @author Patricia Navascués
 * @version 1.0
 *
 */
public class Mensaje {
	
	private String mensaje;
	private String codigo;
	private TIPO_MENSAJE tipo;
			
	public Mensaje(String mensaje, String codigo, TIPO_MENSAJE tipo) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.tipo = tipo;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public TIPO_MENSAJE getTipo() {
		return tipo;
	}


	public void setTipo(TIPO_MENSAJE tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "Mensajes [mensaje=" + mensaje + ", codigo=" + codigo + ", tipo=" + tipo + "]";
	}
	
	

}
