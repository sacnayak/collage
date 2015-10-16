package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.PointF;

/**
 * Created by snayak on 10/9/15.
 * a class that displays a nine1patch 'image'(see
 * android.graphics.drawable.NinePatchDrawable). 
 */
public class NinePartImage extends ArtistBase {


    protected NinePatch mNinePatches;


    public NinePartImage(float x, float y, float w, float h, NinePatch patches) {
        setPosition(new PointF(x, y));
        setSize(w, h);
        this.mNinePatches = patches;
    }



    public void draw(Canvas onCanvas) {
        //draw current NinePartImage object
        

        //call child objects to paint themselves
        for (Artist child : mChildren) {
            onCanvas.save();
            onCanvas.translate(child.getX(), child.getY());
            onCanvas.clipRect(0, 0, child.getW(), child.getH());
            child.draw(onCanvas);
            onCanvas.restore();
        }
    }
}
