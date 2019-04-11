package co.com.ceiba.estacionamiento.gabriel.gonzalez.util;

import java.math.BigInteger;

public interface IValidationUtility {

	public void validateHaveRegister(String placa, BigInteger id);

	public void validateCapacity(String tipo, BigInteger conteo);

	public void validateVehiculoType(String tipo);

	public void validatePlacaDia(String placa);

}
