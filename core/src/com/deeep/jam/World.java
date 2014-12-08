package com.deeep.jam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.deeep.jam.background.Space;
import com.deeep.jam.entities.*;
import com.deeep.jam.input.Assets;
import com.deeep.jam.screens.Core;
import com.deeep.jam.screens.Menu;

import java.util.ArrayList;

/**
 * Created by Andreas on 05/12/2014.
 */
public class World {
    public static final int PLAYING = 0;
    public static final int GAMEOVER = 1;
    private static final float time = 0.5f, delay = 0.2f;
    public int score = 0;
    public Globe globe;
    public ShapeRenderer sR = Core.shapeRenderer;
    public BlobManager blobManager;
    public int damageTimer;
    public int healTimer;
    public int frostTimer;
    public int explosionTimer;
    public Difficulty difficulty;
    public Sprite background;
    public Sprite warningOverlay;
    public Sprite healOverlay;
    public Sprite frostOverlay;
    public Sprite explosionOverlay;
    public int state;
    PowerBlobManager powerBlobManager;
    private Space space;
    private Roulette roulette;
    private BitmapFont bitmapFont;
    private float backgroundRotation;
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private Menu menu;
    private Stage stage;
    private TextButton[] gameOverListChar;
    private TextButton[] scoreLabelChar;
    private TextButton[] scoreChar;
    private FloatArray post1, advances1, post2, advances2, post3, advances3;
    private String gamesOverText, scoreLabelText, scoreText;
    private TextButton.TextButtonStyle style;

    /**
     * ༼ง ͠ຈ ͟ل͜ ͠ຈ༽ง gimme my memes ༼ง ͠ຈ ͟ل͜ ͠ຈ༽ง
     */

    public World() {
        instantiate();
    }

    public void instantiate() {
        Assets.getAssets().loadBitmapFont();
        bitmapFont = Assets.getAssets().getBitmapFont();
        menu = new Menu(this);
        difficulty = new Difficulty();
        globe = new Globe();
        blobManager = new BlobManager();
        powerBlobManager = new PowerBlobManager();
        background = new Sprite(new Texture(Gdx.files.internal("background.png")));
        warningOverlay = new Sprite(new Texture(Gdx.files.internal("warning_overlay.png")));
        healOverlay = new Sprite(new Texture(Gdx.files.internal("heal_overlay.png")));
        frostOverlay = new Sprite(new Texture(Gdx.files.internal("frost_overlay.png")));
        explosionOverlay = new Sprite(new Texture(Gdx.files.internal("explosion_overlay.png")));
        background.setX(-110F);
        background.setY(-110F);
        background.setRotation(90F);
        damageTimer = 0;
        space = new Space(500);
        //difficulty.spawn(globe, blobManager);
        roulette = new Roulette(this, globe);

        stage = new Stage(new StretchViewport(512, 512), Core.batch);
        gamesOverText = "Game Over";
        scoreLabelText = "Score: ";
        gameOverListChar = new TextButton[gamesOverText.length()];
        scoreLabelChar = new TextButton[scoreLabelText.length()];
        advances1 = new FloatArray();
        advances2 = new FloatArray();
        post1 = new FloatArray();
        post2 = new FloatArray();
        Assets.getAssets().getBitmapFont().computeGlyphAdvancesAndPositions(gamesOverText, advances1, post1);
        Assets.getAssets().getBitmapFont().computeGlyphAdvancesAndPositions(scoreLabelText, advances2, post2);

        style = new TextButton.TextButtonStyle();
        style.font = Assets.getAssets().getBitmapFont();

        /** ヽ༼◕ل͜◕༽ﾉ */

        for (int i = 0; i < gamesOverText.length(); i++) {
            gameOverListChar[i] = new TextButton(String.valueOf(gamesOverText.charAt(i)), style);
            gameOverListChar[i].setTransform(true);
            gameOverListChar[i].setPosition(110, 360);
            gameOverListChar[i].setOrigin(advances1.get(i) / 2, gameOverListChar[i].getHeight() / 4);
            stage.addActor(gameOverListChar[i]);
        }

        for (int i = 0; i < scoreLabelText.length(); i++) {
            scoreLabelChar[i] = new TextButton(String.valueOf(scoreLabelText.charAt(i)), style);
            scoreLabelChar[i].setTransform(true);
            scoreLabelChar[i].setPosition(160, 360);
            scoreLabelChar[i].setOrigin(advances2.get(i) / 2, scoreLabelChar[i].getHeight() / 4);
            stage.addActor(scoreLabelChar[i]);
        }

        state = PLAYING;
    }

