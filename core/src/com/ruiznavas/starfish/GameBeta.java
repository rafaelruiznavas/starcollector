package com.ruiznavas.starfish;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class GameBeta extends Game{
	protected Stage escenaPrincipal;
	protected Stage escenaUI;
	
	@Override
	public void create() {
		escenaPrincipal = new Stage();
		escenaUI = new Stage();
		inicializar();
	}

	public abstract void inicializar();

	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		escenaPrincipal.act(dt);
		escenaUI.act(dt);
		
		update(dt);
		
		// Borramos la pantalla
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Dibujamos los graficos
		escenaPrincipal.draw();
		escenaUI.draw();
	}

	public abstract void update(float dt);
}
