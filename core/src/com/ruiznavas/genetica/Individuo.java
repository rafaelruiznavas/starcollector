package com.ruiznavas.genetica;

import java.util.Arrays;

public class Individuo {
	private int[] cromosoma;
	private double fitness = -1;

	public Individuo(int[] cromosoma) {
		super();
		this.cromosoma = cromosoma;
	}

	public Individuo(int longitudCromosoma) {
		this.cromosoma = new int[longitudCromosoma];
		for (int gen = 0; gen < longitudCromosoma; gen++) {
			if (0.5 < Math.random()) {
				this.setGen(gen, 1);
			} else {
				this.setGen(gen, 0);
			}
		}
	}

	public int[] getCromosoma() {
		return cromosoma;
	}

	public int getLongitudCromosoma() {
		return this.cromosoma.length;
	}

	public void setGen(int offset, int gen) {
		this.cromosoma[offset] = gen;
	}

	public int getGen(int offset) {
		return this.cromosoma[offset];
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	@Override
	public String toString() {
		String salida = "";
		for (int gen : this.cromosoma) {
			salida += gen;
		}
		return salida;
	}

}
