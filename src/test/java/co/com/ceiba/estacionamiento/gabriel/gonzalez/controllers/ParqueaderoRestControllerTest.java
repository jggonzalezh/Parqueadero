package co.com.ceiba.estacionamiento.gabriel.gonzalez.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.estacionamiento.gabriel.gonzalez.models.RegistroParqueoModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ParqueaderoRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void listarRegistrosActivosTest() {

		try {
			this.mvc.perform(get("/api/registrosParqueo").contentType(MediaType.APPLICATION_JSON));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void registrarParqueo() {

		try {

			this.mvc.perform(post("/api/registrosParqueo").contentType(MediaType.APPLICATION_JSON)
					.content(getRegistroParqueo()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getRegistroParqueo() {
		return "{\"fechaIngreso\": \"2018-08-13T22:19:21.933+0000\","
				+ "\"vehiculo\":{\"placa\": \"LPA01\",\"tipo\":\"MOTO\" } }";
	}

	@Test
	public void obtenerRegistro() {
		try {
			this.mvc.perform(get("/api/registrosParqueo/HZJ505").contentType(MediaType.APPLICATION_JSON));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void obtenerRegistroSalida() {
		try {
			this.mvc.perform(get("/api/registrosParqueo/HZJ505/ACTIVO").contentType(MediaType.APPLICATION_JSON));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void actualizarParqueo() {
		try {
			
			
			this.mvc.perform(put("/api/registrosParqueo/HZJ505").contentType(MediaType.APPLICATION_JSON)
					.content(getRegistroUpdate()).accept(MediaType.APPLICATION_JSON));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private String getRegistroUpdate() {
		return "{\"fechaIngreso\":\"2018-08-13T22:19:21.000-0500\",\"fechaSalida\":\"2018-08-14T22:19:21.000-0500\","
				+ "\"valorpago\":null,\"estado\":\"ACTIVO\",\"vehiculo\":{\"placa\":\"HZJ505\",\"tipo\":\"CARRO\",\"cilindraje\":\"1.3\"}}";
	}
	
	

}
