package com.brox.livewallpaper.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class LWSpaceScreen implements Screen {

    final LWSpace main;
    int orientation;

    Vector2 currentAcc;
    Vector2 lastAcc;
    Vector2 toAcc;
    float accTreshold = 0.25f;

    float speed = 10;
    float move = 10;

    String path = "";
    Array<LWLayer> pool;

    ParticleEffect stars;

    public LWSpaceScreen(final LWSpace main) {
        this.main = main;
        orientation = Gdx.input.getRotation();

        setupVectors();

        if (Gdx.graphics.getWidth() <= 1024 || Gdx.graphics.getHeight() <= 1024) path = "1024/";

        pool = new Array<LWLayer>();
        new LWLayer(new Texture(Gdx.files.internal(path+"layer01.jpg")),1,this);
        new LWLayer(new Texture(Gdx.files.internal(path+"layer02.png")),0.7f,this);
        new LWLayer(new Texture(Gdx.files.internal(path+"layer05.png")),0.5f,this);
        new LWLayer(new Texture(Gdx.files.internal(path+"layer06.png")),0.3f,this);
        new LWLayer(new Texture(Gdx.files.internal(path+"layer03.png")),0.2f,this);
        new LWLayer(new Texture(Gdx.files.internal(path+"layer04.png")),0.1f,this);
        new LWLayer(new Texture(Gdx.files.internal(path+"layer07.png")),0.05f,this);

        stars = new ParticleEffect();
        stars.load(Gdx.files.internal("particles/stars"),Gdx.files.internal("particles"));
        stars.getEmitters().first().setPosition(main.WIDTH*0.5f,main.HEIGHT*0.5f);
        stars.getEmitters().first().start();
    }

    @Override
    public void render(float delta) {

        currentAcc.x = Gdx.input.getAccelerometerX();
        currentAcc.y = Gdx.input.getAccelerometerY();
        if ((Math.abs(currentAcc.dst(lastAcc)) > accTreshold)) {
            orientation = Gdx.input.getRotation();
            switch (orientation) {
                case 90:
                    toAcc.x = -currentAcc.y;
                    toAcc.y = currentAcc.x;
                    break;
                case 270:
                    toAcc.x = currentAcc.y;
                    toAcc.y = -currentAcc.x;
                    break;
                default:
                    toAcc.x = currentAcc.x;
                    toAcc.y = currentAcc.y;
            }
        }

        lastAcc.lerp(toAcc, delta*speed);

        main.batch.setProjectionMatrix(main.camera.combined);
        main.batch.begin();
        for (LWLayer layer : pool) {
            main.batch.draw(layer,
                    lastAcc.x*layer.offset*move,
                    lastAcc.y*layer.offset*move,
                    main.WIDTH,
                    main.HEIGHT
            );
            if (pool.first() == layer) stars.getEmitters().first().draw(main.batch, delta);
        }
        main.batch.end();
    }

    @Override
    public void show() {
        setupVectors();
    }

    @Override
    public void resize(int width, int height) {
        setupVectors();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        setupVectors();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        for (LWLayer layer : pool) {
            if (layer != null)
                layer.getTexture().dispose();
        }
        stars.dispose();
    }

    private void setupVectors() {
        currentAcc = new Vector2(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY());
        lastAcc = new Vector2(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY());
        toAcc = new Vector2(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY());
    }
}
