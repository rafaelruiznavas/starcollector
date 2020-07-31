package com.ruiznavas.starfish.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorTextura extends Actor{
	private Texture textura;

	public ActorTextura() {
		super();
	}
	
	public Texture getTextura() {
		return textura;
	}


	public void setTextura(Texture textura) {
		this.textura = textura;
	}


	public void draw(Batch batch) {
		batch.draw(getTextura(), getX(), getY());
	}
}
