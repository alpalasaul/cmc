package com.cmc.eval.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmc.eval.commons.ArchivoException;
import com.cmc.eval.entidades.Cliente;
import com.cmc.eval.servicios.AdminClientes;

public class TestBusqueda {
	
	private static Logger logger = LogManager.getLogger(TestBusqueda.class);
	
	public static void main(String[] args) {
		String cedulaBuscada = "6666666";
		try {
			Cliente cli = AdminClientes.buscarPorCedula(cedulaBuscada);
			System.out.println(cli);
		} catch (ArchivoException e) {
			e.printStackTrace();
			logger.error("Error al buscar cliente por cedula");
		}
	}

}
