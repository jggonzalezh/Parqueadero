package co.com.ceiba.estacionamiento.gabriel.gonzalez.services;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.RegistroParqueo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.entities.Vehiculo;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.RegistroParqueoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.IRegistroParqueoRepository;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.ITarifaRepository;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.repository.IVehiculoRepository;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.EstadoEnum;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.ICalculaPagoUtility;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.util.IValidationUtility;

@Service
public class RegistroParqueoServiceImpl implements IRegistroParqueoService {

	@Autowired
	private IRegistroParqueoRepository registroParqueoRepository;

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Autowired
	private ITarifaRepository tarifaRepository;

	@Autowired
	private IValidationUtility validationUtility;

	@Autowired
	private ICalculaPagoUtility calculaPagoUtility;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional(readOnly = true)
	public RegistroParqueoModel registroByPlaca(String placa) {

		RegistroParqueo registroParqueo = registroParqueoRepository.registroByPlaca(placa);
		return modelMapper.map(registroParqueo, RegistroParqueoModel.class);
	}

	@Override
	@Transactional(readOnly = true)
	public RegistroParqueoModel registroSalidaByPlaca(String placa, String estado) {

		RegistroParqueo registroParqueo = registroParqueoRepository.registroByPlacaAndfechaSalida(placa, estado);
		return modelMapper.map(registroParqueo, RegistroParqueoModel.class);
	}

	@Override
	@Transactional
	public RegistroParqueoModel save(RegistroParqueoModel registroParqueoModel) {
      
		// Realiza las validaciones de entrada
		validateEntry(registroParqueoModel);
		// Obtiene la entidad en base al DTO
		RegistroParqueo registroParqueoE = modelMapper.map(registroParqueoModel, RegistroParqueo.class);
		// verifica si la informacion del vehiculo esta persistida
		Vehiculo vehiculoObtenido = vehiculoRepository.findByPlaca(registroParqueoE.getVehiculo().getPlaca());

		// Si no esta persistida la persiste
		if (vehiculoObtenido == null) {
			vehiculoObtenido = vehiculoRepository.saveAndFlush(registroParqueoE.getVehiculo());
		}

		//establece el vehiculo
		registroParqueoE.setVehiculo(vehiculoObtenido);

		//Persiste el registro de parqueo
		RegistroParqueo registroParqueoGuardado = registroParqueoRepository.saveAndFlush(registroParqueoE);

		return modelMapper.map(registroParqueoGuardado, RegistroParqueoModel.class);

	}

	@Override
	@Transactional
	public RegistroParqueoModel update(RegistroParqueoModel registroParqueoModel) {

		// obtengo el registro de la bd
		RegistroParqueo registroParqueoE = registroParqueoRepository
				.registroByPlaca(registroParqueoModel.getVehiculo().getPlaca());

		// Obtengo el vehiculo de la bd

		registroParqueoE.setFechaSalida(registroParqueoModel.getFechaSalida());

		registroParqueoE.setEstado(EstadoEnum.PORPAGAR.toString());
		registroParqueoE.setValorpago(calcularPago(registroParqueoModel));

		RegistroParqueo registroParqueoEU = registroParqueoRepository.saveAndFlush(registroParqueoE);

		return modelMapper.map(registroParqueoEU, RegistroParqueoModel.class);
	}

	@Override
	public void validateEntry(RegistroParqueoModel registroParqueoDTO) {

		String placa = registroParqueoDTO.getVehiculo().getPlaca();
		String tipo = registroParqueoDTO.getVehiculo().getTipo().toString();

		BigInteger id = registroParqueoRepository.vehiculoHasRegisterEntry(placa).orElse(null);
		BigInteger conteo = registroParqueoRepository.countVehiculosByType(tipo).orElse(null);

		validationUtility.validateVehiculoType(tipo);
		validationUtility.validateHaveRegister(placa, id);
		validationUtility.validatePlacaDia(placa);
		validationUtility.validateCapacity(tipo, conteo);

	}

	@Override
	public List<RegistroParqueoModel> vehiclesCurrentlyParked() {

		List<RegistroParqueo> listaregistrosE = registroParqueoRepository.vehiclesCurrentlyParked();

		Type modelListType = new TypeToken<List<RegistroParqueoModel>>() {
		}.getType();

		return modelMapper.map(listaregistrosE, modelListType);

	}

	@Override
	public double calcularPago(RegistroParqueoModel registroParqueoDTO) {

		double tarifaDia = tarifaRepository.obtenerTarifa(registroParqueoDTO.getVehiculo().getTipo().toString(), "DIA");
		double tarifaHora = tarifaRepository.obtenerTarifa(registroParqueoDTO.getVehiculo().getTipo().toString(), "HORA");

		return calculaPagoUtility.calcularpago(registroParqueoDTO.getFechaIngreso(),
				registroParqueoDTO.getFechaSalida(), registroParqueoDTO.getVehiculo().getCilindraje(), tarifaDia,
				tarifaHora, registroParqueoDTO.getVehiculo().getTipo().toString());

	}

}
