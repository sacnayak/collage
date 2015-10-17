package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by snayak on 10/16/15.
 * applies additional clipping to all its children in the form of an 
 * oval which fits just inside its defined bounding box. 
 */
public class OvalClip extends ArtistBase {


    /**
     * Constructor to initialize OvalClip Artist object as per
     * Contract
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public OvalClip(float x, float y, float w, float h) {
        super();
        setPosition(new PointF(x, y));
        setSize(w, h);
    }

    /**
     * Overriding parent class default method for specialized
     * drawing of Oval Clipping Mask
     * @param onCanvas
     */
    @Override
    public void draw(Canvas onCanvas) {
        //TODO: Make compatible with earlier versions (<API 18)
        Path ovalPath = new Path();
        RectF bounds = new RectF(0, 0, getW(), getH());
        ovalPath.addOval(bounds, Path.Direction.CW);

        //create an oval clipping mask
        onCanvas.clipPath(ovalPath);

        //draw all children within the clipping mask
        for (Artist child : mChildren) {
            onCanvas.save();
            onCanvas.translate(child.getX(), child.getY());
            onCanvas.clipRect(0, 0, child.getW(), child.getH());
            child.draw(onCanvas);
            onCanvas.restore();
        }

    }


}
