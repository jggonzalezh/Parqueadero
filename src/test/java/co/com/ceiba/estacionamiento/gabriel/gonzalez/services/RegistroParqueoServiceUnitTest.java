package co.com.ceiba.estacionamiento.gabriel.gonzalez.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.exceptions.ParkApiRestException;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.RegistroParqueoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.VehiculoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.IRegistroParqueoRepository;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.IValidationUtility;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
public class RegistroParqueoServiceUnitTest {

	@MockBean
	private IRegistroParqueoRepository registroParqueoRepository;
	@MockBean
	private IValidationUtility validationUtility;

	private VehiculoModel vehiculoDado;
	private RegistroParqueoModel registroDado;
	private String tipoInvalido = "CAMION";
	private String PlacastarsByA ="ABC-708";
	private BigInteger id;
	private BigInteger conteo;
	private Optional<BigInteger> opId;
	private Optional<BigInteger> opConteo;

	@Before
	public void setUp() {

		vehiculoDado = new VehiculoModel();
		vehiculoDado.setPlaca("DHU-403");
		vehiculoDado.setTipo(TipoVehiculoEnum.CARRO);

		registroDado = new RegistroParqueoModel();
		registroDado.setFechaIngreso(Calendar.getInstance());
		registroDado.setVehiculo(vehiculoDado);

		id = new BigInteger("1");
		opId = Optional.ofNullable(id);
		
		conteo =new BigInteger("20");
		opConteo= Optional.ofNullable(conteo);
		
		
		Mockito.when(registroParqueoRepository.vehiculoHasRegisterEntry(vehiculoDado.getPlaca())).thenReturn(opId);
		
		Mockito.when(registroParqueoRepository.countVehiculosByType(vehiculoDado.getTipo().toString())).thenReturn(opConteo);

		Mockito.doThrow(new ParkApiRestException("El tipo de vehiculo " + tipoInvalido + " no es recibido"))
				.when(validationUtility).validateVehiculoType(tipoInvalido);
		
		Mockito.doThrow( new ParkApiRestException("Un Vehiculo con placa " +vehiculoDado.getPlaca() + " ya registro entrada"))
				 .when(validationUtility).validateHaveRegister(vehiculoDado.getPlaca(), id);
		
		Mockito.doThrow(new ParkApiRestException("Este vehiculo solo puede ingresar los Lunes y Domingos"))
				.when(validationUtility).validatePlacaDia("ABC-708");
	
		Mockito.doThrow(new ParkApiRestException("Capacacidad maxima de " + vehiculoDado.getTipo() + " alcanzada"))
				.when(validationUtility).validateCapacity(vehiculoDado.getTipo().toString(), conteo);
	 
	}

	@Test
	public void whenInvalidType_thenvalidateEntryThrowsExc() {
		

		assertThatThrownBy(() -> validationUtility.validateVehiculoType(tipoInvalido))
				.hasMessage("El tipo de vehiculo " + tipoInvalido + " no es recibido");

	}

	@Test
	public void whenHaveRegister_thenvalidateEntryThrowsExc() {

		BigInteger idObtenido = registroParqueoRepository.vehiculoHasRegisterEntry(vehiculoDado.getPlaca()).orElse(null);
			
		assertThatThrownBy(() ->   validationUtility.validateHaveRegister(vehiculoDado.getPlaca(), idObtenido)  )
				.hasMessage("Un Vehiculo con placa " +vehiculoDado.getPlaca() + " ya registro entrada");

	}
	
	@Test
	public void whenPlacaStarsByA_thenvalidateEntryThrowsExc() {
	
		assertThatThrownBy(() ->   validationUtility.validatePlacaDia(PlacastarsByA)  )
				.hasMessage("Este vehiculo solo puede ingresar los Lunes y Domingos");

	}
	
	@Test
	public void whenCapacityIsAchieve_thenvalidateEntryThrowsExc() {
	
		 BigInteger conteoObtenido= registroParqueoRepository.countVehiculosByType(vehiculoDado.getTipo().toString()).orElse(null);
		
		assertThatThrownBy(() ->   validationUtility.validateCapacity(vehiculoDado.getTipo().toString(),conteoObtenido)  )
				.hasMessage("Capacacidad maxima de " + vehiculoDado.getTipo() + " alcanzada");

	}

}
