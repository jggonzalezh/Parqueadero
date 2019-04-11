package co.com.ceiba.estacionamiento.gabriel.gonzalez.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.RegistroParqueoModel;
import co.com.ceiba.estacionamiento.gabriel.gonzalez.services.IRegistroParqueoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ParqueaderoRestController {

	@Autowired
	private IRegistroParqueoService registroParqueoService;


	@GetMapping("/registrosParqueo/{placa}")
	public RegistroParqueoModel obtenerRegistro(@PathVariable String placa) {
		return registroParqueoService.registroByPlaca(placa);
	}
	
	@GetMapping("/registrosParqueo/{placa}/{estado}")
	public RegistroParqueoModel obtenerRegistroSalida(@PathVariable String placa,@PathVariable String estado) {
		return registroParqueoService.registroSalidaByPlaca(placa,estado);
	}

	@GetMapping("/registrosParqueo")
	public List<RegistroParqueoModel> listarRegistrosActivos() {

		return registroParqueoService.vehiclesCurrentlyParked();
	}

	@PostMapping("/registrosParqueo")
	public ResponseEntity<RegistroParqueoModel> registrarParqueo(@RequestBody RegistroParqueoModel registroParqueoDTO) {

		RegistroParqueoModel registroParqueoModel=registroParqueoService.save(registroParqueoDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(registroParqueoModel);

	}
	
	@PutMapping("/registrosParqueo/{placa}")
	public RegistroParqueoModel actualizarParqueo(@RequestBody RegistroParqueoModel registroParqueoModel,
			@PathVariable String placa) {

		return registroParqueoService.update(registroParqueoModel);

	}


}
