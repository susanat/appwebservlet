package com.ipartek.formacion.egunon.bean;

import com.ipartek.formacion.egunon.enumeration.TIPO_MENSAJE;

/**
 * Encapsula los mensajes para mostrar en el front-end o capda de la Vista de la
 * AppWeb
 * 
 * @author Ioritz Bereikua Etxebarria
 * @version 1.0
 */
public class Mensaje {
	private String msg;
	private int codigo;
	private TIPO_MENSAJE tipo;

	public Mensaje(int codigo) {
		super();
		this.codigo = codigo;
		switch (codigo) {
		case 0x0:
			this.msg = "Te has logeado";
			this.tipo = TIPO_MENSAJE.INFO;
			break;

		case 0x1:
			this.msg = "Te has desconectado";
			this.tipo = TIPO_MENSAJE.INFO;
			break;

		case 0x2:
			this.msg = "Usuario o Password incorrecto";
			this.tipo = TIPO_MENSAJE.ERROR;
			break;

		default:
			break;
		}
	}

	public Mensaje(String msg, int codigo, TIPO_MENSAJE tipo) {
		super();
		this.msg = msg;
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public TIPO_MENSAJE getTipo() {
		return this.tipo;
	}

	public void setTipo(TIPO_MENSAJE tipo) {
		this.tipo = tipo;
	}

}
