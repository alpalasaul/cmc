package com.cmc.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.eval.commons.ArchivoException;
import com.cmc.eval.entidades.Busqueda;
import com.cmc.eval.entidades.Cliente;
import com.cmc.eval.servicios.AdminClientes;

@Path("/clientes")
public class ClientesRest {
	
	private static Logger logger = LogManager.getLogger(ClientesRest.class);
	
	@Path("/cambio")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response cambiar(Busqueda busqueda) {
		Cliente cli = null;
		if (busqueda != null) {
			try {
				cli = AdminClientes.buscarPorCedula(busqueda.getCedula());
			} catch (ArchivoException e) {
				e.printStackTrace();
				logger.error("Se ha producido un error en el m?todo AdminClientes.buscarPorCedula");
			}
		}
		return Response.status(200).entity(cli).build();
	}
	
	@Path("/consultar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response consultarDatosCliente(Busqueda busqueda) {
		Cliente cli = null;
		if (busqueda != null) {
			try {
				cli = AdminClientes.consultarEstado(busqueda.getCedula());
			} catch (ArchivoException e) {
				e.printStackTrace();
				logger.error("Error en el metodo AdminClientes.consultarEstado");
			}
		}
		return Response.status(200).entity(cli).build();
	}

}
