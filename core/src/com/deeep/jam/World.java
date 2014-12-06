package com.deeep.jam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.deeep.jam.entities.*;
import com.deeep.jam.input.Assets;
import com.deeep.jam.screens.Core;

import java.util.Random;

import static com.deeep.jam.PixmapRotater.getRotatedPixmap;

/**
 * Created by Andreas on 05/12/2014.
 */
public class World {
    public static int score = 0;
    private Roulette roulette;
    private BitmapFont bitmapFont;

    public static Globe globe;
    public static ShapeRenderer sR = Core.shapeRenderer;
    public static BlobManager blobManager;

    private int damageTimer;
    private float backgroundRotation;
    private Difficulty difficulty;

    public static Sprite background;
    public static Sprite warningOverlay;

    /**
     * ༼ง ͠ຈ ͟ل͜ ͠ຈ༽ง gimme my memes ༼ง ͠ຈ ͟ل͜ ͠ຈ༽ง
     */

    public World() {
        Assets.getAssets().loadBitmapFont();
        bitmapFont = Assets.getAssets().getBitmapFont();
        difficulty = new Difficulty();
        globe = new Globe();
        blobManager = new BlobManager();
        background = new Sprite(new Texture(Gdx.files.internal("background.png")));
        warningOverlay = new Sprite(new Texture(Gdx.files.internal("warning_overlay.png")));
        background.setX(-110F);
        background.setY(-110F);
        background.setRotation(90F);
        damageTimer = 0;
        difficulty.spawn(globe);
        roulette = new Roulette();

    }

    public void update(float deltaT) {
        backgroundRotation = -globe.angleFacing;
        background.setRotation((float) Math.toDegrees(backgroundRotation));
        Gdx.input.setCursorImage(getRotatedPixmap(Assets.getAssets().getKappaPixmap(), (float) Math.toDegrees(getMouseAngle()) + 180F), 16, 16);
        globe.update(deltaT);
        blobManager.update(deltaT);
        Random random = new Random();
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
                        difficulty.kill(globe);
                        Assets.getAssets().pointsGained.play();
                        break;
                    } else {
                        difficulty.playerHit(globe);
                        damageTimer += 100;
                        Assets.getAssets().incorrect.play();
                        break;
                    }
                }
            }
        }
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
        bitmapFont.draw(batch, "" + difficulty.consecutive, 512 - 25, 512-25);
        batch.end();
        sR.setAutoShapeType(true);
        sR.begin();
        blobManager.draw();
        sR.end();
        batch.begin();
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