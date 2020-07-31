package com.ruiznavas.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.ruiznavas.starfish.actor.ActorBeta;
import com.ruiznavas.starfish.actor.Astronauta;

public class StarfishGameBeta extends GameBeta {
	private Astronauta personaje;
	private ActorBeta estrella;
	private ActorBeta fondo;
	private ActorBeta mensajeGanar;
	
	private boolean hasGanado;
	
	@Override
	public void inicializar() {
		fondo = new ActorBeta();
		fondo.setTextura(new Texture(Gdx.files.internal("fondo.png")));
		escenaPrincipal.addActor(fondo);
		
//		personaje = new Astronauta();
//		personaje.setTextura(new Texture(Gdx.files.internal("personaje.png")));
		personaje.setPosition(20, 20);
		escenaPrincipal.addActor(personaje);

		estrella = new ActorBeta();
		estrella.setTextura(new Texture(Gdx.files.internal("gem01.gif")));
		estrella.setPosition(380, 380);
		escenaPrincipal.addActor(estrella);
		
		mensajeGanar = new ActorBeta();
		mensajeGanar.setTextura(new Texture(Gdx.files.internal("winmsg.png")));
		mensajeGanar.setPosition(180, 180);
		mensajeGanar.setVisible(false);
		escenaPrincipal.addActor(mensajeGanar);
		
		hasGanado = false;
	}

	@Override
	public void update(float dt) {
//		if(personaje.overlaps(estrella)) {
//			estrella.remove();
//			mensajeGanar.setVisible(true);
//		}
	}	
}



