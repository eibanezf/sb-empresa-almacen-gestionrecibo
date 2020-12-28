package pe.com.empresa.almacen.gestionrecibo.canonical.type;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pe.com.empresa.almacen.gestionrecibo.util.Constantes;

public class ActualizarReciboRequestType implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = Constantes.NOT_NULL)
	private Long idRecibo;

	@NotNull(message = Constantes.NOT_NULL)
	private String fechaSalida;

	@NotNull(message = Constantes.NOT_NULL)
	@Size(message = Constantes.SIZE, max = 45)
	private String personaRecibe;

	@NotNull(message = Constantes.NOT_NULL)
	@Size(message = Constantes.SIZE, max = 45)
	private String personaEntrega;

	@NotNull(message = Constantes.NOT_NULL)
	private Long idArea;

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getPersonaRecibe() {
		return personaRecibe;
	}

	public void setPersonaRecibe(String personaRecibe) {
		this.personaRecibe = personaRecibe;
	}

	public String getPersonaEntrega() {
		return personaEntrega;
	}

	public void setPersonaEntrega(String personaEntrega) {
		this.personaEntrega = personaEntrega;
	}

	public Long getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(Long idRecibo) {
		this.idRecibo = idRecibo;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

}
