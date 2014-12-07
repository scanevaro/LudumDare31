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
    public Sound power, pling;
    public Sound menu, ice;
    public Sound start1, game_over1, new_highscore1, quit1, easy1, keep_it_up1, well_done1, nice_going1, wow1, amazing1, incredible1, oh_my_god1, multiplier_loss1, angel_power1, protection1, points1, hasta1, healpower1, blow1, speed_slow1, speed_fast1;
    public Sound start2, game_over2, new_highscore2, quit2, easy2, keep_it_up2, well_done2, nice_going2, wow2, amazing2, incredible2, oh_my_god2, multiplier_loss2, angel_power2, protection2, points2, hasta2, healpower2, blow2, speed_slow2, speed_fast2;
    public Sound start3, game_over3, new_highscore3, quit3, easy3, keep_it_up3, well_done3, nice_going3, wow3, amazing3, incredible3, oh_my_god3, multiplier_loss3, angel_power3, protection3, points3, hasta3, healpower3, blow3, speed_slow3, speed_fast3;


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
        Sound music = Gdx.audio.newSound(Gdx.files.internal("music.mp3"));
        pling = Gdx.audio.newSound(Gdx.files.internal("sound/pling.mp3"));
        ice = Gdx.audio.newSound(Gdx.files.internal("sound/ice.mp3"));
        music.loop(0.35f);
        /**
         * Ruwin files
         * start, game_over, new_highscore, quit, easy, keep_it_up, well_done, nice_going, wow, amazing, incredible, oh_my_god, multiplier_loss, angel_power, protection, points, hasta, healpower, blow, speed_slow, speed_fast;
         */
        start1 = Gdx.audio.newSound(Gdx.files.internal("sound/letsgo1.mp3"));
        game_over1 = Gdx.audio.newSound(Gdx.files.internal("sound/uhoh1.mp3"));
        new_highscore1 = Gdx.audio.newSound(Gdx.files.internal("sound/stunning1.mp3"));
        quit1 = Gdx.audio.newSound(Gdx.files.internal("sound/bye1.mp3"));
        easy1 = Gdx.audio.newSound(Gdx.files.internal("sound/easy1.mp3"));
        keep_it_up1 = Gdx.audio.newSound(Gdx.files.internal("sound/keepitup1.mp3"));
        well_done1 = Gdx.audio.newSound(Gdx.files.internal("sound/welldone1.mp3"));
        nice_going1 = Gdx.audio.newSound(Gdx.files.internal("sound/nicegoing1.mp3"));
        wow1 = Gdx.audio.newSound(Gdx.files.internal("sound/wow1.mp3"));
        amazing1 = Gdx.audio.newSound(Gdx.files.internal("sound/amazing1.mp3"));
        incredible1 = Gdx.audio.newSound(Gdx.files.internal("sound/incredible1.mp3"));
        oh_my_god1 = Gdx.audio.newSound(Gdx.files.internal("sound/ohmygod1.mp3"));
        multiplier_loss1 = Gdx.audio.newSound(Gdx.files.internal("sound/multiplier1.mp3"));
        angel_power1 = Gdx.audio.newSound(Gdx.files.internal("sound/letthemcome1.mp3"));
        protection1 = Gdx.audio.newSound(Gdx.files.internal("sound/protection1.mp3"));
        points1 = Gdx.audio.newSound(Gdx.files.internal("sound/morepoints1.mp3"));
        hasta1 = Gdx.audio.newSound(Gdx.files.internal("sound/hastalavista1.mp3"));
        healpower1 = Gdx.audio.newSound(Gdx.files.internal("sound/heal1.mp3"));
        blow1 = Gdx.audio.newSound(Gdx.files.internal("sound/blow1.mp3"));
        speed_slow1 = Gdx.audio.newSound(Gdx.files.internal("sound/notsoquickly1.mp3"));
        speed_fast1 = Gdx.audio.newSound(Gdx.files.internal("sound/speedup1.mp3"));
        start2 = Gdx.audio.newSound(Gdx.files.internal("sound/letsgo2.mp3"));
        game_over2 = Gdx.audio.newSound(Gdx.files.internal("sound/uhoh2.mp3"));
        new_highscore2 = Gdx.audio.newSound(Gdx.files.internal("sound/stunning2.mp3"));
        quit2 = Gdx.audio.newSound(Gdx.files.internal("sound/bye2.mp3"));
        easy2 = Gdx.audio.newSound(Gdx.files.internal("sound/easy2.mp3"));
        keep_it_up2 = Gdx.audio.newSound(Gdx.files.internal("sound/keepitup2.mp3"));
        well_done2 = Gdx.audio.newSound(Gdx.files.internal("sound/welldone2.mp3"));
        nice_going2 = Gdx.audio.newSound(Gdx.files.internal("sound/nicegoing2.mp3"));
        wow2 = Gdx.audio.newSound(Gdx.files.internal("sound/wow2.mp3"));
        amazing2 = Gdx.audio.newSound(Gdx.files.internal("sound/amazing2.mp3"));
        incredible2 = Gdx.audio.newSound(Gdx.files.internal("sound/incredible2.mp3"));
        oh_my_god2 = Gdx.audio.newSound(Gdx.files.internal("sound/ohmygod2.mp3"));
        multiplier_loss2 = Gdx.audio.newSound(Gdx.files.internal("sound/multiplier2.mp3"));
        angel_power2 = Gdx.audio.newSound(Gdx.files.internal("sound/letthemcome2.mp3"));
        protection2 = Gdx.audio.newSound(Gdx.files.internal("sound/protection2.mp3"));
        points2 = Gdx.audio.newSound(Gdx.files.internal("sound/morepoints2.mp3"));
        hasta2 = Gdx.audio.newSound(Gdx.files.internal("sound/hastalavista2.mp3"));
        healpower2 = Gdx.audio.newSound(Gdx.files.internal("sound/heal2.mp3"));
        blow2 = Gdx.audio.newSound(Gdx.files.internal("sound/blow2.mp3"));
        speed_slow2 = Gdx.audio.newSound(Gdx.files.internal("sound/notsoquickly2.mp3"));
        speed_fast2 = Gdx.audio.newSound(Gdx.files.internal("sound/speedup2.mp3"));
        start3 = Gdx.audio.newSound(Gdx.files.internal("sound/letsgo3.mp3"));
        game_over3 = Gdx.audio.newSound(Gdx.files.internal("sound/uhoh3.mp3"));
        new_highscore3 = Gdx.audio.newSound(Gdx.files.internal("sound/stunning3.mp3"));
        quit3 = Gdx.audio.newSound(Gdx.files.internal("sound/bye3.mp3"));
        easy3 = Gdx.audio.newSound(Gdx.files.internal("sound/easy3.mp3"));
        keep_it_up3 = Gdx.audio.newSound(Gdx.files.internal("sound/keepitup3.mp3"));
        well_done3 = Gdx.audio.newSound(Gdx.files.internal("sound/welldone3.mp3"));
        nice_going3 = Gdx.audio.newSound(Gdx.files.internal("sound/nicegoing3.mp3"));
        wow3 = Gdx.audio.newSound(Gdx.files.internal("sound/wow3.mp3"));
        amazing3 = Gdx.audio.newSound(Gdx.files.internal("sound/amazing3.mp3"));
        incredible3 = Gdx.audio.newSound(Gdx.files.internal("sound/incredible3.mp3"));
        oh_my_god3 = Gdx.audio.newSound(Gdx.files.internal("sound/ohmygod3.mp3"));
        multiplier_loss3 = Gdx.audio.newSound(Gdx.files.internal("sound/multiplier3.mp3"));
        angel_power3 = Gdx.audio.newSound(Gdx.files.internal("sound/letthemcome3.mp3"));
        protection3 = Gdx.audio.newSound(Gdx.files.internal("sound/protection3.mp3"));
        points3 = Gdx.audio.newSound(Gdx.files.internal("sound/morepoints3.mp3"));
        hasta3 = Gdx.audio.newSound(Gdx.files.internal("sound/hastalavista3.mp3"));
        healpower3 = Gdx.audio.newSound(Gdx.files.internal("sound/heal3.mp3"));
        blow3 = Gdx.audio.newSound(Gdx.files.internal("sound/blow3.mp3"));
        speed_slow3 = Gdx.audio.newSound(Gdx.files.internal("sound/notsoquickly3.mp3"));
        speed_fast3 = Gdx.audio.newSound(Gdx.files.internal("sound/speedup3.mp3"));
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
