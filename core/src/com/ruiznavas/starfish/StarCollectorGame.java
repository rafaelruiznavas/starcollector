package com.ruiznavas.starfish;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.ruiznavas.starfish.pantallas.PantallaMenu;

public class StarCollectorGame extends JuegoBase{

	@Override
	public void create() {
		super.create();
		
		FreeTypeFontParameter parametrosFuente = new FreeTypeFontParameter();
		parametrosFuente.size = 48;
		parametrosFuente.color= Color.WHITE;
		parametrosFuente.borderWidth = 2;
		parametrosFuente.borderColor = Color.BLACK;
		parametrosFuente.borderStraight = true;
		parametrosFuente.minFilter = TextureFilter.Linear;
		parametrosFuente.magFilter = TextureFilter.Linear;
		
		FreeTypeFontGenerator generadorFuente = new FreeTypeFontGenerator(Gdx.files.internal("fuente.ttf"));
		BitmapFont fuenteCustom = generadorFuente.generateFont(parametrosFuente);
		estiloEtiqueta.font = fuenteCustom;
		
		estiloBoton = new TextButtonStyle();
		Texture texBoton = new Texture(Gdx.files.internal("buttonBlue.png"));
		NinePatch patchBoton = new NinePatch(texBoton, 24,24,24,24);
		estiloBoton.up = new NinePatchDrawable(patchBoton);
		estiloBoton.font = fuenteCustom;
		estiloBoton.fontColor = Color.GRAY;
		
		setPantallaActiva(new PantallaMenu());
	}
}


