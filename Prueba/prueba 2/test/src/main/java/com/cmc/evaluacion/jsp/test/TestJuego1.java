package com.cmc.evaluacion.jsp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.cmc.evaluacion.jsp.entidades.Carta;
import com.cmc.evaluacion.jsp.servicios.Juego;

public class TestJuego1 {

	@Test
	public void validateJugadoresTest() {
		String[] jugadores = { "AFE", "SMO", "PGA", "EFE" };
		Juego juego = new Juego(jugadores);
		int sizeJugadores = juego.getCartasJugador().keySet().size();
		assertEquals(4, sizeJugadores);
	}


	@Test
	public void validarBarajasTest() {
		String[] jugadores = { "AFE", "SMO", "PGA", "EFE" };
		Juego juego = new Juego(jugadores);
		List<Carta> cartas = juego.getCartasJugador().get("AFE");
		assertNotNull(cartas);
	}
}
