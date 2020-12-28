package pe.com.empresa.almacen.gestionrecibo.canonical.type;

import java.io.Serializable;
import java.util.List;

import pe.com.empresa.almacen.gestionrecibo.entity.Recibo;

public class ListarRecibosResponseType implements Serializable {
	private static final long serialVersionUID = 1L;

	private HeaderResponseType headerResponse;
	private List<Recibo> recibos;

	public HeaderResponseType getHeaderResponse() {
		return headerResponse;
	}

	public void setHeaderResponse(HeaderResponseType headerResponse) {
		this.headerResponse = headerResponse;
	}

	public List<Recibo> getRecibos() {
		return recibos;
	}

	public void setRecibos(List<Recibo> recibos) {
		this.recibos = recibos;
	}

}
