package com.deeep.jam.screens;

import com.badlogic.gdx.Screen;
import com.deeep.jam.World;

/**
 * Created by scanevaro on 05/12/2014.
 */
public class GameScreen implements Screen {

    private AbstractGame game;
    private World world;

    public GameScreen(AbstractGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void show() {

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