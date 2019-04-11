package co.com.ceiba.estacionamiento.gabriel.gonzalez.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Vehiculos")
public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@NotNull
	private String placa;

	@NotNull
	private String tipo;

	private String cilindraje;

	@OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RegistroParqueo> registros;

	public Vehiculo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}

	public List<RegistroParqueo> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroParqueo> registros) {
		this.registros = registros;
	}

}
