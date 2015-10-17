package edu.cmu.ssnayak.collage;


import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.PointF;
import android.graphics.RectF;


/**
 * Created by snayak on 10/9/15.
 * a class that displays a nine1patch 'image'(see
 * android.graphics.drawable.NinePatchDrawable). 
 */
public class NinePartImage extends ArtistBase {

    //additional member variable to store the NinePatch object
    protected NinePatch mNinePatches;


    /**
     * Constructor to initialize the NinePartImage Artist
     * Object
     * @param x
     * @param y
     * @param w
     * @param h
     * @param patches
     */
    public NinePartImage(float x, float y, float w, float h, NinePatch patches) {
        super();
        setPosition(new PointF(x, y));
        setSize(w, h);
        this.mNinePatches = patches;
    }


    /**
     * Overriding parent class implementation for specialized drawing
     * NinePartImage
     *
     * @param onCanvas
     */
    @Override
    public void draw(Canvas onCanvas) {
        //draw current NinePartImage object with bounds specified
        RectF rectBounds = new RectF(0,0,getW(),getH());
        this.mNinePatches.draw(onCanvas, rectBounds);

        //call child objects to paint themselves on top of parent
        for (Artist child : mChildren) {
            onCanvas.save();
            onCanvas.translate(child.getX(), child.getY());
            onCanvas.clipRect(0, 0, child.getW(), child.getH());
            child.draw(onCanvas);
            onCanvas.restore();
        }
    }
}
