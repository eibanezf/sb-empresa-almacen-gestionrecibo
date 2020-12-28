package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.ActualizarReciboRequestType;

public class ActualizarReciboRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private ActualizarReciboRequestType actualizarReciboRequest;

	public ActualizarReciboRequestType getActualizarReciboRequest() {
		return actualizarReciboRequest;
	}

	public void setActualizarReciboRequest(ActualizarReciboRequestType actualizarReciboRequest) {
		this.actualizarReciboRequest = actualizarReciboRequest;
	}

}
