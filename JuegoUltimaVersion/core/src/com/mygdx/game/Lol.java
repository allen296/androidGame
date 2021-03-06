package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Screens.PlayScreen;

/**
 * @author Antonio Valladares García
 *
 * Clase que lanza las distintas (por ahora no) pantallas del juego
 */
public class Lol extends com.badlogic.gdx.Game {

	public static final int V_WIDTH=400;
	public static final int V_HEIGHT=208;
	public static final float PPM=100;

	public SpriteBatch batch;

	@Override
	public void create () {

		batch=new SpriteBatch();
		setScreen(new PlayScreen(this));


	}
	@Override
	public void render () {

		super.render();

	}
	@Override
	public void dispose () {

	}
}

