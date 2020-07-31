package com.ruiznavas.starfish.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.ruiznavas.starfish.JuegoBase;
import com.ruiznavas.starfish.StarCollectorGame;
import com.ruiznavas.starfish.actor.ActorBase;

public class PantallaMenu extends PantallaBase{

	@Override
	public void inicializar() {
		ActorBase fondo = new ActorBase(0, 0, escenaPrincipal);
		fondo.cargarTextura("fondo.png");
		fondo.setSize(800, 600);
		
		/*ActorBase titulo = new ActorBase(0, 0, escenaPrincipal);
		titulo.cargarTextura("titulo.png");
		titulo.centrarEnPosicion(400, 300);
		titulo.moveBy(0, 100);
		
		ActorBase inicio = new ActorBase(0, 0, escenaPrincipal);
		titulo.cargarTextura("start.png");
		titulo.centrarEnPosicion(400, 300);
		titulo.moveBy(0, -100);*/
		
		TextButton btnInicio = new TextButton("Inicio", JuegoBase.estiloBoton);
		btnInicio.setPosition(150, 150);
		escenaUI.addActor(btnInicio);
		
		btnInicio.addListener((Event e) -> {
			if(!(e instanceof InputEvent) ||
				!((InputEvent)e).getType().equals(Type.touchDown))
				return false;
			StarCollectorGame.setPantallaActiva(new PantallaNivel());
			return false;
		});
		
		TextButton btnSalir = new TextButton("Salir", JuegoBase.estiloBoton);
		btnSalir.setPosition(500, 150);
		escenaUI.addActor(btnSalir);
		
		btnSalir.addListener((Event e) -> {
			if(!(e instanceof InputEvent) ||
				!((InputEvent)e).getType().equals(Type.touchDown))
				return false;
			Gdx.app.exit();
			return false;
		});
	}

	@Override
	public void update(float dt) {
		if(Gdx.input.isKeyPressed(Keys.S))
			StarCollectorGame.setPantallaActiva(new PantallaNivel());
	}

	@Override
	public boolean keyDown(int keycode) {
		if(Gdx.input.isKeyPressed(Keys.ENTER))
			StarCollectorGame.setPantallaActiva(new PantallaNivel());
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		return false;
	}
}
