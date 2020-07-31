package com.ruiznavas.starfish.actor;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Roca extends ActorBase{

	public Roca(float x, float y, Stage s) {
		super(x, y, s);
		cargarTextura("roca.png");
		setPoligonoLimites(8);
	}

}
