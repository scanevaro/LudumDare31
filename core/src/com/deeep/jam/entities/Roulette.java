package com.deeep.jam.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.World;
import com.deeep.jam.input.Assets;

import java.util.Random;

/**
 * Created by E on 12/6/2014.
 */
public class Roulette {
    Color color = new Color(0.8f, 0.8f, 0.8f, 0.5f);
    private float turnTimer = 0;
    private float increaseAmount = 100;
    private float prevIncreaseAmount = 40;
    private float increaseAmountTimer = 0;
    private float gotResult = 0;
    boolean given = true;
    Sprite[] sprites;
    int[] shown;
    private World world;
    private Globe globe;
    private boolean firstTime = true;

    public Roulette(World world, Globe globe) {
        this.world = world;
        this.globe = globe;
        shown = new int[5];
        sprites = new Sprite[10];
        sprites[0] = new Sprite(Assets.getAssets().getRegion("angel"));
        sprites[1] = new Sprite(Assets.getAssets().getRegion("armor"));
        sprites[2] = new Sprite(Assets.getAssets().getRegion("explosion"));
        sprites[3] = new Sprite(Assets.getAssets().getRegion("heal"));
        sprites[4] = new Sprite(Assets.getAssets().getRegion("multiplier"));
        sprites[5] = new Sprite(Assets.getAssets().getRegion("score"));
        sprites[6] = new Sprite(Assets.getAssets().getRegion("speeddown"));
        sprites[7] = new Sprite(Assets.getAssets().getRegion("shockwave"));
        sprites[8] = new Sprite(Assets.getAssets().getRegion("speedUp"));
        sprites[9] = new Sprite(Assets.getAssets().getRegion("freeze"));
        Random random= new Random();

        for (int i = 0; i < 10; i++) {
            sprites[i].setScale(0.5f);
        }for (int i = 1; i < 5; i++) {
            shown[i - 1] = shown[i];
        }
        int temp = random.nextInt(10);
        while (contains(shown, temp)) {
            temp = random.nextInt(10);
        }
        for(int i = 0; i<5; i++) {
            sprites[shown[i]].setScale(0.25f);
            sprites[shown[i]].setColor(color);
        }
        sprites[shown[2]].setScale(0.5f);
    }

    public void draw(SpriteBatch spriteBatch) {
        Random random = new Random();
        increaseAmountTimer += Gdx.graphics.getDeltaTime();
        increaseAmount = (float) (0.05f * (Math.pow(increaseAmountTimer, 6)) - 6f * increaseAmountTimer + 10);
        if (increaseAmount > prevIncreaseAmount) {

            increaseAmount = 0;
            gotResult += Gdx.graphics.getDeltaTime();
            if (gotResult > 2) {
                if (!given) {
                    actSession();
                    given = true;
                }
                color.r = 1f;
                color.b = 1f;
                color.a = 0.4f;
            }
        } else {
            color.r = 1f;
            color.b = 1f;
            color.g = 1;
            color.a = 1;
        }
        turnTimer += increaseAmount * 5 * Gdx.graphics.getDeltaTime();
        prevIncreaseAmount = increaseAmount;

        if (turnTimer >= 1) {

            turnTimer -= 1;
                for (int i = 1; i < 5; i++) {
                    shown[i - 1] = shown[i];
                }
                int temp = random.nextInt(10);
                while (contains(shown, temp)) {
                    temp = random.nextInt(10);
                }
                shown[4] = temp;
        }

        for (int i = 0; i < 5; i++) {
            color.r = 1f;
            color.b = 1f;
            color.g = 1;
            sprites[shown[i]].setColor(color);
            sprites[shown[i]].setScale(0.25f);
                if (i == 2) {
                    if (increaseAmount == 0) {
                        if (gotResult < 2) {
                            if (((int) (gotResult / 0.2f) % 2) == 1) {
                                if (shown[i] > 6) {
                                    color.r = 1;
                                    color.g = 0.5f;
                                    color.b = 0.5f;
                                } else {
                                    color.r = 0.5f;
                                    color.g = 1f;
                                    color.b = 0.5f;
                                }
                            }
                        }
                    }
                    sprites[shown[i]].setScale(0.5f);
                    sprites[shown[i]].setColor(color);
                }

        }
        if(world.difficulty.maxEnemiesAlive>0) {
            sprites[shown[0]].setCenter((float) (256 - sprites[0].getWidth() * 0.25 - sprites[0].getWidth() * 0.4), 15);
            sprites[shown[1]].setCenter((float) (256 - sprites[0].getWidth() * 0.4), 15);
            sprites[shown[2]].setCenter(256, 25);
            sprites[shown[3]].setCenter((float) (256 + sprites[0].getWidth() * 0.4), 15);
            sprites[shown[4]].setCenter((float) (256 + sprites[0].getWidth() * 0.25 + sprites[0].getWidth() * 0.4), 15);
            for (int i = 0; i < 5; i++) {
                sprites[shown[i]].draw(spriteBatch);
            }
        }
    }

