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
    private ArrayList<Region> regions = new ArrayList<Region>();
    private int amountRegions = 2;
    private float degreesPerPart = 360 / 2;
    private float angleFacing = 0;
    private Pixmap pixmap;
    private Texture texture;
    private int planetSize = 32;
    private Sprite sprite;

    public Globe() {
        addRandomColour();
        addRandomColour();
        addRandomColour();
        addRandomColour();


        pixmap = new Pixmap(256, 256, Pixmap.Format.RGBA4444);
        pixmap.setColor(Color.WHITE);
        pixmap.fillCircle(64, 64, planetSize);
        sprite = new Sprite();
        setSprite();

    }

    private void setSprite() {
        float tempX, tempY;
        float angle, distance;
        amountRegions = regions.size();

        degreesPerPart = 360 / amountRegions;
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
                degrees += 180;
                for (int i = 0; i < regions.size() + 1; i++) {
                    if (degrees <= degreesPerPart * i) {
                        pixmap.setColor(regions.get(i-1).color);
                        break;
                    }
                }
                pixmap.drawPixel((int) tempX + 64, (int) tempY + 64);

            }
        }
        Pixmap blurred = BlurUtils.blur(pixmap, 2, 3, false);
        texture = new Texture(blurred);
        sprite.setTexture(texture);
        sprite = new Sprite(texture);
    }

    public void addRandomColour(){
        Region region = null;
        Random random = new Random();
        region = new Region(0,randomColour(random));
        regions.add(region);
    }

    public Color randomColour(Random random){
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
        sprite.setRotation((float) Math.toDegrees(angleFacing));
        sprite.setScale(0.5f,0.5f);
        sprite.setCenter(256, 256);
        sprite.draw(spriteBatch);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            angleFacing += Gdx.graphics.getDeltaTime() / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            angleFacing -= Gdx.graphics.getDeltaTime() / 2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            regions.clear();
            Random random = new Random();
            for(int i = 0, l = random.nextInt(4)+2; i<l; i++){
                addRandomColour();
            }
            setSprite();
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
