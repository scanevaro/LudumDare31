package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.deeep.jam.screens.Core;

/**
 * Created by Andreas on 12/6/2014.
 */
public class Blob extends Entity {

    public Blob (){
        this.x = 0F;
        this.y = 0F;
    }

    public Blob (float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void update (float deltaT) {

    }

    @Override
    public void draw (SpriteBatch spriteBatch) {
        ShapeRenderer sR = Core.shapeRenderer;
        sR.circle(x, y, 5F);
    }

}
