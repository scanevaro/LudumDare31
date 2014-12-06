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
import com.deeep.jam.input.Assets;
import com.deeep.jam.screens.Core;

import java.util.Random;

import static com.deeep.jam.PixmapRotater.getRotatedPixmap;

/**
 * Created by Andreas on 05/12/2014.
 */
public class World {

    public static Globe globe;
    public static ShapeRenderer sR = Core.shapeRenderer;
    public static BlobManager blobManager;

    private int damageTimer;
    private float backgroundRotation;

    public static Sprite background;
    public static Sprite warningOverlay;

    /**
     * ༼ง ͠ຈ ͟ل͜ ͠ຈ༽ง gimme my memes ༼ง ͠ຈ ͟ل͜ ͠ຈ༽ง
     */

    public World() {
        globe = new Globe();
        blobManager = new BlobManager();
        background = new Sprite(new Texture(Gdx.files.internal("background.png")));
        warningOverlay = new Sprite(new Texture(Gdx.files.internal("warning_overlay.png")));
        background.setX(-110F);
        background.setY(-110F);
        background.setRotation(90F);
        damageTimer = 0;
        blobManager.blobs.add(new Blob(300, (float) 0, 80, globe.getGlobeImage().getRandomColor()));
    }

    public void update(float deltaT) {
        backgroundRotation += 0.5F;
        if(backgroundRotation >= 360) backgroundRotation = 0;
        background.setRotation(backgroundRotation);
        Gdx.input.setCursorImage(getRotatedPixmap(new Pixmap(Gdx.files.internal("kappa.png")), (float) Math.toDegrees(getMouseAngle()) + 180F), 16, 16);
        globe.update(deltaT);
        blobManager.update(deltaT);
        Random random = new Random();
        for (Blob blob : blobManager.blobs) {
            float angle = (float) ((float) Math.atan2(blob.x - 256, blob.y - 256) + Math.PI / 2);
            int distance = (int) blob.d;
            Color color = globe.getColor(angle, distance);
            if (color == null) {
                //do nothing not colliding
            } else {
                if (color.equals(blob.color)) {
                    System.out.println("Same!");
                    blobManager.blobs.remove(blob);
                    blobManager.blobs.add(new Blob(random.nextInt(60) + 320, (float) (random.nextFloat()*Math.PI*2), 20 + random.nextFloat() * 40,globe.getGlobeImage().getRandomColor()));
                    blobManager.blobs.add(new Blob(random.nextInt(60) + 320, (float) (random.nextFloat()*Math.PI*2), 20 + random.nextFloat() * 40,globe.getGlobeImage().getRandomColor()));
                    //blobManager.blobs.add(new Blob(300, (float) Math.PI + 0.5f, 80));
                    Assets.getAssets().pointsGained.play();
                    return;
                } else {
                    System.out.println("Not same...!");
                    System.out.println("Blob: " + blob.color + " Region: " + color);
                    blobManager.blobs.remove(blob);
                    blobManager.blobs.add(new Blob(random.nextInt(60) + 320, (float) (random.nextFloat()*Math.PI*2), 20 + random.nextFloat() * 40,globe.getGlobeImage().getRandomColor()));
                    //blobManager.blobs.add(new Blob(300, (float) 0, 80));
                    damageTimer += 100;
                    return;
                }
            }
        }
        if(damageTimer >= 1000){
            gameOver();
        }
    }

    private void gameOver() {

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
        batch.begin();
        if(damageTimer > 0) {
            damageTimer --;
            warningOverlay.setAlpha(damageTimer * 0.002F);
            warningOverlay.draw(batch);
        }
        batch.end();
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