package com.cmc.rest.servicios;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.rest.commons.ArchivoException;
import com.cmc.rest.commons.ValidationException;
import com.cmc.rest.entidades.Persona;

public class ServicioPersonas {
	
	private static Logger logger = LogManager.getLogger(ServicioPersonas.class); 
	
	public static Persona actualizar(Persona persona) throws ValidationException {
		Persona personaActualizada = null;
		if (persona != null) {
			personaActualizada = new Persona(
					persona.getCedula(), 
					persona.getNombre().toUpperCase(), 
					persona.getApellido().toUpperCase());
			
			if (persona.getNombre().length() <= 2) {
				logger.error("El nombre es muy corto");
				throw new ValidationException("El nombre es muy corto");
			}
			if (persona.getApellido().length() <= 2) {
				logger.error("El apellido es muy corto");
				throw new ValidationException("El apellido es muy corto");
			}
			
		}
		
		
		return personaActualizada;
	}
	
	public static Persona buscarPorCedula(String cedula) {
		ManejadorArchivos archivos = new ManejadorArchivos("miarchivo.txt");
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			personas.addAll(archivos.leer());
		} catch (ArchivoException e) {
			e.printStackTrace();
			logger.error("Error al leer el archivo", e);
		}
		for (Persona persona : personas) {
			if (persona != null) {
				if (persona.getCedula().equals(cedula)) {
					return persona;
				}
			}
		}
		return null;
	}
	
	public static void guardarPersona(Persona persona) {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:\\Program Files\\apache-tomcat-9.0.52\\bin\\miarchivo.txt", true);
            pw = new PrintWriter(fichero);

            if (persona != null) {
            	String guardar = persona.getNombre() + "-" 
            + persona.getApellido() + "-"   
            + persona.getCedula() + "-"
            + persona.getEdad() + "-"
            + persona.getFechaNacimiento();
            	
            	pw.println("" + "\r\n" + guardar);
			}
            
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error al escribir el archivo", e);
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
              logger.error("Error al cerrar el fichero", e2);
           }
        }        
	}

}
