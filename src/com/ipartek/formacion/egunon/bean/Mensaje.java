package com.ipartek.formacion.egunon.bean;

/**
 * Encapsula los mensajes para mostrar en el front-end o capa de la Vista de la AppWeb
 * 
 * @author Erlantz Romero Parra
 * @version 1.0
 * 
 */
public class Mensaje {
	private String msg;
	private int codigo;
	private TIPO_MENSAJE tipo;

	public Mensaje(final String msg, final int codigo, final TIPO_MENSAJE tipo) {
		super();
		this.msg = msg;
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public String getMsg() {
		return this.msg;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public TIPO_MENSAJE getTipo() {
		return this.tipo;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setTipo(TIPO_MENSAJE tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Mensaje [msg=" + this.msg + ", codigo=" + this.codigo
				+ ", tipo=" + this.tipo + "]";
	}

	/**
	 * clase interna para soportar los tipos de mensaje a mostrar
	 */
	enum TIPO_MENSAJE {
		INFO, WARNING, ERROR
	}

}
