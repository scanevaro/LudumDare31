package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by Andreas on 12/6/2014.
 */

public class BlobManager {


        public ArrayList<Blob> blobs = new ArrayList<Blob>();

        public BlobManager(){
            for(int i = 0; i < 1000; i++){
                blobs.add(new Blob(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
            }
        }

        public void update(float deltaT){
            ListIterator iT = blobs.listIterator();
            while (iT.hasNext()){
                Blob blob = (Blob) iT.next();
                blob.update(deltaT);
            }
        }

        public void draw(){
            ListIterator iT = blobs.listIterator();
            while (iT.hasNext()){
                Blob blob = (Blob) iT.next();
                blob.draw();
            }
        }

}
