package com.cmc.evaluacion.jsp.entidades;

public class Random {

	public static int obtenerPosicion() {
		int numero = 0 + (int) (Math.random() * (51 + 1 - 0));
		return numero;
	}

}
