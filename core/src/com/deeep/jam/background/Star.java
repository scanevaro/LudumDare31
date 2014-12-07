package com.deeep.jam.background;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.deeep.jam.entities.Entity;

/**
 * Created by E on 12/7/2014.
 */
public class Star extends Entity {
    boolean increment;

    float incrementSpeed;
    float alpha = 0;

    public Star(int distance, double angle, float initial, float speed, boolean up) {
        this.x = (float) (Math.cos(angle) * distance) + 256;
        this.y = (float) (Math.sin(angle) * distance) + 256;
        this.alpha = initial;
        this.increment = up;
        this.incrementSpeed = speed;
    }

    @Override
    public void update(float deltaT) {
        if (increment) {
            if (alpha + deltaT * incrementSpeed >= 1) {
                increment = false;
            } else {
                alpha += deltaT * incrementSpeed;
            }
        } else {
            if (alpha - deltaT * incrementSpeed <= 0) {
                increment = true;
            } else {
                alpha -= deltaT * incrementSpeed;
            }
        }
    }


    public void draw(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        shapeRenderer.setColor(1, 1, 1, alpha);
        shapeRenderer.rect(x, y, 2,2);
    }
}
