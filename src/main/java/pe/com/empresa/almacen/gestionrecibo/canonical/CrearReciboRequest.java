package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.CrearReciboRequestType;

public class CrearReciboRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private CrearReciboRequestType crearReciboRequest;

	public CrearReciboRequestType getCrearReciboRequest() {
		return crearReciboRequest;
	}

	public void setCrearReciboRequest(CrearReciboRequestType crearReciboRequest) {
		this.crearReciboRequest = crearReciboRequest;
	}

}
