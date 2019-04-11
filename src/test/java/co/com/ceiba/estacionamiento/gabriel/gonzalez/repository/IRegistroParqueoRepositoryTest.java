	package co.com.ceiba.estacionamiento.gabriel.gonzalez.repository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.RegistroParqueo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Vehiculo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.IRegistroParqueoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IRegistroParqueoRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IRegistroParqueoRepository iRegistroParqueoRepository;

	private Vehiculo vehiculoDado;

	private RegistroParqueo registroDado;

	@Before
	public void setRegistro() {

		// given
		vehiculoDado = new Vehiculo();
		vehiculoDado.setPlaca("DHU-403");
		vehiculoDado.setTipo("CARRO");

		entityManager.persist(vehiculoDado);
		entityManager.flush();

		registroDado = new RegistroParqueo();
		registroDado.setFechaIngreso(Calendar.getInstance());
		registroDado.setVehiculo(vehiculoDado);

		entityManager.persist(registroDado);
		entityManager.flush();
	}

	@Test
	public void vehiculoHasRegisterEntryTest() {
		// when
		BigInteger idRegistroEncontrado = iRegistroParqueoRepository.vehiculoHasRegisterEntry(vehiculoDado.getPlaca())
				.orElse(null);
		// then
		assertNotNull(idRegistroEncontrado);
	}

	@Test
	public void countVehiculosTest() {
		// when
		BigInteger conteo = iRegistroParqueoRepository.countVehiculosByType("CARRO").orElse(null);
		// then
		assertEquals(17, conteo.intValue());

	}

}
