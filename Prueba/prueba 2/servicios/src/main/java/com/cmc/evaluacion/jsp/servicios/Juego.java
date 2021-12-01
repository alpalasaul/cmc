package com.cmc.evaluacion.jsp.servicios;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.cmc.evaluacion.jsp.entidades.Carta;
import com.cmc.evaluacion.jsp.entidades.Naipe;

public class Juego {

	private Naipe naipe;
	private HashMap<String, ArrayList<Carta>> cartasJugador = new HashMap<String, ArrayList<Carta>>(); // ...res
	private ArrayList<Carta> naipeBarajado = new ArrayList<Carta>();

	public Juego() {
		naipe = new Naipe();
		naipeBarajado.addAll(naipe.barajar()); // barajado unit
	}

	public Juego(String[] idsJugadores) {
		for (int i = 0; i < idsJugadores.length; i++) {
			cartasJugador.put(idsJugadores[i], new ArrayList<Carta>());
		}
		naipe = new Naipe();
		naipeBarajado.addAll(naipe.barajar());
	}

	public void entregarCartas(int cartasPorJugador) {
		int numeroJugadores = cartasJugador.size();
		int totalCartasARepartir = numeroJugadores*cartasPorJugador;
		for (int i = 0; i < totalCartasARepartir; i = i + numeroJugadores) { // 15
			
			int iterador = i;
			
			for (Entry<String, ArrayList<Carta>> set : cartasJugador.entrySet()) {				 
				
				ArrayList<Carta> recuperado = new ArrayList<Carta>(); // recupero la lista    A K 2
				recuperado.addAll(set.getValue());
				recuperado.add(naipeBarajado.get(iterador));  // A K 2 J
				iterador++;
				cartasJugador.put(set.getKey(), recuperado);
	        }
		}
	}
	
	public int devolverTotal(String idJugador) {
		int sumaTotal = 0;
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		cartas = cartasJugador.get(idJugador);
		if (!cartas.isEmpty()) {
			for (int i = 0; i < cartas.size(); i++) {
				sumaTotal = sumaTotal + cartas.get(i).getNumero().getValor();
			}
		}
		return sumaTotal;
	}
	
	public String determinarGanador() {
		int sumaGanador = 0;
		String idGanador = "";
		
		for (Entry<String, ArrayList<Carta>> set : cartasJugador.entrySet()) {
			if (this.devolverTotal(set.getKey()) > sumaGanador) {
				sumaGanador = this.devolverTotal(set.getKey());
				idGanador = set.getKey();
			}
		}
		return idGanador;
		
	}

	public Naipe getNaipe() {
		return naipe;
	}

	public void setNaipe(Naipe naipe) {
		this.naipe = naipe;
	}

	public HashMap<String, ArrayList<Carta>> getCartasJugador() {
		return cartasJugador;
	}

}
