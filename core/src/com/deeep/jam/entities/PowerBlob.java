package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.World;

import java.util.Random;

/**
 * Created by Andreas on 12/6/2014.
 */
public class PowerBlob extends Entity{

    private Random random;

    public PowerBlob(){
        random = new Random();
        int r = random.nextInt(4);
        switch(r) {
            case 0:
                //North rectangle
                x = 12 + random.nextInt(488);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
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
        World.sR.setColor(Color.WHITE);
        World.sR.rect(x, y, 10, 10);
    }

}
