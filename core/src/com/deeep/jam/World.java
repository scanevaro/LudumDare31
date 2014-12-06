package com.deeep.jam;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.entities.Blob;
import com.deeep.jam.entities.BlobManager;
import com.deeep.jam.entities.Globe;
import com.deeep.jam.screens.AbstractGame;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Andreas on 05/12/2014.
 */
public class World {

    public static Globe globe;
    public static BlobManager blobManager

    public World() {
        globe = new Globe();
        blobManager = new BlobManager();
    }

    public void update(float deltaT){
        globe.update(deltaT);
        blobManager.update(deltaT);
    }

    public void draw(SpriteBatch spriteBatch){
        globe.draw(spriteBatch);
        blobManager.draw(spriteBatch);
    }

}
