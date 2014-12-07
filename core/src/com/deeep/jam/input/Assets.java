package com.deeep.jam.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 10:17 AM
 */
public class Assets {
    /**
     * instance for singleton
     */
    private static Assets assets;
    /**
     * Just a check to be sure that the assets aren't loaded multiple times
     */
    private static boolean loaded = false;
    /**
     * The atlases containing all the images
     */
    private TextureAtlas textureAtlas;
    /**
     * Logger instance
     */
//    private Logger logger = Logger.getInstance();
    /**
     * The texture region for the shape renderer
     */
    private Sprite blankSprite;
    /**
     * Standard font
     */
    private BitmapFont font;
    private BitmapFont.BitmapFontData bitmapFontData;
    private Pixmap pixmap;
    private Pixmap kappaPixmap;

    public Sound pointsGained;
    public Sound incorrect;
    public Sound power;
    public Sound menu;
    public Sound speed_down;
    public Sound speed_up;

    /**
     * Find a use for this, if there is any TODO
     */
    public Assets() {
    }

    /**
     * Simple singleton
     *
     * @return assets instance
     */
    public static Assets getAssets() {
        if (assets == null) {
            assets = new Assets();
        }
        return assets;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }


    /**
     * function to load everything. Nothing special. TODO add more resources here( sound music etc)
     */
    public void load() {
        if (!loaded) {
            pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.WHITE);
            pixmap.fillRectangle(0, 0, 64, 64);
            blankSprite = new Sprite(new Texture(pixmap));
            font = loadBitmapFont();
            bitmapFontData = new BitmapFont.BitmapFontData(Gdx.files.internal("font/font.fnt"), false);
            kappaPixmap = new Pixmap(Gdx.files.internal("kappa.png"));
            textureAtlas = new TextureAtlas(Gdx.files.internal("TextureAtlas.txt"));
//            logger.system(Assets.class, "All assets have been loaded");
            loaded = true;

            loadSounds();
        }
    }

    private void loadSounds() {
        //༼༼༼༼༼ຈຈຈຈຈل͜ل͜ل͜ل͜ل͜ຈຈຈຈຈ༽༽༽༽༽ﾉﾉﾉﾉﾉ
        pointsGained = Gdx.audio.newSound(Gdx.files.internal("sound/blub.mp3"));
        incorrect = Gdx.audio.newSound(Gdx.files.internal("sound/knack.mp3"));
        power = Gdx.audio.newSound(Gdx.files.internal("sound/blob.mp3"));
        menu = Gdx.audio.newSound(Gdx.files.internal("sound/menu.wav"));
        speed_down = Gdx.audio.newSound(Gdx.files.internal("sound/speed_down.mp3"));
        speed_up = Gdx.audio.newSound(Gdx.files.internal("sound/speed_up.mp3"));
    }

    public Sprite getBlankSprite() {
        return blankSprite;
    }

    /**
     * Dispose function. should ALWAYS be called. This will get rid of the texture atlas
     */
    public void dispose() {
        if (pixmap != null)
            pixmap.dispose();
        if (textureAtlas != null)
            textureAtlas.dispose();
//        logger.system(Assets.class, "All assets have been disposed");
    }

    /**
     * Returns an texture region based on the name given. Get images using this function!
     *
     * @param name see TextureAtlas.txt
     * @return the texture region connected to the name
     */
    public TextureRegion getRegion(String name) {

        TextureRegion textureRegion = textureAtlas.findRegion(name);
        if (textureRegion == null) {
//            logger.error(Assets.class, "Unkown texture requested: " + name);
        }
        return textureAtlas.findRegion(name);
    }

    /**
     * Loads the bitmap font as BitmapFont object
     *
     * @return null or the font
     */
    public BitmapFont loadBitmapFont() {
        Texture texture = new Texture(Gdx.files.internal("font/font.png"));

        BitmapFont font = new BitmapFont(Gdx.files.internal("font/font.fnt"), new TextureRegion(texture), false);
        if (font != null) return font;
//        Logger.getInstance().error(this.getClass(), "Couldn't find specified font!");
        return null;
    }

    /**
     * Returns the bitmap font as BitmapFont object
     *
     * @return null or the font
     */
    public BitmapFont getBitmapFont() {
        return font;
    }
    public BitmapFont.BitmapFontData getBitmapFontData() {
        return bitmapFontData;
    }

    public Pixmap getKappaPixmap() {
        return kappaPixmap;
    }
}
