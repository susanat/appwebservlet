package com.ipartek.formacion.egunon.bean;

import com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE;

/**
 * Encapsula los mensajes para mostrar en el frontend de la AppWeb
 * @author Eduardo Monterrubio Robledo
 * @version 1.0
 */
public class Mensaje {
	
	private String msg;
	private int codigo;
	private TIPO_MENSAJE tipo;
	
		
	public Mensaje(String msg, int codigo, TIPO_MENSAJE tipo) {
		super();
		this.msg = msg;
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public Mensaje(int codigo) {
		super();
		this.codigo = codigo;
		switch (this.codigo){
			case 0: this.msg = "Te has logeado satisfactoriamente"; this.tipo = TIPO_MENSAJE.INFO;break;
			case 1: this.msg = "Te has desconextado satisfactoriamente"; this.tipo = TIPO_MENSAJE.INFO;break;
			case 2: this.msg = "Usuario o password incorrecto"; this.tipo = TIPO_MENSAJE.ERROR;break;
			case 3: this.msg = "No estás logeado"; this.tipo = TIPO_MENSAJE.WARNING;break;
		}
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public TIPO_MENSAJE getTipo() {
		return tipo;
	}
	public void setTipo(TIPO_MENSAJE tipo) {
		this.tipo = tipo;
	}
	
	

}
