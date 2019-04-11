package co.com.ceiba.estacionamiento.gabriel.gonzalez.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Vehiculo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.services.IVehiculoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoServiceImplTest {
	
	

	@Autowired
	private  IVehiculoService  vehiculoService;
	
	
	private Vehiculo vehiculoDado;
	private Vehiculo vehiculoObtenido;
	
	
	@Before
	public void setUp() {
		
		vehiculoDado = new Vehiculo();
		vehiculoDado.setPlaca("DHU-403");
		vehiculoDado.setTipo("CARRO");
				
	}
	
	
	@Test
	public void saveTest() {			
		vehiculoObtenido=vehiculoService.save(vehiculoDado);	
		assertThat(vehiculoDado.getPlaca().equals(vehiculoObtenido.getPlaca())).isTrue();
		
	}
	
	@Test
	public void findAllTest() {
		
		
	}

}
