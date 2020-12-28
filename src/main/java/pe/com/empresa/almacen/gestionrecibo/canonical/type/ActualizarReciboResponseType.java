package pe.com.empresa.almacen.gestionrecibo.canonical.type;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionrecibo.entity.Recibo;

public class ActualizarReciboResponseType implements Serializable {
	private static final long serialVersionUID = 1L;

	private HeaderResponseType headerResponse;
	private Recibo recibo;

	public HeaderResponseType getHeaderResponse() {
		return headerResponse;
	}

	public void setHeaderResponse(HeaderResponseType headerResponse) {
		this.headerResponse = headerResponse;
	}

	public Recibo getRecibo() {
		return recibo;
	}

	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}

}
