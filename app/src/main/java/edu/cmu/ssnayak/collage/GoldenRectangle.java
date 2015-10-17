package edu.cmu.ssnayak.collage;

import android.graphics.PointF;

/**
 * Created by snayak on 10/16/15.
 * Interesting/Cool Artist subclass attempt.
 * The GoldenRectangle lays out its children within a golden rectangle. The class mods
 * the dimensions of the children and does not work with Artist objects with intrinsic sizes
 *
 * Intantiate the Golden Rectangle sides with Golden Ratio pair for best view
 * (1618, 1000), (809, 500), (404.5, 250) and so on.. in either direction.
 *
 * For more information on Golden Rectangle @see https://en.wikipedia.org/wiki/Golden_rectangle
 */
public class GoldenRectangle extends ArtistBase {

    /**
     * Constructor for the GoldenRectangle specialized Layout Implementations
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public GoldenRectangle(float x, float y, float w, float h) {
        super();
        initialize(x, y, w, h);
    }


    /**
     * Private utility method for constructing class
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void initialize(float x, float y, float w, float h) {
        setPosition(new PointF(x, y));
        setSize(w, h);
    }

    /**
     * Overriding parent class doLayout to provide
     * a specialized layout for all children in the form
     * of a Golden Rectangle.
     */
    @Override
    public void doLayout() {
        //this method has the logic for laying out Artist objects in a
        //golden rectangle - layou
        float x = 0f, y = 0f;
        //width and height of the object currently being layed out (the square in the golden rectangle)
        float w = getW(), h = getH();
        //width and height of the object that will be layed out next (the rectangle after diving the parent)
        float otherW = getW(), otherH = getH();

        //logic based on if the rectangle initialized is horizontal or vertical
        if(getW() > getH()) {
            w = getH();
            otherW-=w;
        } else {
            h = getW();
            otherH-=h;
        }

        //logic
        //step 1 - determine shorter side in rectangle.
        //step 2 - layout square of shorter side length.
        //step 3 - calculate the x, y, width and height of the other rectangle created
        //       as a result of making the square
        //step 4 - repeat the same for the other rectangle thus created
        if(mChildren!=null) {
            for (Artist child : mChildren) {
                //set all the children and lay them out
                child.setX(x);
                child.setY(y);
                child.setW(w);
                child.setH(h);

                //calculation of the parameters for the next child laying out
                //phase. Offset the x, y accordingly.
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
