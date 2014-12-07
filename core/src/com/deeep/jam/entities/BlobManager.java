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
    private double speedUpTimer = 0;
    public int speedUps = 0;
    private double speedDownTimer = 0;
    public int speedDowns = 0;
    public float generalSpeed = 1;
    public ArrayList<Blob> blobs = new ArrayList<Blob>();

    public BlobManager() {
    }

    public void update(float deltaT) {
        if (speedUps > 0) {
            speedUpTimer += deltaT;
            if (speedUpTimer >= 4) {
                speedUpTimer = 0;
                generalSpeed -= 0.5f;
                speedUps--;
            }
        }
        if (speedDowns > 0) {
            speedDownTimer += deltaT;
            if (speedDownTimer >= 4) {
                speedDownTimer = 0;
                generalSpeed += 0.5f;
                speedDowns--;
            }
        }

        ListIterator iT = blobs.listIterator();
        ArrayList<Blob> removeBlobs = new ArrayList<Blob>();
        while (iT.hasNext()) {
            Blob blob = (Blob) iT.next();
            blob.update(deltaT * generalSpeed);
            if (blob.isDead) {
                removeBlobs.add(blob);
            }
        }
        blobs.removeAll(removeBlobs);
    }

    public void draw() {
        ListIterator iT = blobs.listIterator();
        while (iT.hasNext()) {
            Blob blob = (Blob) iT.next();
            blob.draw();
        }
    }

}
