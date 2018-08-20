package com.brox.livewallpaper.space;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class LWSpace extends Game {

	public SpriteBatch batch;
	OrthographicCamera camera;
	FillViewport fillViewport;

	int WIDTH = 1024;
	int HEIGHT = 1024;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		fillViewport = new FillViewport(WIDTH, HEIGHT, camera);
		fillViewport.apply(true);
		camera.position.set(camera.viewportWidth*0.5f, camera.viewportHeight*0.5f, 0);
		batch = new SpriteBatch();

		setScreen(new LWSpaceScreen(this));
	}

	@Override
	public void render () {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		fillViewport.update(width, height);
		camera.position.set(camera.viewportWidth*0.5f, camera.viewportHeight*0.5f, 0);
	}

	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
