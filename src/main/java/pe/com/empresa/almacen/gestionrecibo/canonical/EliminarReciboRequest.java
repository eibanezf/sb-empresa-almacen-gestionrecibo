package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.EliminarReciboRequestType;

public class EliminarReciboRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private EliminarReciboRequestType eliminarReciboRequest;

	public EliminarReciboRequestType getEliminarReciboRequest() {
		return eliminarReciboRequest;
	}

	public void setEliminarReciboRequest(EliminarReciboRequestType eliminarReciboRequest) {
		this.eliminarReciboRequest = eliminarReciboRequest;
	}

}
