package co.com.ceiba.estacionamiento.gabriel.gonzalez.services;


import java.util.List;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.RegistroParqueoModel;

public interface IRegistroParqueoService {

	public RegistroParqueoModel save(RegistroParqueoModel registroParqueoModel);

	public RegistroParqueoModel update(RegistroParqueoModel registroParqueoModel);

	public List<RegistroParqueoModel> vehiclesCurrentlyParked();

	public RegistroParqueoModel registroByPlaca(String placa);

	public RegistroParqueoModel registroSalidaByPlaca(String placa, String estado);

	public void validateEntry(RegistroParqueoModel registroParqueoDTO);

	public double calcularPago(RegistroParqueoModel registroParqueoDTO);

}
