package edu.cmu.ssnayak.collage;

/**
 * Created by snayak on 10/16/15.
 * places its children in a single vertical column with the children 
 * horizontally centered. If the children do not fit within the 
 * bounds of the row object they are clipped at the bottom edge. 
 */
public class Column extends ArtistBase {

    public Column(float x, float y, float w, float h) {
        setPosition(x, y);
        setSize(w, h);
    }


    public void doLayout() {
        //initialize y=0 for the coordinates of the first child
        float y = 0;
        for (Artist child : mChildren) {
            child.setY(y);
            //set the Y values of each of the children
            y+= child.getH();
            //all of it's children are vertically centered
            child.setX((this.getW()/(float) 2) - child.getW()/(float) 2);

            //call doLayout for each of it's children to traverse down the tree
            child.doLayout();
        }
    }
}
