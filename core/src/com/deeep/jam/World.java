package com.deeep.jam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.deeep.jam.entities.Blob;
import com.deeep.jam.entities.BlobManager;
import com.deeep.jam.entities.Globe;
import com.deeep.jam.screens.Core;

import static com.deeep.jam.PixmapRotater.getRotatedPixmap;

/**
 * Created by Andreas on 05/12/2014.
 */
public class World {

    public static Globe globe;
    public static ShapeRenderer sR = Core.shapeRenderer;
    public static BlobManager blobManager;

    public World() {
        globe = new Globe();
        blobManager = new BlobManager();
    }

    public void update(float deltaT) {
        Gdx.input.setCursorImage(getRotatedPixmap(new Pixmap(Gdx.files.internal("kappa.png")), (float) Math.toDegrees(getMouseAngle()) + 180F), 16, 16);
        globe.update(deltaT);
        blobManager.update(deltaT);
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        globe.draw(batch);
        batch.end();
        sR.begin();
        blobManager.draw();
        sR.end();
    }

    public void mouseMoved(int x, int y) {

    }

    /**
     * Calculates the angle of the mouse relative to the center of the screen
     * @return the angle in radians
     */
    public float getMouseAngle() {
        float mX = Gdx.input.getX() - 256F;
        float mY = Gdx.input.getY() - 256F;
        return (float) Math.atan2(mY, mX);
    }
}