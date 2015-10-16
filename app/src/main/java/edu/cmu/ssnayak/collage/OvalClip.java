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
        setBoundingRect(getX(), getY(), getX() + getW(), getY() + getH());
    }

    private void setBoundingRect(float x, float y, float w, float h) {
        this.mBoundingRect = new RectF(x, y, x + w, y + h);
    }


    public void draw(Canvas onCanvas) {
        for (Artist child : mChildren) {
            onCanvas.save();
            onCanvas.translate(child.getX(), child.getY());
            onCanvas.clipRect(0, 0, child.getW(), child.getH());
            child.draw(onCanvas);
            onCanvas.restore();
        }
        //creata an oval clipping mask on top of the child drawings
        Path ovalPath = new Path();
        ovalPath.addOval(this.mBoundingRect, Path.Direction.CW);
        onCanvas.clipPath(ovalPath);
    }


}
