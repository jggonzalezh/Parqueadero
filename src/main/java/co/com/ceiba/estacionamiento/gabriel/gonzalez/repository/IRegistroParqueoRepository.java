package co.com.ceiba.estacionamiento.gabriel.gonzalez.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.RegistroParqueo;



@Repository
public interface IRegistroParqueoRepository extends JpaRepository<RegistroParqueo, Long> {

	@Query(value = "SELECT  R.ID  FROM  REGISTROS R  INNER JOIN VEHICULOS V  ON R.VEHICULO_ID=V.ID  WHERE PLACA=?1 AND R.FECHA_SALIDA IS NULL", nativeQuery = true)
	public Optional<BigInteger> vehiculoHasRegisterEntry(String placa);
	
	@Query("SELECT R FROM  RegistroParqueo R  WHERE  R.fechaSalida IS NULL")
	public List<RegistroParqueo > vehiclesCurrentlyParked();
	
	@Query("SELECT R FROM  RegistroParqueo R  WHERE R.vehiculo.placa= ?1 AND  R.fechaSalida IS NULL")
	public RegistroParqueo registroByPlaca(String placa);
	

	@Query(value = "SELECT  COUNT(V.TIPO) FROM  REGISTROS R   INNER JOIN  VEHICULOS V  ON R.VEHICULO_ID=V.ID WHERE R.FECHA_SALIDA IS NULL AND V.TIPO=?1 ", nativeQuery = true)
	public Optional<BigInteger> countVehiculosByType(String tipo);
	
	@Query("SELECT R FROM  RegistroParqueo R  WHERE R.vehiculo.placa= ?1 AND  R.estado=?2")
	public RegistroParqueo registroByPlacaAndfechaSalida(String placa,String estado); 
	
}