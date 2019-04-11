package co.com.ceiba.estacionamiento.gabriel.gonzalez.util;


import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Tarifa;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.TarifaModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.ITarifaRepository;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.ICalculaPagoUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculaPagoUtilityTest {
	
	@Autowired
	private ICalculaPagoUtility calcula;
	
	@Autowired
	private ITarifaRepository  tarifaRepository;
	

	private Calendar fechaInicial;
	private Calendar fechaFinal;
	private double tarifaDiaMoto;
	private double tarifaHoraMoto;
	private double tarifaDiaCarro;
	private double tarifaHoraCarro;
	private String cilindraje;

	@Before
	public void setUp() {
		
		TarifaModel tarifa = new TarifaModel();
		tarifa.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		tarifa.setUnidadCobro(UnidadCobroEnum.DIA.toString());
		tarifa.setValor(8000d);
		tarifa.getTipoVehiculo();
		tarifa.getUnidadCobro();
		tarifa.getValor();
		
		
		Tarifa tari = new Tarifa();
		tari.setTipoVehiculo(TipoVehiculoEnum.CARRO);
		tari.setUnidadCobro(UnidadCobroEnum.DIA);
		tari.setValor(8000d);
		tari.getTipoVehiculo();
		tari.getUnidadCobro();
		tari.getValor();
		
		

		fechaInicial = new GregorianCalendar(2018, 7, 20, 7, 0, 0);
		fechaFinal = new GregorianCalendar(2018, 7, 21, 10, 0, 0);
		cilindraje="600";

	}
	
	@Test
	public void calcularpagoCarroTest() {
		
		
		tarifaDiaCarro=tarifaRepository.obtenerTarifa(TipoVehiculoEnum.CARRO.toString(),UnidadCobroEnum.DIA.toString());
		tarifaHoraCarro=tarifaRepository.obtenerTarifa(TipoVehiculoEnum.CARRO.toString(),UnidadCobroEnum.HORA.toString());
		
		double valorPago=calcula.calcularpago(fechaInicial, fechaFinal,cilindraje, tarifaDiaCarro, tarifaHoraCarro,TipoVehiculoEnum.CARRO.toString());
		double valorExperado=11000;
		assertTrue(valorPago==valorExperado);
		
	};
	
    @Test
	public void calcularpagoMotoTest() {
    	
    	tarifaDiaMoto=tarifaRepository.obtenerTarifa(TipoVehiculoEnum.MOTO.toString(),UnidadCobroEnum.DIA.toString()); 
		tarifaHoraMoto=tarifaRepository.obtenerTarifa(TipoVehiculoEnum.MOTO.toString(),UnidadCobroEnum.HORA.toString()); 
    		
    	double valorPago=calcula.calcularpago(fechaInicial, fechaFinal, cilindraje, tarifaDiaMoto, tarifaHoraMoto,TipoVehiculoEnum.MOTO.toString());
    	double valorExperado=7500;
    	assertTrue(valorPago==valorExperado);
	};
	


}
