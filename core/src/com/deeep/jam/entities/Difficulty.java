package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.deeep.jam.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by E on 12/7/2014.
 */
public class Difficulty {
    public int score = 0;
    public int multiplier = 1;
    public int kills = 0;
    public int consecutive;
    public int killsToDifficult = 1;
    public int maxEnemiesAlive = 0;
    private int enemiesAlive = 0;
    private float minForce = 40;
    private float maxForce = 80;
    public int difficulty = 1;
    public ArrayList<Color> colors = new ArrayList<Color>();

    public Difficulty() {
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
    }

    public void playerHit(Globe globe, BlobManager blobManager) {
        multiplier = 1;
        consecutive = 0;
        enemiesAlive--;
    }

    public void kill(Globe globe, BlobManager blobManager) {
        consecutive++;
        if(consecutive%10==0)
            multiplier++;
        kills++;
        score += multiplier * 10;
        enemiesAlive--;
        calculateDifficulty(globe);
        killsToDifficult--;
    }

    private void calculateDifficulty(Globe globe) {
        if (killsToDifficult <= 0) {
            System.out.println("increase dif");
            killsToDifficult = 2;
            if(difficulty == 1){
                if(colors.size()>0) {
                    globe.getGlobeImage().addRegion(colors.get(0));
                    colors.remove(0);
                }
            }
            if (difficulty % 8 == 0) {
                System.out.println("increase color");
                if (colors.size() > 0) {
                    globe.getGlobeImage().addRegion(colors.get(0));
                    colors.remove(0);
                    maxEnemiesAlive = 1;
                }
            }
            if (difficulty % 4 == 0) {
                System.out.println("increase amount");
                maxEnemiesAlive++;
            }
            maxForce += 2;
            minForce += 1;
            difficulty++;
        }
    }

    public void spawn(Globe globe, BlobManager blobManager) {
        while (enemiesAlive < maxEnemiesAlive) {
            enemiesAlive++;
            blobManager.blobs.add(randomBlob(globe));
        }
    }

    private Blob randomBlob(Globe globe) {
        Random random = new Random();
        float speed = minForce + random.nextFloat() * (maxForce - minForce);
        float rotation = (float) (random.nextFloat() * 2 * Math.PI);
        Blob blob = new Blob(random.nextInt(30) + 362, rotation, speed, globe.getGlobeImage().getRandomColor());
        return blob;
    }
}
