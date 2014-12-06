package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.BlurUtils;

import java.util.ArrayList;

/**
 * Created by E on 12/7/2014.
 */
public class GlobeImage {
    private ArrayList<Region> regions = new ArrayList<Region>();
    private Pixmap pixmap;
    private Pixmap blurredImage;
    private Sprite sprite;
    private float scale;
    //width and height of pixmap
    private int width, height;
    private float globeSize;

    public GlobeImage(float globeSize, float scale) {
        this.globeSize = globeSize;
        this.width = 256;
        this.height = 256;
        this.scale = scale;
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA4444);
        blurredImage = new Pixmap(width, height, Pixmap.Format.RGBA4444);
        sprite = new Sprite();
        calculatePixmap();
    }

    public float getGlobeSize() {
        return globeSize;
    }

    public void setGlobeSize(float globeSize) {
        this.globeSize = globeSize;
        calculatePixmap();
    }

    public void clear() {
        regions.clear();
    }

    public void draw(SpriteBatch spriteBatch, int x, int y, float rotation) {
        sprite.setScale(scale, scale);
        sprite.setCenter(x, y);
        sprite.setRotation(rotation);
        sprite.draw(spriteBatch);
    }

    public void addRegion(Color color) {
        regions.add(new Region(color));
        calculatePixmap();
    }

    public void removeRegion(int index) {
        regions.remove(index);
        calculatePixmap();
    }

    public void removeRegion(Color color) {
        for (Region region : regions) {
            if (region.color.equals(color)) {
                regions.remove(region);
                break;
            }
        }
        calculatePixmap();
    }

    private void calculatePixmap() {
        float tempX, tempY;
        float angle, distance;
        if (regions.size() == 0)
            return;
        float degreesPerPart = 360 / regions.size();
        for (int x = -0; x < width; x++) {
            for (int y = -0; y < height; y++) {
                tempX = x - width / 2;
                tempY = y - height / 2;
                distance = (float) Math.sqrt(tempX * tempX + tempY * tempY);
                angle = (float) Math.atan2(tempY, tempX);
                if (distance > globeSize + 1) {
                    continue;
                }
                int degrees = (int) Math.toDegrees(angle);
                degrees += 180;
                for (int i = 0; i < regions.size() + 1; i++) {
                    if (degrees <= degreesPerPart * i) {
                        pixmap.setColor(regions.get(i - 1).color);
                        break;
                    }
                }
                pixmap.drawPixel((int) tempX + width / 2, (int) tempY + height / 2);

            }
        }
        Pixmap blurred = BlurUtils.blur(pixmap, 2, 2, false);
        Texture texture = new Texture(blurred);
        sprite.setTexture(texture);
        sprite = new Sprite(texture);
    }

    static class Region {
        public Color color;

        public Region(Color color) {
            this.color = color;
        }
    }
}
