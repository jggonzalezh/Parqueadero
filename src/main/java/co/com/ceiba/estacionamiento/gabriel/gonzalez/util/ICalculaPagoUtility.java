package co.com.ceiba.estacionamiento.gabriel.gonzalez.util;

import java.util.Calendar;

public interface ICalculaPagoUtility {
	
	public double calcularpago(Calendar fechaInicial, Calendar fechaFinal, String cilindraje, double tarifaDia,double tarifaHora,String tipo); 
	public long calculaHoras(Calendar calFechaInicial, Calendar calFechaFinal);
	public long cantidadTotalMinutos(Calendar fechaInicial, Calendar fechaFinal);

}
