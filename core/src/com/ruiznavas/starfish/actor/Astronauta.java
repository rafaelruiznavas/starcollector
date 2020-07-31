package com.ruiznavas.starfish.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Astronauta extends ActorBase{

	public Astronauta(float x, float y, Stage s) {
		super(x, y, s);
		
		String[] ficheros = {
				"astronauta/CHAR_astronaut_00001.png",
				"astronauta/CHAR_astronaut_00002.png",
				"astronauta/CHAR_astronaut_00003.png",
				"astronauta/CHAR_astronaut_00004.png",
				"astronauta/CHAR_astronaut_00005.png",
				"astronauta/CHAR_astronaut_00006.png",
				"astronauta/CHAR_astronaut_00007.png",
				"astronauta/CHAR_astronaut_00008.png",
				"astronauta/CHAR_astronaut_00009.png",
				"astronauta/CHAR_astronaut_00010.png",
				"astronauta/CHAR_astronaut_00011.png",
				"astronauta/CHAR_astronaut_00012.png"
		};
		
		cargarAnimacionDesdeFicheros(ficheros, 0.1f, true);
		
		setAceleracion(400);
		setVelMaxima(100);
		setDeceleracion(400);
		
		setPoligonoLimites(8);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			aceleracionEnAngulo(180);
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			aceleracionEnAngulo(0);
		if(Gdx.input.isKeyPressed(Keys.UP))
			aceleracionEnAngulo(90);
		if(Gdx.input.isKeyPressed(Keys.DOWN))
			aceleracionEnAngulo(270);
		
		
		aplicarFisicas(delta);
		
		setAnimacionPausada(!estaMoviendose());
		
		if(getVelocidad() > 0) {
			setRotation(getAnguloMovimiento());
		}
		
		limitadoAlMundo();
		alinearCamara();
	}
}


