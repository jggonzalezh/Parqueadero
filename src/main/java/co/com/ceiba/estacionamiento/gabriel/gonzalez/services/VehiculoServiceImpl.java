package co.com.ceiba.estacionamiento.gabriel.gonzalez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Vehiculo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.IVehiculoRepository;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;


	@Override
	@Transactional
	public Vehiculo save(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}


}