    private void resetText() {

        for (int i = 0; i < gamesOverText.length(); i++) {
            gameOverListChar[i].setPosition(110 + post1.get(i), 330);
            gameOverListChar[i].setOrigin(advances1.get(i) / 2, gameOverListChar[i].getHeight() / 4);
            gameOverListChar[i].setColor(0, 0, 0, 1);
            gameOverListChar[i].setScale(1f);
        }

        for (int i = 0; i < scoreLabelText.length(); i++) {
            scoreLabelChar[i].setPosition(170 + post2.get(i), 330);
            scoreLabelChar[i].setOrigin(advances2.get(i) / 2, scoreLabelChar[i].getHeight() / 4);
            scoreLabelChar[i].setColor(0, 0, 0, 1);
            scoreLabelChar[i].setScale(1f);
        }

        scoreText = String.valueOf(difficulty.score);
        scoreChar = new TextButton[scoreText.length()];
        advances3 = new FloatArray();
        post3 = new FloatArray();
        Assets.getAssets().getBitmapFont().computeGlyphAdvancesAndPositions(scoreText, advances3, post3);
        for (int i = 0; i < scoreText.length(); i++) {
            scoreChar[i] = new TextButton(String.valueOf(scoreText.charAt(i)), style);
            scoreChar[i].setTransform(true);
            scoreChar[i].setPosition(160, 360);
            scoreChar[i].setOrigin(advances3.get(i) / 2, scoreChar[i].getHeight() / 4);
            stage.addActor(scoreChar[i]);
        }
        for (int i = 0; i < scoreText.length(); i++) {
            scoreLabelChar[i].setPosition(170 + post3.get(i), 330);
            scoreLabelChar[i].setOrigin(advances3.get(i) / 2, scoreChar[i].getHeight() / 4);
            scoreLabelChar[i].setColor(0, 0, 0, 1);
            scoreLabelChar[i].setScale(1f);
        }
    }

    private void prepareDrop() {
        int tempX = 0;
        for (int i = 0; i < gamesOverText.length(); i++) {
            gameOverListChar[i].setY(330 + 200f);
            gameOverListChar[i].setColor(0, 0, 0, 0);
            gameOverListChar[i].addAction(Actions.delay(delay * i, Actions.parallel(Actions.alpha(1, time),
                    Actions.moveTo(100 + post1.get(i) + tempX, 330, time, Interpolation.bounceOut))));
            tempX += 15;
        }

        tempX = 0;
        for (int i = 0; i < scoreLabelText.length(); i++) {
            scoreLabelChar[i].setY(330 + 200f);
            scoreLabelChar[i].setColor(0, 0, 0, 0);
            scoreLabelChar[i].addAction(Actions.delay(delay * i, Actions.parallel(Actions.alpha(1, time),
                    Actions.moveTo(150 + post2.get(i) + tempX, 200, time, Interpolation.bounceOut))));
            tempX += 15;
        }

        /** Score */
        for (int i = 0; i < scoreText.length(); i++) {
            scoreChar[i].setY(330 + 200f);
            scoreChar[i].setColor(0, 0, 0, 0);
            scoreChar[i].addAction(Actions.delay(delay * i, Actions.parallel(Actions.alpha(1, time),
                    Actions.moveTo(100 + post3.get(i) + tempX, 150, time, Interpolation.bounceOut))));
        }
    }

    public void shockwave() {
        circles.add(new Circle(256, 256, 1));
    }

