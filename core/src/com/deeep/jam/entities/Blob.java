package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.World;
import com.deeep.jam.input.Assets;

import static com.deeep.jam.World.*;

/**
 * Created by Andreas on 12/6/2014.
 */
public class Blob extends Entity {

    //Theta ALWAYS in radians
    public float v, d, theta;
    public boolean isDead = false;
    public Color color;

    public Blob (float d, float theta, float v, Color color){
        this.v = v;
        this.d = d;
        this.theta = theta;
        calculatePosition();
        this.color = color;
    }

    @Override
    public void update (float deltaT) {
        calculatePosition();
        d -= v * deltaT;
    }

    public void draw () {
        sR.setColor(color);
        sR.circle(x, y, 5F);
    }


    private void calculatePosition() {
        x = 256+(float) Math.cos((theta)) * d;
        y = 256+(float) Math.sin((theta)) * d;
    }

    public void die(){
        isDead = true;
        Assets.getAssets().pointsGained.play();

    }

}
