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
import com.deeep.jam.BlurUtils;

/**
 * Created by E on 12/6/2014.
 */
public class Globe extends Entity {
    GlobeImage globeImage;
    public float angleFacing = 0;
    private int planetSize = 128;

    public Globe() {

        Random random = new Random();
        globeImage = new GlobeImage(planetSize, 0.25f);
        globeImage.addRegion(Color.RED);
        globeImage.addRegion(Color.BLUE);
        globeImage.addRegion(Color.GREEN);
        globeImage.addRegion(Color.OLIVE);

    }

    public Color randomColour() {
        Random random = new Random();
        Color color = new Color();
        color.a = 1;
        color.r = random.nextFloat();
        color.g = random.nextFloat();
        color.b = random.nextFloat();
        return color;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angleFacing += Gdx.graphics.getDeltaTime() *5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angleFacing -= Gdx.graphics.getDeltaTime() *5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            globeImage.clear();
            Random random = new Random();
            for (int i = 0, l = random.nextInt(4) + 2; i < l; i++) {
                globeImage.addRegion(randomColour());
            }
        }
        globeImage.draw(spriteBatch, 256, 256, (float) Math.toDegrees(angleFacing));
        int deltaX = Gdx.input.getX() - 256;
        int deltaY = 512- Gdx.input.getY() - 256;
        float rotation = (float) ((float) Math.atan2(deltaX, deltaY) + Math.PI/2);
        float distance = (float) Math.sqrt(deltaX*deltaX + deltaY * deltaY);
        globeImage.getColor((int) distance, rotation);
    }

    public Color getColor(float angle, float distance){
        return globeImage.getColor((int) distance,angle);
    }

    public GlobeImage getGlobeImage(){
        return globeImage;
    }

    @Override
    public void update(float deltaT) {
        super.update(deltaT);
    }
}