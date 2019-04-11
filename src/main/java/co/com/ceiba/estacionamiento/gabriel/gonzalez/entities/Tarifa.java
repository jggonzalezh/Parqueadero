package co.com.ceiba.estacionamiento.gabriel.gonzalez.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.TipoVehiculoEnum;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.UnidadCobroEnum;

@Entity
@Table(name = "Tarifas")
public class Tarifa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@NotNull
	private Double  valor;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoVehiculoEnum  tipoVehiculo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private UnidadCobroEnum  unidadCobro;
	
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public TipoVehiculoEnum getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public UnidadCobroEnum getUnidadCobro() {
		return unidadCobro;
	}
	public void setUnidadCobro(UnidadCobroEnum unidadCobro) {
		this.unidadCobro = unidadCobro;
	}
	

}
