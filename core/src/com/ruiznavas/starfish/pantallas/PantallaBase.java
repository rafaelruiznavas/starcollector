package com.ruiznavas.starfish.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class PantallaBase implements Screen, InputProcessor{
	protected Stage escenaPrincipal;
	protected Stage escenaUI;
	
	public PantallaBase() {
		escenaPrincipal = new Stage();
		escenaUI = new Stage();
		
		inicializar();
	}
	
	public abstract void inicializar();
	public abstract void update(float dt);
	
	@Override
	public void render(float delta) {
		escenaUI.act(delta);
		escenaPrincipal.act(delta);
		
		update(delta);
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		escenaPrincipal.draw();
		escenaUI.draw();
	}
	@Override
	public void resize(int width, int height) {	}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void show() {
		InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
		im.addProcessor(this);
		im.addProcessor(escenaUI);
		im.addProcessor(escenaPrincipal);
	}
	@Override
	public void hide() {
		InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
		im.removeProcessor(this);
		im.removeProcessor(escenaUI);
		im.removeProcessor(escenaPrincipal);
	}
	@Override
	public void dispose() {}
	
	// Metodos requeridos de InputProcessor
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
}

