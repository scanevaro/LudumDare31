package com.deeep.jam.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.World;

import java.util.Random;

/**
 * Created by Andreas on 12/6/2014.
 */
public class PowerBlob extends Entity{

    private Random random;
    private Sprite sprite;
    public boolean isDead = false;
    private float deathTimer;

    public PowerBlob(){
        deathTimer = 5F;
        random = new Random();
        sprite = new Sprite(new Texture(Gdx.files.internal("images/powerup.png")));
        int r = random.nextInt(4);
        switch(r) {
            case 0:
                //North rectangle
                x = 8 + random.nextInt(464);
                y = 280 + random.nextInt(208);
                break;
            case 1:
                //East rectangle
                x = 280 + random.nextInt(208);
                y = 8 + random.nextInt(464);
                break;
            case 2:
                //South rectangle
                x = 8 + random.nextInt(464);
                y = 8 + random.nextInt(208);
                break;
            case 3:
                x = 8 + random.nextInt(208);
                y = 8 + random.nextInt(464);
                //west rectangle
                break;
            default:
                break;
        }
    }

    /**
     * ヽヽ༼༼ຈຈل͜ل͜ຈຈ༽༽ﾉﾉ Elmar, turn off the vibrator ヽヽ༼༼ຈຈل͜ل͜ຈຈ༽༽ﾉﾉ
     */

    @Override
    public void update(float deltaT){
        if(deathTimer < 2) {
            isDead = true;
        }
        if(isDead) return;
        deathTimer -= deltaT;
        sprite.setX(x);
        sprite.setY(y);
        sprite.setScale(0.5F, 0.5F);
        sprite.setAlpha(deathTimer/5);
    }


    public void draw(SpriteBatch spriteBatch){
        if(isDead) return;
        sprite.draw(spriteBatch);
    }

    public void die() {
        isDead = true;
    }
}
