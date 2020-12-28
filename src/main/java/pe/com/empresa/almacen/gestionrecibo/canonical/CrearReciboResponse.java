package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.CrearReciboResponseType;

public class CrearReciboResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private CrearReciboResponseType crearReciboResponse;

	public CrearReciboResponseType getCrearReciboResponse() {
		return crearReciboResponse;
	}

	public void setCrearReciboResponse(CrearReciboResponseType crearReciboResponse) {
		this.crearReciboResponse = crearReciboResponse;
	}

}
