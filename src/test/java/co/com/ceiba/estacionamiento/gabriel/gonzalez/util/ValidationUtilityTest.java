package co.com.ceiba.estacionamiento.gabriel.gonzalez.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.exceptions.ParkApiRestException;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.IValidationUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationUtilityTest {

	@Autowired
	private IValidationUtility validation;

	private String placa = "DHU-403";
	private String tipoInvalido = "CAMION";
	private String PlacastarsByA = "ABC-708";
	private BigInteger id= new BigInteger("1");


	@Test
	public void whenInvalidType_thenvalidateEntryThrowsExc() {

		try {
			validation.validateVehiculoType(tipoInvalido);
		} catch (ParkApiRestException e) {
			assertThat(e.getMessage(), is("El tipo de vehiculo " + tipoInvalido + " no es recibido"));
		}

	}

	@Test
	public void whenHaveRegister_thenvalidateEntryThrowsExc() {

		try {
			validation.validateHaveRegister(placa, id);
		} catch (ParkApiRestException e) {
			assertThat(e.getMessage(), is("Un Vehiculo con placa " + placa + " ya registro entrada"));
		}

	}

	@Test
	public void whenPlacaStarsByA_thenvalidateEntryThrowsExc() {

		try {
			validation.validatePlacaDia(PlacastarsByA);
		} catch (ParkApiRestException e) {
			assertThat(e.getMessage(), is("Este vehiculo solo puede ingresar los Lunes y Domingos"));
		}

	}

	@Test
	public void whenCapacityIsAchieve_thenvalidateEntryThrowsExc() {
		
		String tipo=TipoVehiculoEnum.CARRO.toString();
		 
		try {
			validation.validateCapacity(tipo,BigInteger.valueOf(ValidationUtility.MAXCANTCARRO) );
		} catch (ParkApiRestException e) {
			assertThat(e.getMessage(), is("Capacacidad maxima de " + tipo + " alcanzada"));
		}

	}
	
	@Test
	public void whenMotoCapacityIsAchieve_thenvalidateEntryThrowsExc() {
		
		String tipo=TipoVehiculoEnum.MOTO.toString();
		
		try {
			validation.validateCapacity(TipoVehiculoEnum.MOTO.toString(),BigInteger.valueOf(ValidationUtility.MAXCANTMOTO) );
		} catch (ParkApiRestException e) {
			assertThat(e.getMessage(), is("Capacacidad maxima de " + tipo + " alcanzada"));
		}

	}


}
