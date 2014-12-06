package com.deeep.jam;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.entities.Blob;
import com.deeep.jam.entities.Globe;
import com.deeep.jam.screens.AbstractGame;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by scanevaro on 05/12/2014.
 */
public class World {

    public static Globe globe;
    public static ArrayList<Blob> blobs;

    public World() {
        globe = new Globe();
        blobs = new ArrayList<Blob>();
    }

    public void update(float deltaT){
        globe.update(deltaT);
        ListIterator iT = blobs.listIterator();
        while(iT.hasNext()){
            Blob b = (Blob) iT.next();
            b.update(deltaT);
        }
    }

    public void draw(SpriteBatch spriteBatch){
        globe.draw(spriteBatch);
        ListIterator iT = blobs.listIterator();
        while(iT.hasNext()){
            Blob b = (Blob) iT.next();
            b.draw(spriteBatch);
        }
    }

}
