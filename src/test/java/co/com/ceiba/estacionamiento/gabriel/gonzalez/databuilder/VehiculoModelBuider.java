package co.com.ceiba.estacionamiento.gabriel.gonzalez.databuilder;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.VehiculoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.TipoVehiculoEnum;

public class VehiculoModelBuider {

	private String placa; // Obligatorio
	private TipoVehiculoEnum tipo; // Obligatorio
	private String cilindraje; // Opcional
	private VehiculoModel vehiculoModel;//Obligatorio
	
	public VehiculoModelBuider() {
		this.vehiculoModel = new VehiculoModel();		
	}

	public VehiculoModelBuider(TipoVehiculoEnum tipo) {
		
       if(tipo.toString().equals(TipoVehiculoEnum.CARRO.toString())) {
		this.placa = "BCD101";
		this.tipo = tipo;
		this.vehiculoModel = new VehiculoModel();	
       }else {
    	   this.placa = "CDE10";
   		   this.tipo = tipo;
   		   this.cilindraje="600";
   		   this.vehiculoModel = new VehiculoModel();	
       }
	}

	public VehiculoModelBuider withPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoModelBuider withTipo(TipoVehiculoEnum tipo) {
		this.tipo = tipo;
		return this;
	}

	public VehiculoModelBuider buidCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public VehiculoModel build() {

		vehiculoModel.setPlaca(this.placa);
		vehiculoModel.setTipo(this.tipo);
		vehiculoModel.setCilindraje(this.cilindraje);
		return vehiculoModel;

	}

}
