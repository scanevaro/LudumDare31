package com.deeep.jam;

import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by Andreas on 12/6/2014.
 */
public class PixmapRotater {

    public static Pixmap getRotatedPixmap (Pixmap src, float angle){
        final int width = src.getWidth();
        final int height = src.getHeight();
        Pixmap rotated = new Pixmap(width, height, src.getFormat());

        final double radians = Math.toRadians(angle), cos = Math.cos(radians), sin = Math.sin(radians);


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final int
                        centerx = width/2, centery = height / 2,
                        m = x - centerx,
                        n = y - centery,
                        j = ((int) (m * cos + n * sin)) + centerx,
                        k = ((int) (n * cos - m * sin)) + centery;
                if (j >= 0 && j < width && k >= 0 && k < height){
                    rotated.drawPixel(x, y, src.getPixel(k, j));
                }
            }
        }
        return rotated;

    }

}
