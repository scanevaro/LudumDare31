package com.deeep.jam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    public static Sprite background;

    public World() {
        globe = new Globe();
        blobManager = new BlobManager();
        background = new Sprite(new Texture(Gdx.files.internal("background.png")));
        background.setX(-110F);
        background.setY(-110F);
        background.setRotation(90F);
        blobManager.blobs.add(new Blob(200,45,40));
    }

    public void update(float deltaT) {
        Gdx.input.setCursorImage(getRotatedPixmap(new Pixmap(Gdx.files.internal("kappa.png")), (float) Math.toDegrees(getMouseAngle()) + 180F), 16, 16);
        globe.update(deltaT);
        blobManager.update(deltaT);
        for(Blob blob : blobManager.blobs){
            Color color = globe.getColor(blob.theta, blob.d);
            if(color==null){
                //do nothing not colliding
            }else {
                if(color.equals(blob.color)){
                    System.out.println("Same!");
                }else{
                    System.out.println("Not same...!");
                }
            }
        }
    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        background.draw(batch);
        globe.draw(batch);
        batch.end();
        sR.setAutoShapeType(true);
        sR.begin();
        blobManager.draw();
        sR.end();
    }

    /**
     * Calculates the angle of the mouse relative to the center of the screen
     *
     * @return the angle in radians
     */
    public float getMouseAngle() {
        float mX = Gdx.input.getX() - 256F;
        float mY = Gdx.input.getY() - 256F;
        return (float) Math.atan2(mY, mX);
    }
}