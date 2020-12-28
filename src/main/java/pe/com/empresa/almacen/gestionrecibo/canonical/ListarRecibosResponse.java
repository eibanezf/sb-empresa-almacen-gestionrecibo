package pe.com.empresa.almacen.gestionrecibo.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionrecibo.canonical.type.ListarRecibosResponseType;

public class ListarRecibosResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private ListarRecibosResponseType listarRecibosResponse;

	public ListarRecibosResponseType getListarRecibosResponse() {
		return listarRecibosResponse;
	}

	public void setListarRecibosResponse(ListarRecibosResponseType listarRecibosResponse) {
		this.listarRecibosResponse = listarRecibosResponse;
	}

}
