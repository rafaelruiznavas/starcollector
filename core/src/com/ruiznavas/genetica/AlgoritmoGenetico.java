package com.ruiznavas.genetica;

public class AlgoritmoGenetico {
	private int tamPoblacion;
	private double ratioMutacion;
	private double ratioCruzamiento;
	private int contadorElitismo;

	public AlgoritmoGenetico(int tamPoblacion, double ratioMutacion, double ratioCruzamiento, int contadorElitismo) {
		super();
		this.tamPoblacion = tamPoblacion;
		this.ratioMutacion = ratioMutacion;
		this.ratioCruzamiento = ratioCruzamiento;
		this.contadorElitismo = contadorElitismo;
	}
	
	public Poblacion iniciarPoblacion(int longitudCromosoma) {
		Poblacion poblacion = new Poblacion(this.tamPoblacion, longitudCromosoma);
		return poblacion;
	}

	public double calcularFitness(Individuo individuo) {
		// Seguimos el numero de genenes correctos
		int genesCorrectos = 0;
		
		// REcorremos todos los genes correctos
		for(int indiceGen = 0; indiceGen< individuo.getLongitudCromosoma();indiceGen++) {
			// Agregamos un punto de fitness por cada 1 encontrado
			if(individuo.getGen(indiceGen) == 1) {
				genesCorrectos += 1;
			}
		}
		
		double fitness = (double) genesCorrectos / individuo.getLongitudCromosoma();
		
		// Almacenamos el valor de fitness
		individuo.setFitness(fitness);;
		
		return fitness;
	}
	
	public void evaluarPoblacion(Poblacion poblacion) {
		double fitnessPoblacion = 0;
		
		for(Individuo individuo : poblacion.getIndividuos()) {
			fitnessPoblacion += calcularFitness(individuo);
		}
		
		poblacion.setFitnessPoblacion(fitnessPoblacion);
	}
	
	public boolean seHaAlcanzadoLaCondicionDeFin(Poblacion poblacion) {
		for(Individuo individuo : poblacion.getIndividuos()) {
			if(individuo.getFitness() == 1) {
				return true;
			}
		}
		return false;
	}
	
	public Individuo seleccionarPadre(Poblacion poblacion) {
		// Obtenemos los individuos
		Individuo individuos[] = poblacion.getIndividuos();
		
		// Giramos la ruleta 
		double fitnessPoblacion = poblacion.getFitnessPoblacion();
		double posicionRuleta = Math.random() * fitnessPoblacion;
		
		// Buscar padre
		double spinRueda = 0;
		for(Individuo individuo : individuos) {
			spinRueda += individuo.getFitness();
			if(spinRueda >= posicionRuleta) {
				return individuo;
			}
		}
		return individuos[poblacion.tam()-1];
	}
	
	public Poblacion crucePoblacion(Poblacion poblacion) {
		// Creamos la nueva poblacion
		Poblacion neoPoblacion = new Poblacion(poblacion.tam());
		
		// Recorremos la poblacion actual por su valor de fitness
		for(int indicePoblacion = 0; indicePoblacion<poblacion.tam();indicePoblacion++) {
			Individuo padre1 = poblacion.getMasEnforma(indicePoblacion);
		
			// Aplicamos el cruce a este individuo?
			if(this.ratioCruzamiento > Math.random() && indicePoblacion > this.contadorElitismo) {
				// Inicillizamos la descendencia
				Individuo descendencia = new Individuo(padre1.getLongitudCromosoma());
				
				// Buscamos al segundo padre
				Individuo padre2 = seleccionarPadre(poblacion);
				
				// Recorremos cada genoma
				for(int indiceGen = 0;indiceGen<padre1.getLongitudCromosoma();indiceGen++) {
					// Usamos la mitad de los genes del padre 1 y la mitad de los del padre2
					if(0.5 > Math.random()) {
						descendencia.setGen(indiceGen, padre1.getGen(indiceGen));
					}else {
						descendencia.setGen(indiceGen, padre2.getGen(indiceGen));
					}
				}
				
				// Agregamos la descendencia a la nueva poblacion
				neoPoblacion.setIndividuo(indicePoblacion, descendencia);
			}else {
				// Agregamos el individuo a la nueva poblacion sin aplicar el cruce
				neoPoblacion.setIndividuo(indicePoblacion, padre1);
			}
		}
		
		return neoPoblacion;
	}
}










