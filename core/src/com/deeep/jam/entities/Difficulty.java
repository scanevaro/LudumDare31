package com.deeep.jam.entities;

import com.badlogic.gdx.graphics.Color;
import com.deeep.jam.input.Assets;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by E on 12/7/2014.
 */
public class Difficulty {
    public int score = 0;
    private int multiplier = 1;
    public int kills = 0;
    public int consecutive;
    public int killsToDifficult = 1;
    public int maxEnemiesAlive = 0;
    public int enemiesAlive = 0;
    private float minForce = 40;
    private float maxForce = 80;
    public int difficulty = 1;
    public ArrayList<Color> colors = new ArrayList<Color>();
    private boolean multipliers[];

    public void incrementMultiplier(int increment){

        Random random = new Random();
        int choice = random.nextInt(2) + 1;
        if (multiplier == 5) {
            switch (choice) {
                case 1:
                    Assets.getAssets().easy1.play();
                    break;
                case 2:
                    Assets.getAssets().easy2.play();
                    break;
                case 3:
                    Assets.getAssets().easy3.play();
                    break;
            }
        } else if (multiplier == 10) {
            switch (choice) {
                case 1:
                    Assets.getAssets().keep_it_up1.play();
                    break;
                case 2:
                    Assets.getAssets().keep_it_up2.play();
                    break;
                case 3:
                    Assets.getAssets().keep_it_up3.play();
                    break;
            }
        } else if (multiplier == 15) {
            switch (choice) {
                case 1:
                    Assets.getAssets().well_done1.play();
                    break;
                case 2:
                    Assets.getAssets().well_done2.play();
                    break;
                case 3:
                    Assets.getAssets().well_done3.play();
                    break;
            }
        } else if (multiplier == 20) {
            switch (choice) {
                case 1:
                    Assets.getAssets().nice_going1.play();
                    break;
                case 2:
                    Assets.getAssets().nice_going2.play();
                    break;
                case 3:
                    Assets.getAssets().nice_going3.play();
                    break;
            }
        } else if (multiplier == 25) {
            switch (choice) {
                case 1:
                    Assets.getAssets().wow1.play();
                    break;
                case 2:
                    Assets.getAssets().wow2.play();
                    break;
                case 3:
                    Assets.getAssets().wow3.play();
                    break;
            }
        } else if (multiplier == 30) {
            switch (choice) {
                case 1:
                    Assets.getAssets().amazing1.play();
                    break;
                case 2:
                    Assets.getAssets().amazing2.play();
                    break;
                case 3:
                    Assets.getAssets().amazing3.play();
                    break;
            }
        } else if (multiplier == 40) {
            switch (choice) {
                case 1:
                    Assets.getAssets().incredible1.play();
                    break;
                case 2:
                    Assets.getAssets().incredible2.play();
                    break;
                case 3:
                    Assets.getAssets().incredible3.play();
                    break;
            }
        } else if (multiplier == 50) {
            switch (choice) {
                case 1:
                    Assets.getAssets().oh_my_god1.play();
                    break;
                case 2:
                    Assets.getAssets().oh_my_god2.play();
                    break;
                case 3:
                    Assets.getAssets().oh_my_god3.play();
                    break;
            }
        }
    }

    public Difficulty() {
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
    }

    public void playerHit(Globe globe, BlobManager blobManager) {
        if (multiplier >= 5) {
            Random random = new Random();
            switch (random.nextInt(2) + 1) {
                case 1:
                    Assets.getAssets().multiplier_loss1.play();
                    break;
                case 2:
                    Assets.getAssets().multiplier_loss2.play();
                    break;
                case 3:
                    Assets.getAssets().multiplier_loss3.play();
                    break;
            }
        }
        multiplier = 1;
        consecutive = 0;
        enemiesAlive--;
    }

    public void kill(Globe globe, BlobManager blobManager) {
        consecutive++;
        if (consecutive % 2 == 0) {
            incrementMultiplier(1);
        }
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
            if (difficulty == 1) {
                if (colors.size() > 0) {
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
