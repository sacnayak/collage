package edu.cmu.ssnayak.collage;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.NinePatchDrawable;

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
        RectF rectBounds = new RectF(0,0,getW(),getH());
        this.mNinePatches.draw(onCanvas, rectBounds);

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
