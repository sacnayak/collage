package edu.cmu.ssnayak.collage;

/**
 * Created by snayak on 10/16/15.
 *
 * places its children in a single horizontal row with the children 
 * vertically centered.  If the children do not fit within the bounds 
 * of the row object they are clipped at the right edge. 
 * 
 */
public class Row extends ArtistBase {

    public Row(float x, float y, float w, float h) {
        setPosition(x, y);
        setSize(w, h);
    }

    public void doLayout() {
        //initialize x=0 for the coordinates of the first child
        float x = 0;
        for (Artist child : mChildren) {
            child.setX(x);
            //set the x values of each of the children
            x+= child.getW();
            //all of it's children are vertically centered
            child.setY((this.getH()/(float) 2) - child.getH()/(float) 2);

            //call doLayout for each of it's children to traverse down the tree
            child.doLayout();
        }
    }
}
