package edu.cmu.ssnayak.collage;

/**
 * Created by snayak on 10/16/15.
 * places its children in a single vertical column with the children 
 * horizontally centered. If the children do not fit within the 
 * bounds of the row object they are clipped at the bottom edge. 
 */
public class Column extends ArtistBase {

    /**
     * Constructor for Column Artist object as per contract
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public Column(float x, float y, float w, float h) {
        super();
        setPosition(x, y);
        setSize(w, h);
    }

    /**
     * Overriding parent class doLayout to provide
     * a specialized Column layout for all children.
     *
     * This layout horizontally centers all its children
     */
    @Override
    public void doLayout() {
        //initialize y=0 for the coordinates of the first child
        float y = 0;
        for (Artist child : mChildren) {
            child.setY(y);
            //set the Y values of each of the children
            y+= child.getH();
            //all of it's children are vertically centered
            child.setX(((this.getW() / 2.0f)) - (child.getW() / 2.0f));

            //call doLayout for each of it's children to traverse down the tree
            child.doLayout();
        }
    }
}
