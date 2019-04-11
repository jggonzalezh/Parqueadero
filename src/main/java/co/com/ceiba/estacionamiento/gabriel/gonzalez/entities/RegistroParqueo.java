package co.com.ceiba.estacionamiento.gabriel.gonzalez.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Registros")
public class RegistroParqueo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Calendar fechaIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaSalida;

	private Double valorpago;

	private String estado;

	@ManyToOne(fetch = FetchType.EAGER)
	private Vehiculo vehiculo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Double getValorpago() {
		return valorpago;
	}

	public void setValorpago(Double valorpago) {
		this.valorpago = valorpago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}