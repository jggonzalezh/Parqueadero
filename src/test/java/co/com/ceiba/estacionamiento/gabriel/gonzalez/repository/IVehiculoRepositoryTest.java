package co.com.ceiba.estacionamiento.gabriel.gonzalez.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Vehiculo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.IVehiculoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IVehiculoRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IVehiculoRepository ivehiculoRepository;

	private Vehiculo vehiculoDado;

	@Before
	public void setRegistro() {

		// given
		vehiculoDado = new Vehiculo();
		vehiculoDado.setPlaca("DHU-403");
		vehiculoDado.setTipo("CARRO");

		entityManager.persist(vehiculoDado);
		entityManager.flush();
	}

	@Test
	public void findbyPlacaTest() {
		// when
		Vehiculo vehiculoEncontrado = ivehiculoRepository.findByPlaca(vehiculoDado.getPlaca());
		// then
		assertThat(vehiculoDado.getPlaca()).isEqualTo(vehiculoEncontrado.getPlaca());
	}

}