    public void update(float deltaT) {
        switch (state) {
            case PLAYING:
                space.update(deltaT);
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
                            if (globe.armor == 0) {
                                if (color.r == blob.color.r && color.g == blob.color.g && color.b == blob.color.b) {
                                    difficulty.kill(globe, blobManager);
                                    Assets.getAssets().pointsGained.play();
                                    blob.die();
                                } else {
                                    if (globe.angelBlock > 0) {
                                        globe.angelBlock--;
                                        difficulty.kill(globe, blobManager);
                                        Assets.getAssets().pointsGained.play();
                                    } else {
                                        difficulty.playerHit(globe, blobManager);
                                        damageTimer += 200;
                                        Assets.getAssets().incorrect.play();
                                    }
                                    blob.die();
                                }
                            } else {
                                globe.armor--;
                                Assets.getAssets().pling.play();
                                difficulty.enemiesAlive--;
                                blob.die();
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
                powerBlobManager.update(deltaT, this);
                if (damageTimer >= 1000)
                    gameOver();

                difficulty.spawn(globe, blobManager);

                /** HighScores open*/
                if (menu.showHighscores && Gdx.input.justTouched()) {
                    menu.showHighscores = false;
                    instantiate();
                }
                break;
            case GAMEOVER:
                stage.act();

                if (Gdx.input.justTouched())
                    instantiate();

                break;
        }
    }

    private void gameOver() {
        state = GAMEOVER;
        /** Prepare game over gamesOverText */
        resetText();
        prepareDrop();

        Settings.addScore(difficulty.score);
        Settings.save();
    }

    public void draw(SpriteBatch batch) {

        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sR.begin(ShapeRenderer.ShapeType.Filled);
        sR.setColor(Color.BLACK);
        sR.rect(0, 0, 512, 512);
        space.draw(batch, sR);
        sR.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
        globe.draw(batch);

        if (state == PLAYING) {
            roulette.draw(batch);

            bitmapFont.setScale(1);
            bitmapFont.draw(batch, "Score: " + difficulty.score, 10, 512 - 5);
            int tempY = (int) bitmapFont.getLineHeight();
            bitmapFont.setScale(0.5f);
            bitmapFont.draw(batch, "Multiplier: " + difficulty.getMultiplier() + "x", 10, 512 - 10 - tempY + bitmapFont.getLineHeight());
            bitmapFont.setScale(0.4f);
            bitmapFont.draw(batch, "" + difficulty.consecutive, 512 - 25, 512 - 25);

        }

        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sR.setAutoShapeType(true);
        sR.begin();
        sR.setColor(Color.YELLOW);
        for (int i = 0; i < globe.angelBlock; i++) {
            sR.circle(256, 256, 70 + i);
        }
        Color color = new Color(0.9f, 0.4f, 0.2f, 1);
        for (Circle circle : circles) {
            for (int i = 0; i < 12; i++) {
                color.a = 1 - ((float) i / 12);
                color.r = 0.9f + color.a / 10;
                color.g = 0.4f + color.a / 10;
                color.b = 0.2f + color.a / 10;
                sR.setColor(color);
                sR.circle(circle.x, circle.y, circle.radius - i);
            }
        }
        sR.end();
        sR.begin(ShapeRenderer.ShapeType.Filled);
        blobManager.draw();
        sR.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.begin();
        powerBlobManager.draw(batch);
        if (damageTimer > 0) {
            damageTimer--;
            warningOverlay.setAlpha(damageTimer * 0.001F);
            warningOverlay.draw(batch);
        }
        if (healTimer > 0) {
            healTimer--;
            healOverlay.setAlpha(healTimer * 0.004F);
            healOverlay.draw(batch);
        }
        if (frostTimer > 0) {
            frostTimer--;
            frostOverlay.setAlpha(frostTimer * 0.004F);
            frostOverlay.draw(batch);
        }
        if (explosionTimer > 0) {
            explosionTimer--;
            explosionOverlay.setAlpha(explosionTimer * 0.004F);
            explosionOverlay.draw(batch);
        }
        menu.draw(batch);

        if (menu.showHighscores) {
            bitmapFont.setScale(1);
            int tempY = 350;
            int tempX = 1;
            for (int i = 0; i < Settings.highscores.length; i++) {
                bitmapFont.draw(batch, tempX + ") " + Settings.highscores[i], 10, tempY);
                tempY -= 50;
                tempX++;
            }
        }

        batch.end();

        if (state == GAMEOVER)
            stage.draw();
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