package com.deeep.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.Logger;
import com.deeep.jam.World;
import com.deeep.jam.input.GameInputListener;

/**
 * Created by scanevaro on 05/12/2014.
 */
public class GameScreen implements Screen {

    private Logger logger;
    private SpriteBatch batch;
    private World world;

    @Override
    public void render(float delta) {
        /** updates */
        world.update(delta);

        /** draws */
        world.draw(batch);
    }

    @Override
    public void show() {
        logger = Logger.getInstance();
        batch = ((Core) Gdx.app.getApplicationListener()).batch;
        world = new World();

        Gdx.input.setInputProcessor(new GameInputListener(this));
    }

    public void mouseMoved(int x, int y) {
        logger.system(Gdx.app.getApplicationListener().getClass(), "x = " + x + ", y = " + y);
        //TODO globe movement
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}