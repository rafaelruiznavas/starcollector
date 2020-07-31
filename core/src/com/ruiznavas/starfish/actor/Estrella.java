package com.ruiznavas.starfish.actor;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Estrella extends ActorBase{
	private boolean recolectado;

	public Estrella(float x, float y, Stage s) {
		super(x, y, s);
		
		cargarTextura("gem01.gif");
		
		Action spin = Actions.rotateBy(30,1);
		this.addAction(Actions.forever(spin));
		
		setPoligonoLimites(8);
		recolectado = false;
	}
	
	public boolean estaRecolectado() {
		return recolectado;
	}
	
	public void recolectar() {
		recolectado = true;
		clearActions();
		addAction(Actions.fadeOut(1));
		addAction(Actions.after(Actions.removeActor()));
	}
}
