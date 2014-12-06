package com.deeep.jam;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.entities.BlobManager;
import com.deeep.jam.entities.Globe;

/**
 * Created by Andreas on 05/12/2014.
 */
public class World {

    public static Globe globe;
    public static BlobManager blobManager;

    public World() {
        globe = new Globe();
        blobManager = new BlobManager();
    }

    public void update(float deltaT) {
        globe.update(deltaT);
        blobManager.update(deltaT);
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        globe.draw(batch);
        blobManager.draw(batch);
        batch.end();
    }

    public void mouseMoved(int x, int y) {

    }
}