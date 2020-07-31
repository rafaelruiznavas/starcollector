package com.ruiznavas.starfish;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class StarfishGame extends Game {
	private SpriteBatch batch;
	private Texture texturaPersonaje;
	private float personajeX;
	private float personajeY;
	private Rectangle rectPersonaje;
	
	private Texture texturaEstrella;
	private float estrellaX;
	private float estrellaY;
	private Rectangle rectEstrella;
	
	private Texture texuraFondo;
	private Texture texturaMensajeGanar;
	
	private boolean hasGanado;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		texturaPersonaje = new Texture(Gdx.files.internal("personaje.png"));
		personajeX = 20;
		personajeY = 20;
		rectPersonaje = new Rectangle(personajeX, personajeY, texturaPersonaje.getWidth(), texturaPersonaje.getHeight());
		
		texturaEstrella = new Texture(Gdx.files.internal("gem01.gif"));
		estrellaX = 380;
		estrellaY = 380;
		rectEstrella = new Rectangle(estrellaX, estrellaY, texturaEstrella.getWidth(), texturaEstrella.getHeight());
		
		texuraFondo = new Texture(Gdx.files.internal("fondo.png"));
		texturaMensajeGanar = new Texture(Gdx.files.internal("winmsg.png"));
		
		hasGanado = false;
	}

	@Override
	public void render () {
		// Comprobamos la entrada del usuario
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			personajeX--;
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			personajeX++;
		if(Gdx.input.isKeyPressed(Keys.UP))
			personajeY++;
		if(Gdx.input.isKeyPressed(Keys.DOWN))
			personajeY--;
		// Actualizamos la localziacion del rectangulo
		rectPersonaje.setPosition(personajeX,personajeY);
		
		// Comprobamls la condicion de ganar
		if(rectPersonaje.overlaps(rectEstrella))
			hasGanado = true;
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Dibujamos los graficos
		batch.begin();
		batch.draw(texuraFondo, 0, 0, 800,600);
		if(!hasGanado)
			batch.draw(texturaEstrella, estrellaX,estrellaY);
		else
			batch.draw(texturaMensajeGanar, 180,200);
		batch.draw(texturaPersonaje, personajeX,personajeY);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texturaPersonaje.dispose();
	}
}


