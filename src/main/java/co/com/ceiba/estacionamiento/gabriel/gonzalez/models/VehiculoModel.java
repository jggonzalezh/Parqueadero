package co.com.ceiba.estacionamiento.gabriel.gonzalez.models;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.TipoVehiculoEnum;

public class VehiculoModel {

	private String placa;      //Obligatorio
	private TipoVehiculoEnum tipo;       //Obligatorio
	private String cilindraje; //Opcional

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public TipoVehiculoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculoEnum tipo) {
		this.tipo = tipo;
	}

	public String getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}

}
