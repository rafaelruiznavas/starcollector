package com.ruiznavas.genetica;

public class TestGA {
	public static void main(String[] args) {
		AlgoritmoGenetico ga = new AlgoritmoGenetico(100, 0.01, 0.95, 0);
		Poblacion poblacion = ga.iniciarPoblacion(50);
		
		ga.evaluarPoblacion(poblacion);
		int generacion = 1;
		
		while(ga.seHaAlcanzadoLaCondicionDeFin(poblacion) == false) {
			// imprimimos el valro de fitnes de cada individuo de la poblacion
			System.out.println("Mejor solucion: " + poblacion.getMasEnforma(0).toString());
			
			// Aplicamos el cruce
			poblacion = ga.crucePoblacion(poblacion);
			
			// Aplicamos la mutacion
			
			// Evaluamos la poblacion
			ga.evaluarPoblacion(poblacion);
			
			// Incrementamos la generacion actual
			generacion++;
		}
		
		System.out.println("Solucion encontrada en: " + generacion +" generaciones");
		System.out.println("Mejor solucion: " + poblacion.getMasEnforma(0).toString());
	}
}
