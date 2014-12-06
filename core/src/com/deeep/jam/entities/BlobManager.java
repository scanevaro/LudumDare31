package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by Andreas on 12/6/2014.
 */

public class BlobManager {

        private Random random;
        private long gameTime, powerTimer;
        public ArrayList<Blob> blobs = new ArrayList<Blob>();
        private boolean isPowerTicking;

        public BlobManager(){
            random = new Random();
        }

        public void update(float deltaT){
            gameTime ++;
            if(!isPowerTicking){
                isPowerTicking = true;
            }else{
                if(powerTimer > 1200 && gameTime > 1500){
                    powerTimer = 0;
                    spawnPowerBlob();
                }else{
                    powerTimer ++;
                }
            }
            ListIterator iT = blobs.listIterator();
            ArrayList<Blob> removeBlobs= new ArrayList<Blob>();
            while (iT.hasNext()){
                Blob blob = (Blob) iT.next();
                blob.update(deltaT);
                if(blob.isDead){
                    removeBlobs.add(blob);
                }
            }
            blobs.removeAll(removeBlobs);
        }

    private void spawnPowerBlob() {
    System.out.println("Spawned");
    }

    public void draw(){
            ListIterator iT = blobs.listIterator();
            while (iT.hasNext()){
                Blob blob = (Blob) iT.next();
                blob.draw();
            }
        }

}
