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

    public PowerBlob(){
        random = new Random();
        sprite = new Sprite(new Texture(Gdx.files.internal("images/powerup.png")));
        int r = random.nextInt(4);
        switch(r) {
            case 0:
                //North rectangle
                x = 24 + random.nextInt(464);
                y = 280 + random.nextInt(208);
                break;
            case 1:
                //East rectangle
                x = 280 + random.nextInt(208);
                y = 24 + random.nextInt(464);
                break;
            case 2:
                //South rectangle
                x = 24 + random.nextInt(464);
                y = 24 + random.nextInt(208);
                break;
            case 3:
                x = 24 + random.nextInt(208);
                y = 24 + random.nextInt(464);
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

    }


    public void render(SpriteBatch spriteBatch){
        sprite.draw(spriteBatch);
    }

}
