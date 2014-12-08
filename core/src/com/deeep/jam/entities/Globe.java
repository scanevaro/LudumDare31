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
    public boolean invincible = false;
    private float invincibleTimer = 0, armorTimer;
    public int armor;
    private Sprite armorSprite;

    public int angelBlock = 0;

    public Color color = new Color();


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
        globeImage = new GlobeImage(planetSize, 0.25f);
        globeImage.addRegion(Color.RED);
        armorSprite = new Sprite(new Texture(Gdx.files.internal("shield.png")));
        armorSprite.setCenter(256, 256);
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
        if(invincible){
            invincibleTimer+=Gdx.graphics.getDeltaTime();
            if(invincibleTimer>=4){
                invincibleTimer = 0;
                invincible = false;
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
        color = globeImage.getColor(distance, rotation);
        if(armor != 0) armorSprite.draw(spriteBatch);
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
        if(armorTimer > 0) armorTimer --;
        if(armorTimer == 0) armor = 0;
    }

    public void armor() {
        this.armor = 5;
        this.armorTimer = 300;
    }
}