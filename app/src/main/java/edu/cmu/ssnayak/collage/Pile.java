package edu.cmu.ssnayak.collage;

import android.graphics.PointF;

/**
 * Created by snayak on 10/16/15.
 * <p/>
 * places all of its children at its own top left corner
 */
public class Pile extends ArtistBase {

    /**
     * Constructor for the Pile specialized layout Artist object
     * as per contract
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public Pile(float x, float y, float w, float h) {
        super();
        setPosition(x, y);
        setSize(w, h);
    }

    /**
     * Overriding doLayout of parent class implementation for specialied layout
     * in a pile
     */
    @Override
    public void doLayout() {
        //Pile is a specialized container that lays out all of it's children at it's own top left corner
        //set all children's X, Y = 0 by defauly
        for (Artist child : mChildren) {
            child.setX(0);
            child.setY(0);
            //call doLayout for each of it's children to traverse down the tree
            child.doLayout();
        }
    }

}
