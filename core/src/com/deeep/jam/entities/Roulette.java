package com.deeep.jam.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.input.Assets;

import java.util.Collections;
import java.util.Random;

/**
 * Created by E on 12/6/2014.
 */
public class Roulette {
    Color color = new Color(0.8f, 0.8f, 0.8f, 0.5f);
    Color theOne = new Color();
    private float turnTimer = 0;
    private float increaseAmount = 50;
    private float prevIncreaseAmount = 100;
    private float increaseAmountTimer = 0;
    private float gotResult = 0;
    private float flashResult = 0;
    Sprite[] sprites;
    int[] shown;

    public Roulette() {
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
        for (int i = 0; i < 10; i++) {
            sprites[i].setScale(0.5f);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        Random random = new Random();
        increaseAmountTimer += Gdx.graphics.getDeltaTime();
        increaseAmount = (float) (0.05f * (Math.pow(increaseAmountTimer, 6)) - 6f * increaseAmountTimer + 10);
        if (increaseAmount > prevIncreaseAmount) {
            //Stijgende curve
            increaseAmount = 0;
            gotResult += Gdx.graphics.getDeltaTime();
            if (gotResult > 2) {
                color.r = 1f;
                color.b = 1f;
                color.a = 0.4f;
                theOne.r = 1f;
                theOne.b = 1f;
                theOne.a = 0.4f;
            }
        } else {
            color.r = 1f;
            color.b = 1f;
            color.g = 1;
            color.a = 1;
            theOne.r = 1f;
            theOne.b = 1f;
            theOne.g = 1;
            theOne.a = 1;
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
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            newSession();
        }
        int position = 0;


        for (int i = 0; i < 5; i++) {
            if (increaseAmount == 0) {
                if (gotResult < 2) {
                    if (gotResult < 0.4) {
                        if (shown[i] > 7) {
                            //bad pickup
                            theOne.r = 1f;
                            theOne.b = 0.5f;
                            theOne.g = 0.5f;
                        } else {
                            theOne.r = 0.5f;
                            theOne.b = 0.5f;
                            theOne.g = 1f;
                        }
                    } else if (gotResult < 0.8) {
                        theOne.r = 0.9f;
                        theOne.b = 0.9f;
                        theOne.g = 0.9f;
                    } else if (gotResult < 1.2) {
                        if (shown[i] > 7) {
                            //bad pickup
                            theOne.r = 1f;
                            theOne.b = 0.5f;
                            theOne.g = 0.5f;
                        } else {
                            theOne.r = 0.5f;
                            theOne.b = 0.5f;
                            theOne.g = 1f;
                        }
                    } else if (gotResult < 1.6) {
                        theOne.r = 0.9f;
                        theOne.b = 0.9f;
                        theOne.g = 0.9f;
                    } else {
                        if (shown[i] > 7) {
                            //bad pickup
                            theOne.r = 1f;
                            theOne.b = 0.5f;
                            theOne.g = 0.5f;
                        } else {
                            theOne.r = 0.5f;
                            theOne.b = 0.5f;
                            theOne.g = 1f;
                        }
                    }
                }
            }
            sprites[shown[i]].setColor(color);
            sprites[shown[i]].setScale(0.25f);
            if (i == 2) {
                sprites[shown[i]].setScale(0.5f);
                sprites[shown[i]].setColor(theOne);
            }

        }
        sprites[shown[0]].setCenter((float) (256 - sprites[0].getWidth() * 0.25 - sprites[0].getWidth() * 0.4), 15);
        sprites[shown[1]].setCenter((float) (256 - sprites[0].getWidth() * 0.4), 15);
        sprites[shown[2]].setCenter(256, 25);
        sprites[shown[3]].setCenter((float) (256 + sprites[0].getWidth() * 0.4), 15);
        sprites[shown[4]].setCenter((float) (256 + sprites[0].getWidth() * 0.25 + sprites[0].getWidth() * 0.4), 15);
        for (int i = 0; i < 5; i++) {
            sprites[shown[i]].draw(spriteBatch);
        }
    }

    public void newSession() {
        Random random = new Random();
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

    private boolean contains(int[] list, int number) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == number)
                return true;
        }
        return false;
    }
}
