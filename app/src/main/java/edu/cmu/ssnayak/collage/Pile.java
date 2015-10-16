package edu.cmu.ssnayak.collage;

import android.graphics.PointF;

/**
 * Created by snayak on 10/16/15.
 * <p/>
 * places all of its children at its own topWleft corner
 */
public class Pile extends ArtistBase {

    public Pile(float x, float y, float w, float h) {
        setPosition(x, y);
        setSize(w, h);
    }

    @Override
    public void doLayout() {
        //Pile is a specialized containter that lays out all of it's children at it's own top left corner
        //set all children's X, Y = 0
        for (Artist child : mChildren) {
            child.setX(0);
            child.setY(0);
            //call doLayout for each of it's children to traverse down the tree
            child.doLayout();
        }
    }

}
