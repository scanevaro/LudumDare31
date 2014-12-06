package com.deeep.jam.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

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
        globeImage.addRegion(randomColour(random));
        globeImage.addRegion(randomColour(random));
        globeImage.addRegion(randomColour(random));

    }

    public Color randomColour(Random random) {
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
            angleFacing += Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angleFacing -= Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            globeImage.clear();
            Random random = new Random();
            for (int i = 0, l = random.nextInt(4) + 2; i < l; i++) {
                globeImage.addRegion(randomColour(random));
            }
        }
        globeImage.draw(spriteBatch, 256, 256, (float) Math.toDegrees(angleFacing));
    }

    @Override
    public void update(float deltaT) {
        super.update(deltaT);
    }
}