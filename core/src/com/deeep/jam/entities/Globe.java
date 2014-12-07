package com.deeep.jam.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by E on 12/6/2014.
 */
public class Globe extends Entity {
    GlobeImage globeImage;
    private float angleFacing = 0;
    private int planetSize = 128;
    private float freezeAngle;
    private boolean frozen = false;
    private float freezeTimer = 0;

    public void setAngleFacing(float angleFacing){
        if(frozen)
            this.angleFacing = freezeAngle;
        else
            this.angleFacing = angleFacing;
    }

    public float getAngleFacing(){
        if(frozen){
            return freezeAngle;
        }
        return angleFacing;
    }

    public void freeze() {
        freezeAngle = getAngleFacing();
        frozen = true;
    }

    public Globe() {

        Random random = new Random();
        globeImage = new GlobeImage(planetSize, 0.25f);
        globeImage.addRegion(Color.RED);
        //globeImage.addRegion(Color.BLUE);
        //globeImage.addRegion(Color.GREEN);
        // globeImage.addRegion(Color.CYAN);

    }

    public Color randomColour() {
        Random random = new Random();
        Color color = new Color();
        color.a = 1;
        color.r = random.nextInt(255);
        color.g = random.nextInt(255);
        color.b = random.nextInt(255);
        return color;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            setAngleFacing(getAngleFacing()+ Gdx.graphics.getDeltaTime() * 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            setAngleFacing(getAngleFacing()- Gdx.graphics.getDeltaTime() * 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            globeImage.clear();
            Random random = new Random();
            for (int i = 0, l = random.nextInt(4) + 2; i < l; i++) {
                globeImage.addRegion(randomColour());
            }
        }
        if(frozen){
            freezeTimer+=Gdx.graphics.getDeltaTime();
            if(freezeTimer>=2){
                frozen = false;
                freezeTimer = 0;
            }
        }
        globeImage.draw(spriteBatch, 256, 256, (float) Math.toDegrees(angleFacing));
        int deltaX = Gdx.input.getX() - 256;
        int deltaY = 512 - Gdx.input.getY() - 256;
        int distance = (int) Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        float rotation = (float) Math.atan2(deltaY,deltaX);
        System.out.println(globeImage.getColor((int) distance, rotation));
        //globeImage.getColor((int) Gdx.input.getX(), (int)(512-Gdx.input.getY()));
    }

    public Color getColor(float angle, float distance) {
        return globeImage.getColor((int) distance, angle);
    }

    public GlobeImage getGlobeImage() {
        return globeImage;
    }

    @Override
    public void update(float deltaT) {
        super.update(deltaT);
    }
}