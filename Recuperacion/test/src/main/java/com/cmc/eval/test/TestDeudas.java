package com.cmc.eval.test;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.eval.commons.ArchivoException;
import com.cmc.eval.entidades.Busqueda;
import com.cmc.eval.entidades.Cliente;
import com.cmc.eval.entidades.Deuda;
import com.cmc.eval.servicios.AdminClientes;
import com.cmc.eval.servicios.AdminDeudas;

public class TestDeudas {
	
	private static Logger logger = LogManager.getLogger(TestDeudas.class);
	
	

	public static void main(String[] args) {
		
		Busqueda busqueda = new Busqueda();
		busqueda.setCedula("0414616123");
		
		Cliente cli = null;
		ArrayList<Deuda> deudas = new ArrayList<Deuda>();
		if (busqueda != null) {
			try {
				cli = AdminClientes.buscarPorCedula(busqueda.getCedula());
				
			} catch (ArchivoException e) {
				e.printStackTrace();
				logger.error("Se ha producido un error en el método AdminClientes.buscarPorCedula");
			}
			try {
				deudas.addAll(AdminDeudas.recuperarDeudas(busqueda.getCedula()));
			} catch (ArchivoException e) {
				e.printStackTrace();
				logger.error("Se ha producido un error en el método AdminClientes.recuperarDeudas");
			}
		}
		cli.setDeudas(deudas);
		
		System.out.println(cli);

	}

}
