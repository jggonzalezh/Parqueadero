package co.com.ceiba.estacionamiento.gabriel.gonzalez.databuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.RegistroParqueoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.VehiculoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.EstadoEnum;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.TipoVehiculoEnum;

public class RegistroParqueoModelBuilder {
		
	private Calendar fechaIngreso;  
	private Calendar fechaSalida;   
	private Double   valorpago;       
	private String   estado;
	private VehiculoModel vehiculo;
	private  RegistroParqueoModel registroParqueoModel;
	
	public RegistroParqueoModelBuilder() {
		vehiculo =new VehiculoModel();
		registroParqueoModel=new RegistroParqueoModel();
		
	}
	
	public RegistroParqueoModelBuilder(TipoVehiculoEnum tipo) {
		
		this.fechaIngreso = Calendar.getInstance();
		this.estado =EstadoEnum.ACTIVO.toString() ;
		vehiculo = new VehiculoModelBuider(tipo).build();
		registroParqueoModel=new RegistroParqueoModel();
	}

	public RegistroParqueoModelBuilder withFechaIngreso(Calendar fechaIngreso){
		this.fechaIngreso=fechaIngreso;
		return this;
	}
	
	public RegistroParqueoModelBuilder withFechaSalida(Calendar fechaSalida) {
		this.fechaSalida=fechaSalida;
		return this;
	}
	
	public RegistroParqueoModelBuilder withValorPago(Double   valorpago) {
      this.valorpago=valorpago;
      return this;
	}
	
	public RegistroParqueoModelBuilder  withEstado(String   estado) {
		this.estado=estado;
		return this;
		
	}
	
	
	public RegistroParqueoModelBuilder  withVehiculo(VehiculoModel vehiculo) {
		
		this.vehiculo=vehiculo;
		return this;
	}
	
	
	public RegistroParqueoModel  build() {
		registroParqueoModel.setFechaIngreso(this.fechaIngreso);	
		registroParqueoModel.setFechaSalida(this.fechaSalida);
		registroParqueoModel.setValorpago(this.valorpago);
		registroParqueoModel.setEstado(this.estado);
		registroParqueoModel.setVehiculo(this.vehiculo);
		
		return registroParqueoModel; 
	}

}
