package com.ruiznavas.starfish.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class CogeEstrella extends ActorBase{

	public CogeEstrella(float x, float y, Stage s) {
		super(x, y, s);
		
		cargarAnimacionDesdeHoja("cogeestrella.png", 1, 3, 0.1f, false);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(estaAnimacionFinalizada())
			remove();
	}
	
}
