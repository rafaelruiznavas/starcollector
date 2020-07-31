package com.ruiznavas.starfish;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.ruiznavas.starfish.pantallas.PantallaBase;

public abstract class JuegoBase extends Game{
	private static JuegoBase juego;
	public static LabelStyle estiloEtiqueta;
	public static TextButtonStyle estiloBoton;
	
	public JuegoBase() {
		juego = this;
	}
	
	@Override
	public void create() {
		InputMultiplexer im = new InputMultiplexer();
		Gdx.input.setInputProcessor(im);
		
		estiloEtiqueta = new LabelStyle();
		estiloEtiqueta.font = new BitmapFont();
	}
	
	public static void setPantallaActiva(PantallaBase s) {
		juego.setScreen(s);
	}
}
