package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.EliminarReciboResponseType;

public class EliminarReciboResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private EliminarReciboResponseType eliminarReciboResponse;

	public EliminarReciboResponseType getEliminarReciboResponse() {
		return eliminarReciboResponse;
	}

	public void setEliminarReciboResponse(EliminarReciboResponseType eliminarReciboResponse) {
		this.eliminarReciboResponse = eliminarReciboResponse;
	}

}
