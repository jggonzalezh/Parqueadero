package co.com.ceiba.estacionamiento.gabriel.gonzalez.util;

import java.math.BigInteger;
import java.util.Calendar;
import org.springframework.stereotype.Component;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.exceptions.ParkApiRestException;

@Component
public class ValidationUtility implements IValidationUtility {

	public static final int POSCHAR = 0;
	public static final char CHARACTER = 'A';
	protected static final int[] DIASPERMITIDOS = { Calendar.SUNDAY, Calendar.MONDAY };
	public static final int MAXCANTMOTO =  10;
	public static final int MAXCANTCARRO = 20;

	@Override
	public void validateVehiculoType(String tipo) {

		if (!tipo.equals(TipoVehiculoEnum.CARRO.toString()) && !tipo.equals(TipoVehiculoEnum.MOTO.toString()))
			throw new ParkApiRestException("El tipo de vehiculo " + tipo + " no es recibido");
	}

	@Override
	public void validateHaveRegister(String placa, BigInteger id) {

		if (id != null)
			throw new ParkApiRestException("Un Vehiculo con placa " + placa + " ya registro entrada");

	}

	@Override
	public void validateCapacity(String tipo, BigInteger conteo) {

		switch (tipo) {
		case "MOTO":
			if (conteo.intValue() == MAXCANTMOTO)
				throw new ParkApiRestException("Capacacidad maxima de " + tipo + " alcanzada");
			break;
		case "CARRO":
			if (conteo.intValue() == MAXCANTCARRO)
				throw new ParkApiRestException("Capacacidad maxima de " + tipo + " alcanzada");
			break;
		}

	}

	@Override
	public void validatePlacaDia(String placa) {

		if (placa.charAt(POSCHAR) == CHARACTER && validateDay()) {
			throw new ParkApiRestException("Este vehiculo solo puede ingresar los Lunes y Domingos");
		}

	}

	public Boolean validateDay() {

		for (int i = 0; i < DIASPERMITIDOS.length; i++) {
			if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == DIASPERMITIDOS[i]) {
				return Boolean.valueOf(false);
			}
		}
		return Boolean.valueOf(true);

	}

}
