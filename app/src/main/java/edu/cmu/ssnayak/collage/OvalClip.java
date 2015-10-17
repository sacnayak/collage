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


    public OvalClip(float x, float y, float w, float h) {
        setPosition(new PointF(x, y));
        setSize(w, h);
        //assuming position and size are defined correctly
    }

    public void draw(Canvas onCanvas) {
        Path ovalPath = new Path();
        RectF bounds = new RectF(0, 0, getW(), getH());
        ovalPath.addOval(bounds, Path.Direction.CW);

        //create an oval clipping mask
        onCanvas.clipPath(ovalPath);

        for (Artist child : mChildren) {
            onCanvas.save();
            onCanvas.translate(child.getX(), child.getY());
            onCanvas.clipRect(0, 0, child.getW(), child.getH());
            child.draw(onCanvas);
            onCanvas.restore();
        }

    }


}
