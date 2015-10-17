package edu.cmu.ssnayak.collage;

import android.graphics.PointF;

/**
 * Created by snayak on 10/16/15.
 * Lays out its children within a golden rectangle. Does not work with
 * intrinsic artist classes
 *
 */
public class GoldenRectangle extends ArtistBase {

    public GoldenRectangle(float x, float y, float w, float h) {
        super();
        initialize(x, y, w, h);
    }

    public void initialize(float x, float y, float w, float h) {
        setPosition(new PointF(x, y));
        setSize(w, h);
    }

    @Override
    public void doLayout() {
        //if a horizontal rectangle ie width >= height
        float x = 0f, y = 0f;
        float w = getW(), h = getH();
        float otherW = getW(), otherH = getH();

        if(getW() > getH()) {
            w = getH();
            otherW-=w;
        } else {
            h = getW();
            otherH-=h;
        }

        if(mChildren!=null) {
            for (Artist child : mChildren) {
                //set all the children
                child.setX(x);
                child.setY(y);
                child.setW(w);
                child.setH(h);

                if(otherW < otherH) {
                    x += w;
                    w = otherW;
                    h = w;
                    otherH-=w;
                } else {
                    y += h;
                    h = otherH;
                    w = h;
                    otherW-=h;
                }

            }
        }
    }

}
