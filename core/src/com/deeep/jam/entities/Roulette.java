package com.deeep.jam.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.jam.input.Assets;

import java.util.Collections;
import java.util.Random;

/**
 * Created by E on 12/6/2014.
 */
public class Roulette {
    private float turnTimer = 0;
    private float increaseAmount = 50;
    private float increaseAmountTimer = 0;
    Sprite[] sprites;
    int[] shown;

    public Roulette() {
        shown = new int[5];
        sprites = new Sprite[10];
        sprites[0] = new Sprite(Assets.getAssets().getRegion("angel"));
        sprites[1] = new Sprite(Assets.getAssets().getRegion("armor"));
        sprites[2] = new Sprite(Assets.getAssets().getRegion("explosion"));
        sprites[3] = new Sprite(Assets.getAssets().getRegion("freeze"));
        sprites[4] = new Sprite(Assets.getAssets().getRegion("heal"));
        sprites[5] = new Sprite(Assets.getAssets().getRegion("multiplier"));
        sprites[6] = new Sprite(Assets.getAssets().getRegion("score"));
        sprites[7] = new Sprite(Assets.getAssets().getRegion("shockwave"));
        sprites[8] = new Sprite(Assets.getAssets().getRegion("speedUp"));
        sprites[9] = new Sprite(Assets.getAssets().getRegion("speeddown"));
        for (int i = 0; i < 10; i++) {
            sprites[i].setScale(0.5f);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        increaseAmountTimer += Gdx.graphics.getDeltaTime();
        increaseAmount = -2 * (increaseAmountTimer * increaseAmountTimer) + 40;
        turnTimer += increaseAmount * Gdx.graphics.getDeltaTime();
        System.out.println("increaseAmount: " + increaseAmount + " " + turnTimer);
        if (turnTimer >= 1) {
            Random random = new Random();
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
            increaseAmountTimer = 0;
            turnTimer = 0;
            increaseAmount = 0;
            Random random = new Random();
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
        int position = 0;
        sprites[shown[0]].setPosition(0, 0);
        sprites[shown[1]].setPosition((float) (sprites[0].getWidth() * 0.5), 0);
        sprites[shown[2]].setPosition((float) (sprites[0].getWidth() * 0.5 + sprites[0].getWidth() * 0.75), 0);
        sprites[shown[3]].setPosition((float) (2 * sprites[0].getWidth() * 0.5 + sprites[0].getWidth()), 0);
        sprites[shown[4]].setPosition((float) (3 * sprites[0].getWidth() * 0.5 + sprites[0].getWidth()), 0);
        for (int i = 0; i < 5; i++) {
            sprites[shown[i]].setScale(0.5f);
            if (i == 2)
                sprites[shown[i]].setScale(1);
            sprites[shown[i]].draw(spriteBatch);
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
