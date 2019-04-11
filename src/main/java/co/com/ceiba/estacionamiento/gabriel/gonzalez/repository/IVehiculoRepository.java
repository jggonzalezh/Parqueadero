package co.com.ceiba.estacionamiento.gabriel.gonzalez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Vehiculo;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long> {

	public Vehiculo findByPlaca(String placa);

}
