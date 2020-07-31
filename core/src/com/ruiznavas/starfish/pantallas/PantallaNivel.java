package com.ruiznavas.starfish.pantallas;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.ruiznavas.starfish.JuegoBase;
import com.ruiznavas.starfish.StarCollectorGame;
import com.ruiznavas.starfish.actor.ActorBase;
import com.ruiznavas.starfish.actor.Astronauta;
import com.ruiznavas.starfish.actor.CogeEstrella;
import com.ruiznavas.starfish.actor.Estrella;
import com.ruiznavas.starfish.actor.Roca;

public class PantallaNivel extends PantallaBase{
	private Astronauta astronauta;
	private boolean win;
	private Label labelStarfish;
	
	@Override
	public void inicializar() {
		ActorBase fondo = new ActorBase(0, 0, escenaPrincipal);
		fondo.cargarTextura("fondo.png");
		fondo.setSize(1200, 900);
		
		labelStarfish = new Label("Gemas por recoger: ", JuegoBase.estiloEtiqueta);
		labelStarfish.setColor(Color.CYAN);
		labelStarfish.setPosition(20, 520);
		escenaUI.addActor(labelStarfish);
		
		ButtonStyle estiloBoton = new ButtonStyle();
		Texture texBoton = new Texture(Gdx.files.internal("atras.png"));
		TextureRegion regionBoton = new TextureRegion(texBoton);
		estiloBoton.up = new TextureRegionDrawable(regionBoton);
		
		Button botonReiniciar = new Button(estiloBoton);
		botonReiniciar.setColor(Color.CYAN);
		botonReiniciar.setPosition(720, 520);
		escenaUI.addActor(botonReiniciar);
		
		botonReiniciar.addListener((Event e) -> {
			if(!(e instanceof InputEvent) ||
					!((InputEvent)e).getType().equals(Type.touchDown))
				return false;
			StarCollectorGame.setPantallaActiva(new PantallaNivel());
			return false;
		});
		
		new Estrella(400, 400, escenaPrincipal);
		new Estrella(500, 100, escenaPrincipal);
		new Estrella(100, 450, escenaPrincipal);
		new Estrella(200, 250, escenaPrincipal);
		
		new Roca(200, 150, escenaPrincipal);
		new Roca(100, 300, escenaPrincipal);
		new Roca(300, 350, escenaPrincipal);
		new Roca(450, 200, escenaPrincipal);
		
		astronauta = new Astronauta(20, 20, escenaPrincipal);
		win = false;
		
		ActorBase.setLimitesMundo(fondo);
	}
	@Override
	public void update(float dt) {
		List<ActorBase> listaEstrellas = ActorBase.getList(escenaPrincipal, "com.ruiznavas.starfish.actor.Estrella");
		for(ActorBase rock : ActorBase.getList(escenaPrincipal, "com.ruiznavas.starfish.actor.Roca"))
			astronauta.prevenirSuperposicion(rock);
		
		for(ActorBase estrellaActor : listaEstrellas) {
			Estrella estrella = (Estrella)estrellaActor;
			
			if(astronauta.superposiciona(estrella) && !estrella.estaRecolectado()) {
				estrella.recolectar();
				CogeEstrella cogeestrella = new CogeEstrella(0, 0, escenaPrincipal);
				cogeestrella.centrarEnActor(estrella);
			}
		}
			
		if(listaEstrellas.size() == 0 && !win) {
			win = true;
			ActorBase msgWin = new ActorBase(0,0,escenaUI);
			msgWin.cargarTextura("winmsg.png");
			msgWin.centrarEnPosicion(400, 300);
			msgWin.setOpacidad(0);
			msgWin.addAction(Actions.delay(1));
			msgWin.addAction(Actions.after(Actions.fadeIn(1)));
		}
		
		labelStarfish.setText("Gemas por recoger: " + listaEstrellas.size());
	}

	
}
