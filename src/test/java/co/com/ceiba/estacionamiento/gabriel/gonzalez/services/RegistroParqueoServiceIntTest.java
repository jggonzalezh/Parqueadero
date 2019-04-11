package co.com.ceiba.estacionamiento.gabriel.gonzalez.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.databuilder.RegistroParqueoModelBuilder;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.databuilder.VehiculoModelBuider;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.RegistroParqueo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Vehiculo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.exceptions.ParkApiRestException;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.RegistroParqueoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.VehiculoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.IRegistroParqueoRepository;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.IVehiculoRepository;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.EstadoEnum;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.TipoVehiculoEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistroParqueoServiceIntTest {

	@Autowired
	private IRegistroParqueoRepository registroParqueoRepository;

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Autowired
	private IRegistroParqueoService registroParqueoService;

	//Entidades Dadas
	private Vehiculo vehiculoUno;
	private Vehiculo vehiculoDos;
	private Vehiculo vehiculoTres;
	private RegistroParqueo registroUno;
	private RegistroParqueo registroDos;
	private RegistroParqueo registroTres;
	//Modelo Dados
	private VehiculoModel vehiculoUnoM;
	private VehiculoModel vehiculoDosM;
	private VehiculoModel vehiculoTresM;
	private RegistroParqueoModel registroUnoModel;
	private RegistroParqueoModel registroDosModel;
	private RegistroParqueoModel registroTresModel;
	
	private List<RegistroParqueoModel> listaRegistrosModel;

	ModelMapper modelMapper = new ModelMapper();

	@Before
	public void setRegistros() {
		
	    vehiculoUnoM = new VehiculoModelBuider().withPlaca("DHU403").withTipo(TipoVehiculoEnum.CARRO).build();
		vehiculoUno = modelMapper.map(vehiculoUnoM, Vehiculo.class);
		vehiculoUno = vehiculoRepository.saveAndFlush(vehiculoUno);
		registroUnoModel = new RegistroParqueoModelBuilder()
				.withFechaIngreso(new GregorianCalendar(2018, 7, 20, 7, 0, 0))
				.withFechaSalida(new GregorianCalendar(2018, 7, 21, 7, 0, 0))
				.withEstado(EstadoEnum.PORPAGAR.toString())
                .withVehiculo(vehiculoUnoM).build();
		
		registroUno = modelMapper.map(registroUnoModel, RegistroParqueo.class);
		registroUno.setVehiculo(vehiculoUno);
		registroParqueoRepository.saveAndFlush(registroUno);
         
		//Registro para un vehiculo que no habia ingresado antes
		vehiculoDosM = new VehiculoModelBuider().withPlaca("XWY87").withTipo(TipoVehiculoEnum.MOTO).build();

		registroDosModel = new RegistroParqueoModelBuilder()
				.withFechaIngreso(new GregorianCalendar(2018, 7, 21, 10, 0, 0)).withEstado(EstadoEnum.ACTIVO.toString())
				.withVehiculo(vehiculoDosM).build();
		
		registroTresModel= new RegistroParqueoModelBuilder(TipoVehiculoEnum.MOTO).build();
		vehiculoTresM=registroTresModel.getVehiculo();
		vehiculoTres=modelMapper.map(vehiculoTresM, Vehiculo.class);
		vehiculoTres=vehiculoRepository.saveAndFlush(vehiculoTres);
		
		registroTres=modelMapper.map(registroTresModel, RegistroParqueo.class);
		registroTres.setVehiculo(vehiculoTres);
		registroTres=registroParqueoRepository.saveAndFlush(registroTres);
	}
	
	
	@After
	public void clearRegistros() {
		vehiculoRepository.deleteAll();
		registroParqueoRepository.deleteAll();
	}
	

	@Test
	public void calcularPago() {

		double valorPago;

		valorPago = registroParqueoService.calcularPago(registroUnoModel);

		assertThat(valorPago == 8000d).isTrue();

	}

	@Test
	public void validateEntryTest() {

		try {
			registroParqueoService.validateEntry(registroUnoModel);
		} catch (ParkApiRestException e) {
			assertThat(e.getMessage(), is(
					"Un Vehiculo con placa " + registroUnoModel.getVehiculo().getPlaca() + " ya registro entrada"));
		}

	}

	@Test
	public void vehiclesCurrentlyParked() {

		Type modelListType = new TypeToken<List<RegistroParqueoModel>>() {
		}.getType();

		listaRegistrosModel = modelMapper.map(registroParqueoService.vehiclesCurrentlyParked(), modelListType);
		

		assertThat(listaRegistrosModel.size() ==1).isTrue();
	}

	@Test
	public void updateTest() {

		Calendar fecha = Calendar.getInstance();
		fecha.add( Calendar.DATE, 1 );
		
		registroTresModel.setFechaSalida(fecha);
		RegistroParqueoModel registro = registroParqueoService.update(registroTresModel);

		assertNotNull(registro);

	}

	@Test
	public void saveTest() {
		
		RegistroParqueoModel m=registroParqueoService.save(registroDosModel);

		assertNotNull(m);

	}

	@Test
	public void saveTestNewVehicle() {
		
		RegistroParqueoModel rp =new RegistroParqueoModelBuilder(TipoVehiculoEnum.CARRO).build();

		assertNotNull(registroParqueoService.save(rp));

	}

	@Test
	public void registroByPlacaTest() {
		
		assertNotNull(registroParqueoService.registroByPlaca(vehiculoTresM.getPlaca()));

	}

}
