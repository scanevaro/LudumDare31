package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.World;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by Andreas on 12/7/2014.
 */

public class PowerBlobManager {

    public ArrayList<PowerBlob> powerBlobs = new ArrayList<PowerBlob>();
    private double powerTimer;
    private Random random;

    public PowerBlobManager(){
        random = new Random();
    }

    public void update(float deltaT, World world){
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
        if(powerTimer > 8){
            powerTimer = 0;
            spawnPowerBlob(world);
        }else{
            powerTimer +=deltaT;
        }
    }

    private void spawnPowerBlob(World world) {
        if(world.difficulty.maxEnemiesAlive > 0) powerBlobs.add(new PowerBlob());
    }

    public void draw(SpriteBatch spriteBatch){
        ListIterator iT = powerBlobs.listIterator();
        while (iT.hasNext()){
            PowerBlob powerBlob = (PowerBlob) iT.next();
            powerBlob.draw(spriteBatch);
        }
    }

}
