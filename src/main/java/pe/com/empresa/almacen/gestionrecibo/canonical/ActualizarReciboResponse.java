package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.ActualizarReciboResponseType;

public class ActualizarReciboResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private ActualizarReciboResponseType actualizarReciboResponse;

	public ActualizarReciboResponseType getActualizarReciboResponse() {
		return actualizarReciboResponse;
	}

	public void setActualizarReciboResponse(ActualizarReciboResponseType actualizarReciboResponse) {
		this.actualizarReciboResponse = actualizarReciboResponse;
	}

}
