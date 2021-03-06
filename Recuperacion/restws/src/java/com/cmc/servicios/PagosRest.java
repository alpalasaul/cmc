package com.cmc.servicios;

import java.util.ArrayList;

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
import com.cmc.eval.entidades.Pago;
import com.cmc.eval.servicios.AdminClientes;
import com.cmc.eval.servicios.AdminPagos;

@Path("/pagos")
public class PagosRest {
	
private static Logger logger = LogManager.getLogger(PagosRest.class);
	
	@Path("/registrar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response registrar(Pago pago) {
		if (pago != null) {
			AdminPagos.registrarPago(pago);
		}
		
		return Response.status(200).build();
	}
	
	@Path("/buscar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response consultar(Busqueda busqueda) {
		Cliente cli = null;
		ArrayList<Pago> pagos = new ArrayList<Pago>();
		if (busqueda != null) {
			try {
				cli = AdminClientes.buscarPorCedula(busqueda.getCedula());
				
			} catch (ArchivoException e) {
				e.printStackTrace();
				logger.error("Se ha producido un error en el m?todo AdminClientes.buscarPorCedula");
			}
			try {
				pagos.addAll(AdminPagos.buscarPago(busqueda.getCedula()));
			} catch (ArchivoException e) {
				e.printStackTrace();
				logger.error("Se ha producido un error en el m?todo AdminClientes.recuperarDeudas");
			}
		}
		cli.setPagos(pagos);
		
		
		return Response.status(200).entity(cli).build();
	}

}
