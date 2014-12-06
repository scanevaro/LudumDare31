package com.deeep.jam;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.entities.Globe;
import com.deeep.jam.screens.AbstractGame;

/**
 * Created by scanevaro on 05/12/2014.
 */
public class World {
    public static Globe globe;

    public World() {
        globe = new Globe();
    }

    public void update(float deltaT){
        globe.update(deltaT);
    }

    public void draw(SpriteBatch spriteBatch){
        globe.draw(spriteBatch);
    }

}
