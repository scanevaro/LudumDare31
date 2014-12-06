package com.deeep.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.World;

/**
 * Created by scanevaro on 05/12/2014.
 */
public class GameScreen implements Screen {

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
        batch = ((Core) Gdx.app.getApplicationListener()).batch;
        world = new World();
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