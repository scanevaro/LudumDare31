package com.deeep.jam.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by E on 12/6/2014.
 */
public class Globe extends Entity {
    private ArrayList<Region> regions = new ArrayList<Region>();
    private int amountRegions = 2;
    private float degreesPerPart = 360 / 2;
    private float angleFacing = 1f;
    private Pixmap pixmap;
    private Texture texture;
    private int planetSize = 32;
    private Sprite sprite;
    public Globe() {
        regions.add(new Region(0,Color.BLUE));
        regions.add(new Region(1,Color.RED));
        regions.add(new Region(2,Color.GREEN));
        amountRegions = regions.size();
        degreesPerPart = 360/amountRegions;
        pixmap = new Pixmap(128, 128, Pixmap.Format.RGBA4444);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        pixmap.setColor(Color.WHITE);
        pixmap.fillCircle(64, 64, (int) planetSize);
        //pixmap.fillTriangle();
        texture = new Texture(pixmap);
        sprite = new Sprite(texture);

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        sprite.setRotation((float) Math.toDegrees(angleFacing));
        sprite.setCenter(256,256);
        sprite.draw(spriteBatch);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angleFacing += Gdx.graphics.getDeltaTime() / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angleFacing -= Gdx.graphics.getDeltaTime() / 2;
        }
    }

    @Override
    public void update(float deltaT) {
        super.update(deltaT);

    }

    static class Region {
        int index;
        Color color;

        public Region(int index, Color color) {
            this.index = index;
            this.color = color;
        }
    }
}
