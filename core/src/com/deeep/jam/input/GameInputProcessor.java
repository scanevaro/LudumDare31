package com.deeep.jam.input;

import com.badlogic.gdx.InputProcessor;
import com.deeep.jam.World;
import com.deeep.jam.screens.GameScreen;

/**
 * Created by scanevaro on 06/12/2014.
 */
public class GameInputProcessor implements InputProcessor {

    private GameScreen screen;
    private int screenX;
    private int screenY;

    public GameInputProcessor(GameScreen screen) {
        this.screen = screen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if (screen.world.state == World.GAMEOVER) return false;

        screen.world.globe.setAngleFacing((float) (Math.atan2(screenX - 256, 512 - screenY - 256) * -1));

        this.screenX = screenX;
        this.screenY = screenY;

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        if (screen.world.state == World.GAMEOVER) return false;

        screen.world.globe.setAngleFacing((float) (Math.atan2(screenX - 256, 512 - screenY - 256) * -1));

        this.screenX = screenX;
        this.screenY = screenY;

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}