package co.com.ceiba.estacionamiento.gabriel.gonzalez.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ParkApiRestException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public ParkApiRestException(String exception) {
		super(exception);	
	}
	
	

}
