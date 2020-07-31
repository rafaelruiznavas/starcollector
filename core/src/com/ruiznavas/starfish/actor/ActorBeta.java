package com.ruiznavas.starfish.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorBeta extends Actor{
	private TextureRegion regionTextura;
	private Rectangle rectangulo;
	
	public ActorBeta() {
		super();
		regionTextura = new TextureRegion();
		rectangulo = new Rectangle();
	}
	
	public void setTextura(Texture t) {
		regionTextura.setRegion(t);
		setSize(t.getWidth(), t.getHeight());
		rectangulo.setSize(t.getWidth(),t.getHeight());
	}

	public Rectangle getRectangulo() {
		rectangulo.setPosition(getX(),getY());
		return rectangulo;
	}

	public boolean overlaps(ActorBeta otro) {
		return this.getRectangulo().overlaps(otro.getRectangulo());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		Color c = getColor(); // Lo usamos para aplicar efectos de tintado
		batch.setColor(c);
		
		if(isVisible())
			batch.draw(regionTextura, 
					getX(), getY(),
					getOriginX(), getOriginY(),
					getWidth(), getHeight(),
					getScaleX(), getScaleY(), getRotation());
	}
}

