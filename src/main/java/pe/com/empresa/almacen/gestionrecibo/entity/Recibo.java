package pe.com.empresa.almacen.gestionrecibo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "RECIBO", schema = "EIBANEZF")
public class Recibo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RECIBO_GENERATOR")
	@SequenceGenerator(name = "SEQ_RECIBO_GENERATOR", schema = "EIBANEZF", sequenceName = "SEQ_RECIBO", allocationSize = 0)
	@Column(name = "ID_RECIBO", updatable = false, nullable = false)
	private Long idRecibo;

	@Column(name = "FECHA_SALIDA")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-05:00")
	private Date fechaSalida;

	@Column(name = "PERSONA_RECIBE")
	private String personaRecibe;

	@Column(name = "PERSONA_ENTREGA")
	private String personaEntrega;

	@Column(name = "ID_AREA")
	private Long idArea;

	public Long getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(Long idRecibo) {
		this.idRecibo = idRecibo;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
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

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

}
