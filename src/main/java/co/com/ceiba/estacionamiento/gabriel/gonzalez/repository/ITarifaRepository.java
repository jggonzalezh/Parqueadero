package co.com.ceiba.estacionamiento.gabriel.gonzalez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Tarifa;


public interface ITarifaRepository  extends JpaRepository<Tarifa, Long>{
	
	
	@Query(value = "SELECT  T.VALOR  FROM  TARIFAS T   WHERE T.TIPO_VEHICULO=?1 AND T.UNIDAD_COBRO=?2", nativeQuery = true)
	public double obtenerTarifa(String tipo,String unidad);
	
	

}
