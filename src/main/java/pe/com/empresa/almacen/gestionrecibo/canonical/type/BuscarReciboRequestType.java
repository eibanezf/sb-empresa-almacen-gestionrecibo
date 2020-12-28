package pe.com.empresa.almacen.gestionrecibo.canonical.type;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionrecibo.util.Constantes;

public class BuscarReciboRequestType implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = Constantes.NOT_NULL)
	private String idRecibo;

	public String getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(String idRecibo) {
		this.idRecibo = idRecibo;
	}

}
