package com.deeep.jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.jam.World;
import com.deeep.jam.input.Assets;

import java.util.Random;

/**
 * Created by E on 12/7/2014.
 */
public class Menu {
    public World world;
    public boolean show = true;
    public boolean showHighscores = false;
    BitmapFont bitmapFont;
    MenuOption menuOption;
    private float menuEffect = 0;
    private boolean doneEffect = false;
    private boolean doingEffect = false;

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
        Letter[] selected;
        float freezeMouse = 0;

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
            if (freezeMouse != 0) {
                rotation = freezeMouse;
            }
            draw(spriteBatch, play, rotation);
            draw(spriteBatch, quite, rotation);
            draw(spriteBatch, highScores, rotation);
            draw(spriteBatch, about, rotation);
            if (!doingEffect) {
                boolean temp = true;
                for (Letter letter : play) {
                    if (letter.redTimer > 0) {
                        temp = false;
                    }
                }
                if (temp) {
                    //show = false;
                    freezeMouse = rotation;
                    selected = play;
                    doingEffect = true;
                    world.difficulty.maxEnemiesAlive++;
                    Random random = new Random();
                    switch (random.nextInt(2) + 1){
                        case 1:
                            Assets.getAssets().start1.play();
                            break;
                        case 2:
                            Assets.getAssets().start2.play();
                            break;
                        case 3:
                            Assets.getAssets().start3.play();
                            break;
                    }
                    return;
                }
                temp = true;
                for (Letter letter : quite) {
                    if (letter.redTimer > 0) {
                        temp = false;
                    }
                }
                if (temp) {
                    freezeMouse = rotation;
                    selected = quite;
                    doingEffect = true;
                }
                temp = true;
                for (Letter letter : highScores) {
                    if (letter.redTimer > 0) {
                        temp = false;
                    }
                }
                if (temp) {
                    freezeMouse = rotation;
                    selected = highScores;
                    doingEffect = true;
                }
                temp = true;
                for (Letter letter : about) {
                    if (letter.redTimer > 0) {
                        temp = false;
                    }
                }
                if (temp) {
                    freezeMouse = rotation;
                    selected = about;
                    doingEffect = true;
                }

            }
            if (menuEffect >= 512) {
                if (selected == play)
                    show = true;
                else if (selected == about) {
                    Gdx.net.openURI("http://deeepgames.com/about");
                    world.instantiate();
                } else if (selected == quite) {
                    Gdx.app.exit();
                } else if (selected == highScores) {
                    showHighscores = true;
                }
            }
            if (doingEffect) {
                menuEffect += (Gdx.graphics.getDeltaTime() * 256);
                if (play != selected) {
                    moveToFinnish(play, rotation);
                }
                if (quite != selected) {
                    moveToFinnish(quite, rotation);
                }
                if (highScores != selected) {
                    moveToFinnish(highScores, rotation);
                }
                if (about != selected) {
                    moveToFinnish(about, rotation);
                }
                for (int i = 0; i < selected.length; i++) {
                    selected[i].offsetY = menuEffect;
                }

            }

        }

        private void moveToFinnish(Letter[] moving, float mouseRotation) {
            for (int i = 0; i < moving.length; i++) {
                moving[i].offsetY += (float) Math.sin(moving[i].ownRotation * mouseRotation) * 2;
                moving[i].offsetX += (float) Math.cos(moving[i].ownRotation * mouseRotation) * 2;
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
            public float offsetY;
            public float offsetX;
            public float ownRotation;
            public char character;
            public int index = 0;
            public float redTimer = 1;
            Sprite sprite;
            private float x, y;
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

                    sprite.setCenter(x, y + offsetY);
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
