package co.com.ceiba.estacionamiento.gabriel.gonzalez.util;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class CalculoPagoUtility implements ICalculaPagoUtility {

	public static final int HOURSPERDAY = 24;
	public static final int SECONDSPERMINUTE = 60;
	public static final int MINUTESPERHOUR = 60;
	public static final int MILLISECONDSPERSECOND = 1000;
	public static final double ADDITIONALVALUEFORMC = 2000;
	public static final int LOWERLIMITHIGHCYLINDER = 500;
	public static final int LOWERLIMIT = 500;
	public static final int NUMBERHOURSCHARGEASDAY = 9;

	/*
	 * Metodo para obtener el valor a cobrar por la estadia
	 * de un vehiculo en el parqueadero.
	 * 
	 * @param fechaIngreso fecha ingreso de un vehiculo
	 * @param fechaSalida fecha salida de un vehiculo
	 * @return long total horas a cobrar
	 */
	@Override
	public double calcularpago(Calendar fechaIngreso, Calendar fechaSalida, String cilindraje, double tarifaDia,
			double tarifaHora, String tipo) {

		long horasEstadia;
		int diasACobrar;
		int horasACobrar;

		double costodia;
		double coostohora;
		double valorPago;

		horasEstadia = calculaHoras(fechaIngreso, fechaSalida);

		//total de dias enteros de estadia
		diasACobrar = (int) horasEstadia / HOURSPERDAY;
		//horas restantes
		horasACobrar = (int) horasEstadia % HOURSPERDAY;

		//Si las horas restante sobrepasan el limite
		//se incrementa 1 dia
		if (horasACobrar >= NUMBERHOURSCHARGEASDAY) {
			diasACobrar = diasACobrar + 1;
			horasACobrar = 0;
		}

		costodia = diasACobrar * tarifaDia;
		coostohora = horasACobrar * tarifaHora;

		valorPago = costodia + coostohora;

		if (tipo != null && tipo.equals(TipoVehiculoEnum.MOTO.toString())) {

			if (Integer.parseInt(cilindraje) > LOWERLIMITHIGHCYLINDER) {

				valorPago = valorPago + ADDITIONALVALUEFORMC;
			}

		}
		return valorPago;

	}

	/*
	 * Metodo para obtener el total de horas a cobrar.
	 * 
	 * @param fechaIngreso fecha ingreso de un vehiculo
	 * @param fechaSalida fecha salida de un vehiculo
	 * @return long total horas a cobrar
	 */
	@Override
	public long calculaHoras(Calendar fechaIngreso, Calendar fechaSalida) {

		long cantidadTMinutos;
		long horas;
		long minutos;

		// Numero total de horas que hay entre las dos Fechas
		cantidadTMinutos = cantidadTotalMinutos(fechaIngreso, fechaSalida);

		/*
		 * Llamamos el metodo diferenciaHorasDias y diferenciaHoras y retamos para que
		 * nos de el total de horas
		 */

	    //Cantidad de horas
		horas = cantidadTMinutos / MINUTESPERHOUR;

		//minutos restantes
		minutos = cantidadTMinutos % MINUTESPERHOUR;

		//Si hay minutos restantes se incrementa 1 hora
		if (minutos > 0) {
			horas = horas + 1;
		}

		return horas;

	}

	/*
	 * Metodo para obtener el total de minutos de permanencia
	 * entre la fecha de ingreso y salida.
	 * 
	 * @param fechaIngreso fecha ingreso de un vehiculo
	 * @param fechaSalida fecha salida de un vehiculo
	 * @return long total minutos permanencia del vehiculo
	 */
	@Override
	public long cantidadTotalMinutos(Calendar fechaIngreso, Calendar fechaSalida) {

		long totalMinutos = 0;
		// Calcula la diferencia en milisengudos,la pasa a segundos
		// y finalmente la pasa a minutos
		totalMinutos = ((fechaSalida.getTimeInMillis() - fechaIngreso.getTimeInMillis()) / MILLISECONDSPERSECOND
				/ SECONDSPERMINUTE);
		return totalMinutos;
	}


	


}
