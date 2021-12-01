package com.cmc.servicios;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.DateUtil;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.ManejadorArchivos;
import com.cmc.rest.servicios.ServicioPersonas;

@Path("/personas")
public class PersonaRest {
	
	private static Logger logger = LogManager.getLogger(PersonaRest.class); 
	
	@Path("/cambiar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Persona modificar(Persona persona) {
		System.out.println(persona);
		Persona personaCambiada = null;
		try {
			personaCambiada = ServicioPersonas.actualizar(persona);
		} catch (ValidationException e) {
			e.printStackTrace();
			logger.error("Se a producido un error en el método ServicioPersonas.actualizar", e);
		}
		return personaCambiada;
	}
	
	@Path("/cambio")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response cambiar(Persona persona) {
		System.out.println(persona);
		Persona personaCambiada = null;
		try {
			personaCambiada = ServicioPersonas.actualizar(persona);
		} catch (ValidationException e) {
			e.printStackTrace();
			logger.error("Se a producido un error en el método ServicioPersonas.actualizar", e);
			return Response.status(200).entity(e.getMessage()).build();
			
		}
		return Response.status(200).entity(personaCambiada).build();
	}
	
	@Path("/consultar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarPersonas() {
		ManejadorArchivos manejadorArchivos = new ManejadorArchivos("miarchivo.txt"); // CAMBIAR RUTA
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			personas.addAll(manejadorArchivos.leer());
		} catch (ArchivoException e) {
			logger.error("Se a producido un error al leer el archivo", e);
			e.printStackTrace();
			return Response.status(200).entity(e.getMessage()).build();
		}
		return Response.status(200).entity(personas).build();
	}
	
	@Path("/fecha")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String fecha(Persona persona) {
		Date date = new Date();
		String fechaFormateada = "";
		try {
			fechaFormateada = DateUtil.convertir(date);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("Se a producido un error al formatear la fecha", e);
		}
		
		return fechaFormateada;
	}
	
	@Path("/buscarPorCedula")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Persona buscarPorCedula(Persona persona) {
		Persona personaEcontrada = null;
		if (persona != null) {
			personaEcontrada = ServicioPersonas.buscarPorCedula(persona.getCedula());
		}
		return personaEcontrada;
	}
	
	@Path("/guardar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardar(Persona persona) {
		if (persona != null) {
			ServicioPersonas.guardarPersona(persona);
		}
	}
	
	

}
