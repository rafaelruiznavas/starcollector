package com.ruiznavas.genetica;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Poblacion {
	private Individuo poblacion[];
	private double fitnessPoblacion = -1;
	
	public Poblacion(int tamPoblacion) {
		super();
		this.poblacion = new Individuo[tamPoblacion];
	}
	
	public Poblacion(int tamPoblacion, int longitudCromosoma) {
		this.poblacion = new Individuo[tamPoblacion];
		
		for(int contIndividuo = 0; contIndividuo < tamPoblacion;contIndividuo++) {
			Individuo individuo = new Individuo(longitudCromosoma);
			this.poblacion[contIndividuo] = individuo;
		}
	}

	public Individuo[] getIndividuos() {
		return poblacion;
	}
	
	public Individuo getMasEnforma(int offset) {
		Arrays.sort(this.poblacion, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				if(o1.getFitness() > o2.getFitness()) {
					return -1;
				}
				else if(o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});
		return this.poblacion[offset];
	}

	public double getFitnessPoblacion() {
		return fitnessPoblacion;
	}

	public void setFitnessPoblacion(double fitnessPoblacion) {
		this.fitnessPoblacion = fitnessPoblacion;
	}
	
	public int tam() {
		return this.poblacion.length;
	}
	
	public Individuo setIndividuo(int offset, Individuo individuo) {
		return poblacion[offset] = individuo;
	}
	
	public Individuo getIndividuo(int offset) {
		return poblacion[offset];
	}

	public void barajar() {
		Random rnd = new Random();
		for(int i = poblacion.length-1;i>0;i--) {
			int indice = rnd.nextInt(i+1);
			Individuo a = poblacion[indice];
			poblacion[indice] = poblacion[i];
			poblacion[i] = a;
		}
	}
}





