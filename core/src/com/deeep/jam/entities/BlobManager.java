package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Andreas on 12/6/2014.
 */
public class BlobManager {


        public ArrayList<Blob> blobs = new ArrayList<Blob>();

        public void update(float deltaT){
            ListIterator iT = blobs.listIterator();
            while (iT.hasNext()){
                Blob blob = (Blob) iT.next();
            }
        }

        public void draw(SpriteBatch spriteBatch){

        }

        public void remove(int id){

        }

        public void remove(Entity e){

        }


}
