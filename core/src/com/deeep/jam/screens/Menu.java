package com.deeep.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.jam.World;
import com.deeep.jam.input.Assets;

/**
 * Created by E on 12/7/2014.
 */
public class Menu {
    BitmapFont bitmapFont;
    public World world;
    public boolean show = true;

    MenuOption menuOption;

    public Menu(World world) {
        this.world = world;
        bitmapFont = Assets.getAssets().getBitmapFont();
        menuOption = new MenuOption("PLAY");
    }

    public void draw(SpriteBatch spriteBatch) {
        if (show)
            menuOption.draw(bitmapFont, spriteBatch);
    }

    class MenuOption {

        String text;
        Letter[] play;
        Letter[] about;
        Letter[] highScores;
        Letter[] quite;

        public MenuOption(String text) {
            play = setUp(play, "play", 0);
            about = setUp(about, "about", 6);
            quite = setUp(quite, "quit", 13);
            highScores = setUp(highScores, "scores", 19);

        }

        public Letter[] setUp(Letter[] toSetUp, String text, int index) {
            toSetUp = new Letter[text.length()];
            double startRotation = ((2 * Math.PI) / 27);
            double rotationPerLetter = (2 * Math.PI) / 27;
            for (int i = 0, l = text.length(); i < l; i++) {
                toSetUp[i] = new Letter(text.charAt(i), (float) (startRotation - rotationPerLetter * (i + index)));
                toSetUp[i].x = (float) Math.cos(startRotation - rotationPerLetter * (i + index)) * 50 + 256;
                toSetUp[i].y = (float) Math.sin(startRotation - rotationPerLetter * (i + index)) * 50 + 256;
                toSetUp[i].index = index;
            }
            return toSetUp;
        }

        public void draw(BitmapFont bitmapFont, SpriteBatch spriteBatch) {
            float rotation = (float) Math.atan2(512 - Gdx.input.getY() - 256, Gdx.input.getX() - 256);
            draw(spriteBatch, play, rotation);
            draw(spriteBatch, quite, rotation);
            draw(spriteBatch, highScores, rotation);
            draw(spriteBatch, about, rotation);
            boolean temp = true;
            for (Letter letter : play) {
                if (letter.redTimer > 0) {
                    temp = false;
                }
            }
            if (temp) {
                show = false;
                world.difficulty.spawn(world.globe, world.blobManager);
                return;
            }
        }

        public void draw(SpriteBatch spriteBatch, Letter[] toDraw, float mouseAngle) {
            for (int i = 0; i < toDraw.length; i++) {
                toDraw[i].x = (float) Math.cos(toDraw[i].ownRotation + mouseAngle) * 50 + 256;
                toDraw[i].y = (float) Math.sin(toDraw[i].ownRotation + mouseAngle) * 50 + 256;
                toDraw[i].draw(spriteBatch, mouseAngle);
            }
        }

        class Letter {
            public float x, y;
            public float ownRotation;
            Sprite sprite;
            public char character;
            public int index = 0;
            public float redTimer = 1;
            private Color color = new Color(1, 1, 1, 1);
            private boolean previousShown = false;

            public Letter(char letter, float ownRotation) {
                character = letter;
                this.ownRotation = ownRotation;
                BitmapFont.BitmapFontData bitmapFontData = Assets.getAssets().getBitmapFontData();
                int sX, sY, sW, sH;
                sX = bitmapFontData.getGlyph(letter).srcX;
                sY = bitmapFontData.getGlyph(letter).srcY;
                sW = bitmapFontData.getGlyph(letter).width;
                sH = bitmapFontData.getGlyph(letter).height;
                TextureRegion textureRegion = new TextureRegion(Assets.getAssets().getBitmapFont().getRegion().getTexture(), sX, sY, sW, sH);
                this.sprite = new Sprite(textureRegion);
                this.sprite.flip(true, true);
            }

            public void draw(SpriteBatch spriteBatch, float rotationDeviation) {
                float rotation = (float) Math.toDegrees(rotationDeviation + ownRotation);
                sprite.setRotation(rotation - 270);
                if (rotation < 0) {
                    rotation += 360;
                }
                rotation += 180;
                rotation %= 360;
                System.out.print(rotation);
                if (!(rotation > 25 && rotation < 155)) {
                    color.a = 1;
                    if (!previousShown) {
                        Assets.getAssets().menu.play();
                    }
                    if (rotation < 200 && rotation > 155) {
                        color.a = (rotation - 155) / 45;
                        //right
                    } else if (rotation > 340) {
                        color.a = 20 / 45 + 1 - ((rotation - 340) / 45);
                        //left
                    } else if (rotation < 25 && rotation > 0) {
                        color.a = (25f / 45f) - ((rotation) / (float) 45);
                        //left
                    }
                    System.out.print(" " + color.a);
                    System.out.println();
                    previousShown = true;
                    color.b = redTimer;
                    color.g = redTimer;
                    if (rotation > 225 && rotation < 315) {
                        if (redTimer > 0.1)
                            redTimer -= Gdx.graphics.getDeltaTime() * 0.5;
                        else {
                            redTimer = 0;
                        }
                    } else {
                        if (redTimer < 0.9)
                            redTimer += Gdx.graphics.getDeltaTime() * 4;
                        else
                            redTimer = 1;
                    }
                    //sprite.rotate90(false);
                    sprite.setColor(color);

                    sprite.setCenter(x, y);
                    sprite.setScale(0.6f);
                    sprite.draw(spriteBatch);
                } else {
                    redTimer = 1;
                    previousShown = false;
                }
            }
        }
    }
}
