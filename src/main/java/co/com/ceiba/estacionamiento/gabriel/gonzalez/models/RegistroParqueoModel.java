package co.com.ceiba.estacionamiento.gabriel.gonzalez.models;

import java.util.Calendar;

public class RegistroParqueoModel {


	private Calendar fechaIngreso;  //Obligatorio
	private Calendar fechaSalida;   //Opcional
	private Double valorpago;       //Opcional
	private String estado;          //Obligatorio
	private VehiculoModel vehiculo; //Obligatorio
	
	
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

	public VehiculoModel getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoModel vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
