package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by Andreas on 12/7/2014.
 */

public class PowerBlobManager {

    public ArrayList<PowerBlob> powerBlobs = new ArrayList<PowerBlob>();
    private long powerTimer;
    private Random random;

    public PowerBlobManager(){
        random = new Random();
    }

    public void update(float deltaT){
        ListIterator iT = powerBlobs.listIterator();
        ArrayList<PowerBlob> removeBlobs= new ArrayList<PowerBlob>();
        while (iT.hasNext()){
            PowerBlob powerBlob = (PowerBlob) iT.next();
            powerBlob.update(deltaT);
            if(powerBlob.isDead){
                removeBlobs.add(powerBlob);
            }
        }
        powerBlobs.removeAll(removeBlobs);
        if(powerTimer > 1){
            powerTimer = 0;
            spawnPowerBlob();
        }else{
            powerTimer ++;
        }
    }

    private void spawnPowerBlob() {
        System.out.println("Spawned");
        powerBlobs.add(new PowerBlob());
    }

    public void draw(SpriteBatch spriteBatch){
        ListIterator iT = powerBlobs.listIterator();
        while (iT.hasNext()){
            PowerBlob powerBlob = (PowerBlob) iT.next();
            powerBlob.draw(spriteBatch);
        }
    }

}
