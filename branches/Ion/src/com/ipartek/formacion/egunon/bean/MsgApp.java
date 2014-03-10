package com.ipartek.formacion.egunon.bean;

import com.ipartek.formacion.egunon.bean.enumeracion.TipoMsg;

/**
 * Clase que crea objetos de tipo mensaje para cuando un usuario realiza acciones en el AppWeb
 * 
 * @author Erlantz Romero Parra
 * @version 1.0
 */
public class MsgApp {

	private int codigo;
	private TipoMsg tipo;
	private String mensaje;

	public MsgApp(final String mensaje, final int codigo, final TipoMsg tipo) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public TipoMsg getTipo() {
		return this.tipo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setTipo(TipoMsg tipo) {
		this.tipo = tipo;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "MsgApp [codigo=" + codigo + ", tipo=" + tipo + ", mensaje="
				+ mensaje + "]";
	}

}
