package com.deeep.jam.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by E on 12/6/2014.
 */
public class Globe extends Entity {
    private ArrayList<Region> regions = new ArrayList<Region>();
    private int amountRegions = 2;
    private float degreesPerPart = 360 / 2;
    private float angleFacing = 0;
    private Pixmap pixmap;
    private Texture texture;
    private int planetSize = 32;
    private Sprite sprite;

    public Globe() {
        regions.add(new Region(0, Color.BLUE));
        regions.add(new Region(1, Color.RED));
        regions.add(new Region(2, Color.GREEN));
        regions.add(new Region(3, Color.PURPLE));
        amountRegions = regions.size();
        degreesPerPart = 360 / amountRegions;
        System.out.println(degreesPerPart);
        pixmap = new Pixmap(128, 128, Pixmap.Format.RGBA4444);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        pixmap.setColor(Color.WHITE);
        pixmap.fillCircle(64, 64, planetSize);
        float tempAngle = 0;

        int tempX = 0;
        int tempY = 0;
        float angle = 0;
        float distance = 0;

        for (int x = -0; x < 128; x++) {
            for (int y = -0; y < 128; y++) {
                tempX = x - 64;
                tempY = y - 64;
                distance = (float) Math.sqrt(tempX * tempX + tempY * tempY);
                angle = (float) Math.atan2(tempY, tempX);
                if (distance > planetSize + 1) {
                    continue;
                }
                int degrees = (int) Math.toDegrees(angle);
                System.out.println("d: " + distance + " r" + degrees + " (" + tempX + ", " + tempY + ")");
                degrees += 180;
                if (degrees < 90)
                    pixmap.setColor(Color.GREEN);
                else if (degrees < 180)
                    pixmap.setColor(Color.BLUE);
                else if(degrees < 270)
                    pixmap.setColor(Color.PURPLE);
                else
                    pixmap.setColor(Color.RED);
                pixmap.drawPixel(tempX + 64, tempY + 64);

            }
        }

        texture = new Texture(pixmap);
        sprite = new Sprite(texture);

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        sprite.setRotation((float) Math.toDegrees(angleFacing));
        sprite.setCenter(256, 256);
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
