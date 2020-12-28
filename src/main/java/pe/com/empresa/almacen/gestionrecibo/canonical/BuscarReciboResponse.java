package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.BuscarReciboResponseType;

public class BuscarReciboResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private BuscarReciboResponseType buscarReciboResponse;

	public BuscarReciboResponseType getBuscarReciboResponse() {
		return buscarReciboResponse;
	}

	public void setBuscarReciboResponse(BuscarReciboResponseType buscarReciboResponse) {
		this.buscarReciboResponse = buscarReciboResponse;
	}

}
