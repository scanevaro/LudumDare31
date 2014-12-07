package com.deeep.jam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.deeep.jam.entities.*;
import com.deeep.jam.input.Assets;
import com.deeep.jam.screens.Core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static com.deeep.jam.PixmapRotater.getRotatedPixmap;

/**
 * Created by Andreas on 05/12/2014.
 */
public class World {
    public int score = 0;
    private Roulette roulette;
    private BitmapFont bitmapFont;

    public Globe globe;
    public ShapeRenderer sR = Core.shapeRenderer;
    public BlobManager blobManager;
    PowerBlobManager powerBlobManager;

    public int damageTimer;
    private float backgroundRotation;
    public Difficulty difficulty;

    public Sprite background;
    public Sprite warningOverlay;
    private ArrayList<Circle> circles = new ArrayList<Circle>();

    /**
     * ༼ง ͠ຈ ͟ل͜ ͠ຈ༽ง gimme my memes ༼ง ͠ຈ ͟ل͜ ͠ຈ༽ง
     */

    public World() {
        Assets.getAssets().loadBitmapFont();
        bitmapFont = Assets.getAssets().getBitmapFont();
        difficulty = new Difficulty();
        globe = new Globe();
        blobManager = new BlobManager();
        powerBlobManager = new PowerBlobManager();
        background = new Sprite(new Texture(Gdx.files.internal("background.png")));
        warningOverlay = new Sprite(new Texture(Gdx.files.internal("warning_overlay.png")));
        background.setX(-110F);
        background.setY(-110F);
        background.setRotation(90F);
        damageTimer = 0;
        difficulty.kill(globe, blobManager);
        difficulty.kill(globe, blobManager);
        difficulty.kill(globe, blobManager);
        difficulty.kill(globe, blobManager);
        difficulty.kill(globe, blobManager);
        difficulty.kill(globe, blobManager);
        difficulty.kill(globe, blobManager);
        difficulty.kill(globe, blobManager);
        difficulty.kill(globe, blobManager);
        difficulty.spawn(globe, blobManager);
        roulette = new Roulette(this, globe);

    }

    public void shockwave() {
        circles.add(new Circle(256, 256, 1));
    }

    public void update(float deltaT) {
        backgroundRotation = -globe.getAngleFacing();
        background.setRotation((float) Math.toDegrees(backgroundRotation));
        Gdx.input.setCursorImage(getRotatedPixmap(Assets.getAssets().getKappaPixmap(), (float) Math.toDegrees(getMouseAngle()) + 180F), 16, 16);
        globe.update(deltaT);
        blobManager.update(deltaT);
        ArrayList<Circle> remove = new ArrayList<Circle>();
        for (Circle circle : circles) {
            circle.radius += deltaT * 50;
            for (Blob blob : blobManager.blobs) {
                if (circle.contains(blob.x, blob.y)) {
                    blob.d = circle.radius;
                }
            }
            if (circle.radius >= 200) {
                remove.add(circle);
            }
        }
        circles.removeAll(remove);
        for (Blob blob : blobManager.blobs) {
            if (!blob.isDead) {
                float angle = (float) ((float) Math.atan2(blob.x - 256, blob.y - 256) + Math.PI / 2);
                int distance = (int) blob.d;
                Color color = globe.getColor(angle, distance);
                if (color == null) {
                    //do nothing not colliding
                } else {
                    blob.die();
                    if (color.equals(blob.color)) {
                        difficulty.kill(globe, blobManager);
                        Assets.getAssets().pointsGained.play();
                        break;
                    } else {
                        if (!globe.invincible) {
                            difficulty.playerHit(globe, blobManager);
                            damageTimer += 100;
                            Assets.getAssets().incorrect.play();
                        }
                        break;
                    }
                }
            }
        }
        for (PowerBlob powerBlob : powerBlobManager.powerBlobs) {
            if (!powerBlob.isDead) {
                float adaptedX = Gdx.input.getX() - 24;
                float adaptedY = 488 - Gdx.input.getY();
                float dX = Math.abs(adaptedX - powerBlob.x);
                float dY = Math.abs(adaptedY - powerBlob.y);
                if (dX <= 40 && dY <= 40) {
                    powerBlob.die();
                    Assets.getAssets().power.play();
                    roulette.newSession();
                }
            }
        }
        powerBlobManager.update(deltaT);
        if (damageTimer >= 1000) {
            gameOver();
        }
    }

    private void gameOver() {

    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        background.draw(batch);
        globe.draw(batch);
        roulette.draw(batch);
        bitmapFont.setScale(1);
        bitmapFont.draw(batch, "Score: " + difficulty.score, 10, bitmapFont.getLineHeight());
        bitmapFont.setScale(0.6f);
        bitmapFont.draw(batch, "Multiplier: " + difficulty.multiplier + "x", 5, 512 - 5);
        bitmapFont.setScale(0.4f);
        bitmapFont.draw(batch, "" + difficulty.consecutive, 512 - 25, 512 - 25);
        batch.end();
        sR.setAutoShapeType(true);
        sR.begin();
        Color color = new Color(0.9f, 0.4f, 0.2f, 1);
        for (Circle circle : circles) {
            for (int i = 0; i < 12; i++) {
                color.a = 1 - ((float)i / 12);
                color.r = 0.9f+ color.a/10;
                color.g = 0.4f+ color.a/10;
                color.b = 0.2f+ color.a/10;
                sR.setColor(color);
                sR.circle(circle.x, circle.y, circle.radius - i);
            }
        }
        blobManager.draw();
        sR.end();
        batch.begin();
        powerBlobManager.draw(batch);
        if (damageTimer > 0) {
            damageTimer--;
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