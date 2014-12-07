package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

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
    private float rotation;

    public GlobeImage(float globeSize, float scale) {
        this.globeSize = globeSize - 1;
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
        this.rotation = (float) Math.toRadians(rotation);
    }

    public void addRegion(Color color) {
        regions.add(new Region(color));
        System.out.println("Size: " + regions.size());
        calculatePixmap();
    }

    public void removeRegion(int index) {
        System.out.println("Found region");
        regions.remove(index);
        calculatePixmap();
    }

    public void removeRegion(Color color) {
        for (Region region : regions) {
            System.out.println(color + " - " + region.color);
            if (region.color.equals(color)) {
                regions.remove(region);
                System.out.println("Found region");
                break;
            }
        }
        calculatePixmap();
    }

    public Color getRandomColor(){
        Random random = new Random();
        Color color = new Color(regions.get(random.nextInt(regions.size())).color);
        //System.out.println(color);
        //color.rgba8888(regions.get(random.nextInt(regions.size())).color);
        //System.out.println(color);
        return color;
    }

    public Color getColor(int x, int y) {
        x -= 256;
        y -= 256;
        int distance = (int) Math.sqrt(x * x + y * y);
        if (Math.abs(distance) > globeSize * scale) {
            return null;
        }
        float otherRotation = (float) ((float) Math.atan2(y, x) + Math.PI);
        System.out.println("otherRotation: " + otherRotation);
        int tempX = (int) (Math.cos(otherRotation) * distance);
        int tempY = (int) (Math.sin(otherRotation) * distance);

        Color color = new Color(pixmap.getPixel(tempX + width / 2, tempY + height / 2));
        return color;
    }

    public Color getColor(int distance, float rotation) {
        if (Math.abs(distance) > globeSize * scale) {
            return null;
        }
        rotation += this.rotation;
        // if(regions.size()%2!=0){
        rotation += Math.PI;
        //}
        int tempX = (int) (Math.cos(rotation) * distance);
        int tempY = (int) (Math.sin(rotation) * distance);

        Color color = new Color(pixmap.getPixel(tempX + width / 2, tempY + height / 2));
        return color;
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
                if ((int) distance >= globeSize) {
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
        pixmap.setColor(Color.WHITE);
        pixmap.drawCircle(width / 2, height / 2, (int) globeSize);
        pixmap.drawCircle(width / 2, height / 2, (int) globeSize - 1);
        pixmap.drawCircle(width / 2, height / 2, (int) globeSize - 2);
        pixmap.drawCircle(width / 2, height / 2, (int) globeSize - 3);
        pixmap.drawPixel((width - 1) / 2, (height - 1) / 2);
        pixmap.setFilter(Pixmap.Filter.NearestNeighbour);

        Texture texture = new Texture(pixmap);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
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
