package com.krypton.lambda;

import java.util.Arrays;
import java.util.List;

public class App {
	public static void main(String[] args) {
		List<Integer> lista = Arrays.asList(1, 2);
		int suma = lista.stream().mapToInt(a -> a).sum();
		System.out.println(suma);
		
		int resta = lista.stream().reduce(2, (a, b) -> a - b);
		System.out.println(resta);
		
		int multiplicacion = lista.stream().reduce(1, (x, y) -> x * y);
		System.out.println(multiplicacion);
		
	}
}