    public void newSession() {
        Random random = new Random();
        given = false;
        firstTime = false;
        System.out.println(firstTime);
        gotResult = 0;
        increaseAmountTimer = 0;
        turnTimer = 0;
        increaseAmount = 50;
        prevIncreaseAmount = 100;
        for (int i = 0; i < 5; i++) {
            shown[i] = -1;
        }
        int temp = 0;
        for (int i = 0; i < 5; i++) {
            temp = random.nextInt(10);
            while (contains(shown, temp)) {
                temp = random.nextInt(10);
            }
            shown[i] = temp;
        }
    }

    private void actSession() {
        int soundRandomizer = new Random().nextInt(2) + 1;

        switch (shown[2]) {
            case 0:
                //angel
                globe.angelBlock += 3;
                switch (soundRandomizer) {
                    case 1:
                        Assets.getAssets().angel_power1.play();
                        break;
                    case 2:
                        Assets.getAssets().angel_power2.play();
                        break;
                    case 3:
                        Assets.getAssets().angel_power3.play();
                        break;
                }
                break;
            case 1:
                //armor
                globe.armor();
                switch (soundRandomizer) {
                    case 1:
                        Assets.getAssets().protection3.play();
                        break;
                    case 2:
                        Assets.getAssets().protection2.play();
                        break;
                    case 3:
                        Assets.getAssets().protection3.play();
                        break;
                }
                break;
            case 2:
                world.explosionTimer = 100;
                int size = world.blobManager.blobs.size();
                world.blobManager.blobs.clear();
                for (int i = 0; i < size; i++) {
                    world.difficulty.kill(globe, world.blobManager);
                }
                switch (soundRandomizer) {
                    case 1:
                        Assets.getAssets().hasta1.play();
                        break;
                    case 2:
                        Assets.getAssets().hasta2.play();
                        break;
                    case 3:
                        Assets.getAssets().hasta3.play();
                        break;
                }
                break;
            case 3:
                world.healTimer = 100;
                world.damageTimer = 0;
                switch (soundRandomizer) {
                    case 1:
                        Assets.getAssets().healpower1.play();
                        break;
                    case 2:
                        Assets.getAssets().healpower2.play();
                        break;
                    case 3:
                        Assets.getAssets().healpower3.play();
                        break;
                }
                break;
            case 4:
                world.difficulty.incrementMultiplier(5);
                switch (soundRandomizer) {
                    case 1:
                        Assets.getAssets().points1.play();
                        break;
                    case 2:
                        Assets.getAssets().points2.play();
                        break;
                    case 3:
                        Assets.getAssets().points3.play();
                        break;
                }
                break;
            case 5:
                world.difficulty.score *= 1.1;
                switch (soundRandomizer) {
                    case 1:
                        Assets.getAssets().points1.play();
                        break;
                    case 2:
                        Assets.getAssets().points2.play();
                        break;
                    case 3:
                        Assets.getAssets().points3.play();
                        break;
                }
                break;
            case 6:
                if (world.blobManager.generalSpeed > 0.5) {
                    world.blobManager.generalSpeed -= 0.5;
                    world.blobManager.speedDowns++;
                    switch (soundRandomizer) {
                        case 1:
                            Assets.getAssets().speed_slow1.play();
                            break;
                        case 2:
                            Assets.getAssets().speed_slow2.play();
                            break;
                        case 3:
                            Assets.getAssets().speed_slow3.play();
                            break;
                    }
                }
                break;
            case 7:
                world.shockwave();
                switch (soundRandomizer) {
                    case 1:
                        Assets.getAssets().blow1.play();
                        break;
                    case 2:
                        Assets.getAssets().blow2.play();
                        break;
                    case 3:
                        Assets.getAssets().blow3.play();
                        break;
                }
                break;
            case 8:
                world.blobManager.generalSpeed += 0.5f;
                world.blobManager.speedUps++;
                switch (soundRandomizer) {
                    case 1:
                        Assets.getAssets().speed_fast1.play();
                        break;
                    case 2:
                        Assets.getAssets().speed_fast2.play();
                        break;
                    case 3:
                        Assets.getAssets().speed_fast3.play();
                        break;
                }
                //speedUp
                break;
            case 9:
                world.frostTimer = 100;
                globe.freeze();
                Assets.getAssets().ice.play();
                break;
        }
    }

    private boolean contains(int[] list, int number) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == number)
                return true;
        }
        return false;
    }
}
