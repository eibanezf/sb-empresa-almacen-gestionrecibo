package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.BuscarReciboRequestType;

public class BuscarReciboRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private BuscarReciboRequestType buscarReciboRequest;

	public BuscarReciboRequestType getBuscarReciboRequest() {
		return buscarReciboRequest;
	}

	public void setBuscarReciboRequest(BuscarReciboRequestType buscarReciboRequest) {
		this.buscarReciboRequest = buscarReciboRequest;
	}

}
