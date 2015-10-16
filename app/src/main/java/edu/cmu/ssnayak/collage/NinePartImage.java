package edu.cmu.ssnayak.collage;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.PointF;
import android.graphics.Rect;
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
        //FIXME - how to fetch resources
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(Resources.getSystem(), this.mNinePatches);
        Rect bounds = new Rect();
        bounds.set((int) getX(), (int) getY(),(int) (getX()+getW()),(int) (getY()+getH()));
        ninePatchDrawable.setBounds(bounds);
        ninePatchDrawable.draw(onCanvas);

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
