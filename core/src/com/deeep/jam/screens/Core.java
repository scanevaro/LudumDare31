package com.deeep.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.jam.input.Assets;
import com.deeep.jam.sequencer.FormulaTypes;
import com.deeep.jam.sequencer.Sequence;
import com.deeep.jam.sequencer.Sequencer;

/**
 * This class is the entry point to the game
 */
public class Core extends AbstractGame {
    /**
     * Batch
     */
    SpriteBatch batch;
    Sprite img;
    Sequencer sequencer;

    /**
     * Called when the {@link com.badlogic.gdx.Application} is first created.
     */
    @Override
    public void create() {
        Assets.getAssets().load();
        sequencer = new Sequencer(true);
        sequencer.addSequence(new Sequence(new FormulaTypes.Linear(1f,1)));
        sequencer.addSequence(new Sequence(new FormulaTypes.Linear(1f,0.1f)));
        batch = new SpriteBatch();
        img = new Sprite(Assets.getAssets().getRegion("badlogic"));
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
     * This should in the future render a background
     */
    @Override
    public void render(float deltaTime) {
        sequencer.update(deltaTime);
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        Assets.getAssets().getBitmapFont().setScale(Math.max(0.1f, sequencer.getValue()));
        Assets.getAssets().getBitmapFont().setColor(Color.WHITE);
        Assets.getAssets().getBitmapFont().draw(batch, "Elmar", 10, 50);
        batch.end();

    }
}
