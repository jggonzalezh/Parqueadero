package co.com.ceiba.estacionamiento.gabriel.gonzalez.models;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.TipoVehiculoEnum;

public class TarifaModel {
	
	private Double  valor;
	private TipoVehiculoEnum  tipoVehiculo;
	private String  unidadCobro;
	
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
	public String getUnidadCobro() {
		return unidadCobro;
	}
	public void setUnidadCobro(String unidadCobro) {
		this.unidadCobro = unidadCobro;
	}
	
}
