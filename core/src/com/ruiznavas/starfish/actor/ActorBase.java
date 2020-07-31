package com.ruiznavas.starfish.actor;

import java.util.ArrayList;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ActorBase extends Group{
	private Animation<TextureRegion> animacion;
	private float tiempoLapso;
	private boolean animacionPausada;
	
	private Vector2 vecVelocidad;
	private Vector2 vecAceleracion;
	private float aceleracion;
	private float velMaxima;
	private float deceleracion;
	
	private Polygon poligonoLimites;
	
	public ActorBase(float x, float y, Stage s) {
		super();
		
		setPosition(x, y);
		s.addActor(this);
		
		animacion = null;
		tiempoLapso = 0;
		animacionPausada = false;
		
		vecVelocidad = new Vector2(0,0);
		vecAceleracion = new Vector2(0,0);
		aceleracion = 0;
		velMaxima = 1000;
		deceleracion = 0;
	}
	
	public void setAnimacion(Animation<TextureRegion> anim) {
		animacion = anim;
		TextureRegion tr = animacion.getKeyFrame(0);
		float ancho = tr.getRegionWidth();
		float alto = tr.getRegionHeight();
		setSize(ancho, alto);
		setOrigin(ancho/2,alto/2);
		
		if(poligonoLimites == null)
			setRectanguloLimites();
	}
	
	public void setAnimacionPausada(boolean pausar) {
		this.animacionPausada = pausar;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(!animacionPausada)
			tiempoLapso += delta;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		// Aplicamos efecto tintado
		Color c = getColor();
		batch.setColor(c.r,c.g,c.b,c.a);
		
		if(animacion != null && isVisible()) {
			batch.draw(animacion.getKeyFrame(tiempoLapso), 
					getX(),getY(),
					getOriginX(),getOriginY(),
					getWidth(),getHeight(),
					getScaleX(),getScaleY(), getRotation());
		}
	}
	
	public Animation<TextureRegion> cargarAnimacionDesdeFicheros(String[] ficheros, float duracionFrame, boolean bucle){
		int contFicheros = ficheros.length;
		Array<TextureRegion> arrayTextura = new Array<TextureRegion>();
		
		for(int n=0;n<contFicheros;n++) {
			String fichero = ficheros[n];
			Texture textura = new Texture(Gdx.files.internal(fichero));
			textura.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			arrayTextura.add(new TextureRegion(textura));
		}
		Animation<TextureRegion> anim = new Animation<TextureRegion>(duracionFrame, arrayTextura);
		
		if(bucle) {
			anim.setPlayMode(Animation.PlayMode.LOOP);
		}else {
			anim.setPlayMode(Animation.PlayMode.NORMAL);
		}
		
		if(animacion == null)
			setAnimacion(anim);
		
		return anim;
	}
	
	public Animation<TextureRegion> cargarAnimacionDesdeHoja(String fichero, int filas, int cols, float duracionFrame, boolean bucle){
		Texture texture = new Texture(Gdx.files.internal(fichero), true);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		int anchoFrame = texture.getWidth() / cols;
		int altoFrame = texture.getHeight() / filas;
		
		TextureRegion[][] temp = TextureRegion.split(texture, anchoFrame, altoFrame);
		
		Array<TextureRegion> arrayTextura = new Array<TextureRegion>();
		
		for(int r=0;r<filas;r++) {
			for(int c=0;c<cols;c++) {
				arrayTextura.add(temp[r][c]);
			}
		}
		
		Animation<TextureRegion> anim = new Animation<TextureRegion>(duracionFrame, arrayTextura);
		
		if(bucle) {
			anim.setPlayMode(Animation.PlayMode.LOOP);
		}else {
			anim.setPlayMode(Animation.PlayMode.NORMAL);
		}
		
		if(animacion == null)
			setAnimacion(anim);
		
		return anim;
	}
	
	public Animation<TextureRegion> cargarTextura(String fichero){
		String[] ficheros = new String[1];
		ficheros[0] = fichero;
		return cargarAnimacionDesdeFicheros(ficheros, 1, true);
	}
	
	public boolean estaAnimacionFinalizada() {
		return animacion.isAnimationFinished(tiempoLapso);
	}
	
	public void setVelocidad(float velocidad) {
		// Si la longitud del vector es cero, asumimos que el angulo de movimiento es 0 grados
		if(vecVelocidad.len() == 0) {
			vecVelocidad.set(velocidad, 0);
		}else {
			vecVelocidad.setLength(velocidad);
		}
	}
	
	public float getVelocidad() {
		return vecVelocidad.len();
	}
	
	public void setAnguloMovimiento(float angulo) {
		vecVelocidad.setAngle(angulo);
	}
	
	public float getAnguloMovimiento() {
		return vecVelocidad.angle();
	}
	
	public boolean estaMoviendose() {
		return (getVelocidad() > 0);
	}
	
	public void setAceleracion(float acc) {
		this.aceleracion = acc;
	}
	
	public void aceleracionEnAngulo(float angulo) {
		vecAceleracion.add(new Vector2(aceleracion, 0).setAngle(angulo));
	}
	
	public void acelerarHaciaDelante() {
		aceleracionEnAngulo(getRotation());
	}

	public void setVelMaxima(float velMaxima) {
		this.velMaxima = velMaxima;
	}

	public void setDeceleracion(float deceleracion) {
		this.deceleracion = deceleracion;
	}
	
	public void aplicarFisicas(float dt) {
		// Aplicacmos aceleracion
		vecVelocidad.add(vecAceleracion.x * dt, vecAceleracion.y * dt);
		
		float velocidad = getVelocidad();
		
		// Decrementamos la velocidad cuando no aceleramos
		if(vecAceleracion.len() == 0) {
			velocidad -= deceleracion * dt;
		}
		
		// Mantenemos la velocidad dentro de unos limites
		velocidad = MathUtils.clamp(velocidad, 0, velMaxima);

		// Actualizamos la velocidad
		setVelocidad(velocidad);
		
		// aplicamos la velocidad
		moveBy(vecVelocidad.x * dt, vecVelocidad.y * dt);
		
		// Reseteamos la aceleracion
		vecAceleracion.set(0,0);
	}
	
	public void setRectanguloLimites() {
		float w = getWidth();
		float h = getHeight();
		float [] vertices = {0,0,w,0,w,h,0,h};
		poligonoLimites = new Polygon(vertices);
	}
	
	public void setPoligonoLimites(int numLados) {
		float w = getWidth();
		float h = getHeight();
		float [] vertices = new float[2*numLados];
		
		for(int i=0;i<numLados;i++) {
			float angulo = i * 6.28f / numLados;
			// Coordenada X
			vertices[2*i] = w/2 * MathUtils.cos(angulo) + w/2;
			// Coordenada Y
			vertices[2*i+1] = h/2 * MathUtils.sin(angulo) + h/2;
		}
		poligonoLimites = new Polygon(vertices);
	}

	public Polygon getPoligonoLimites() {
		poligonoLimites.setPosition(getX(), getY());
		poligonoLimites.setOrigin(getOriginX(), getOriginY());
		poligonoLimites.setRotation(getRotation());
		poligonoLimites.setScale(getScaleX(), getScaleY());
		return poligonoLimites;
	}
	
	public boolean superposiciona(ActorBase otro) {
		Polygon pol1 = this.getPoligonoLimites();
		Polygon pol2 = otro.getPoligonoLimites();
		
		if(!pol1.getBoundingRectangle().overlaps(pol2.getBoundingRectangle()))
			return false;
		
		return Intersector.overlapConvexPolygons(pol1, pol2);
	}
	
	public void centrarEnPosicion(float x, float y) {
		setPosition(x - getWidth()/2, y - getHeight()/2);
	}
	
	public void centrarEnActor(ActorBase otro) {
		centrarEnPosicion(otro.getX() + otro.getWidth()/2, otro.getY() + otro.getHeight()/2);
	}
	
	public void setOpacidad(float opacidad) {
		this.getColor().a = opacidad;
	}
	
	public Optional<Vector2> prevenirSuperposicion(ActorBase otro) {
		Polygon pol1 = this.getPoligonoLimites();
		Polygon pol2 = otro.getPoligonoLimites();
		
		// Test inicial
		if(!pol1.getBoundingRectangle().overlaps(pol2.getBoundingRectangle()))
			return Optional.empty();
		
		MinimumTranslationVector mtv = new MinimumTranslationVector();
		boolean poligonoSuperposicion = Intersector.overlapConvexPolygons(pol1, pol2, mtv);
		
		if(!poligonoSuperposicion)
			return Optional.empty();
		
		this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
		return Optional.of(mtv.normal);
	}
	
	public void limitadoAlMundo() {
		// Lado izquierdo
		if(getX() < 0)
			setX(0);
		// Lado derecho
		if(getX() + getWidth() > limitesMundo.width)
			setX(limitesMundo.width - getWidth());
		// Lado inferior
		if(getY() < 0)
			setY(0);
		// Lado superior
		if(getY() + getHeight() > limitesMundo.height)
			setY(limitesMundo.height - getHeight());
	}
	
	public void darLaVueltaAlMundo() {
		if(getX() + getWidth() < 0)
			setX(limitesMundo.width);
		if(getX() > limitesMundo.width)
			setX(-getWidth());
		if(getY() + getHeight() < 0)
			setY(limitesMundo.height);
		if(getY() > limitesMundo.height)
			setY(-getHeight());
	}
	
	public void alinearCamara() {
		Camera cam = this.getStage().getCamera();
		Viewport v = this.getStage().getViewport();

		// Centramos la camara en el actor
		cam.position.set(this.getX() + this.getOriginX(), this.getY() + this.getOriginY(), 0);
		
		// Limitamos la camara a la capa
		cam.position.x = MathUtils.clamp(cam.position.x,  cam.viewportWidth/2, limitesMundo.width - cam.viewportWidth/2);
		cam.position.y = MathUtils.clamp(cam.position.y,  cam.viewportHeight/2, limitesMundo.height - cam.viewportHeight/2);
		cam.update();
	}
	
	public static ArrayList<ActorBase> getList(Stage escena, String nombreClase){
		nombreClase = nombreClase;
		ArrayList<ActorBase> lista = new ArrayList<ActorBase>();
		
		Class laClase = null;
		try {
			laClase = Class.forName(nombreClase);
		}catch(Exception err) {
			err.printStackTrace();
		}
		
		for(Actor a : escena.getActors()) {
			if(laClase.isInstance(a))
				lista.add((ActorBase)a);
		}
		return lista;
	}
	
	public static int contar(Stage escena, String nombreClase) {
		return getList(escena, nombreClase).size();
	}
	
	private static Rectangle limitesMundo;
	public static void setLimitesMundo(float ancho, float alto) {
		limitesMundo = new Rectangle(0,0,ancho,alto);
	}
	public static void setLimitesMundo(ActorBase ab) {
		setLimitesMundo(ab.getWidth(), ab.getHeight());
	}
}


















