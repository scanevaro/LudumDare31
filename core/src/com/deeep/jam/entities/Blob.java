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

    public Color color;

    public Blob (float d, float theta, float v){
        this.v = v;
        this.d = d;
        this.theta = theta;
        calculatePosition();
        color = Color.RED;
    }

    @Override
    public void update (float deltaT) {
        calculatePosition();
        d -= v * deltaT;
        //Assets.getAssets().pointsGained.loop();
    }

    public void draw () {
        sR.setColor(Color.RED);
        sR.circle(x, y, 5F);
    }


    private void calculatePosition() {
        x = 256+(float) Math.cos((theta)) * d;
        y = 256+(float) Math.sin((theta)) * d;
    }

}
