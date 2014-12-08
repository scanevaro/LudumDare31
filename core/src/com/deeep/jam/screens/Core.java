package com.deeep.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.deeep.jam.Settings;
import com.deeep.jam.input.Assets;

/**
 * This class is the entry point to the game
 */
public class Core extends AbstractGame {

    public static ShapeRenderer shapeRenderer;
    public static SpriteBatch batch;

    /**
     * Called when the {@link com.badlogic.gdx.Application} is first created.
     */
    @Override
    public void create() {
        Settings.load();
        Assets.getAssets().load();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        setScreen(new GameScreen());
    }

    /**
     * This will get rid of all the assets to prevent a memory leak
     */
    @Override
    public void dispose() {
        super.dispose();
        Assets.getAssets().dispose();
    }

    /**
     * Render & Update
     */
    @Override
    public void render(float deltaTime) {
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}