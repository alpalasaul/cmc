package com.cmc.rest.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;
import com.cmc.rest.servicios.ServicioPersonas;

public class TestActualizar {
	
	private static Logger logger = LogManager.getLogger(TestActualizar.class); 
	
	public static void main(String[] args) {
		Persona persona = new Persona("17455454", "saul", "alpala");
		Persona personaActualizada = null;
		try {
			personaActualizada = ServicioPersonas.actualizar(persona);
		} catch (ValidationException e) {
			e.printStackTrace();
			logger.error("Se a producido un error en el método ServicioPersonas.actualizar", e);
		}
		System.out.println(personaActualizada);
		
		
		
		
		
		
	}

}
