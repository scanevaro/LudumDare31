package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by E on 12/6/2014.
 */
public class Globe extends Entity {
    private int amountRegions = 2;
    private float degreesPerPart = 360 / 2;
    private float angleFacing = 1f;

    public Globe() {
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }

    @Override
    public void update(float deltaT) {
        super.update(deltaT);

    }
}
