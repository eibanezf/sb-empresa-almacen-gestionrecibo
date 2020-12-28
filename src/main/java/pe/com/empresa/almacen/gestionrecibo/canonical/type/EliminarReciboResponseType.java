package pe.com.empresa.almacen.gestionrecibo.canonical.type;

import java.io.Serializable;

public class EliminarReciboResponseType implements Serializable {
	private static final long serialVersionUID = 1L;

	private HeaderResponseType headerResponse;

	public HeaderResponseType getHeaderResponse() {
		return headerResponse;
	}

	public void setHeaderResponse(HeaderResponseType headerResponse) {
		this.headerResponse = headerResponse;
	}

}
