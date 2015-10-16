package edu.cmu.ssnayak.collage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

/**
 * Created by snayak on 10/9/15.
 * a class displaying a BitMap image.  This Artist should have an 
 * intrinsic size which matches the size of the BitMap. 
 *
 */
public class Icon extends ArtistBase {

    protected Bitmap image;

    public Icon(float x, float y, Bitmap image) {
        super();
        initialize(x, y, image);
    }

    public void initialize(float x, float y, Bitmap image) {
        //Icon has an intrinsic size
        super.isIntrinsic = true;
        setPosition(new PointF(x, y));
        if(image!=null) {
            this.image = image;
        }
    }

    public void draw(Canvas onCanvas) {
        //draw current SimpleFrame object
        onCanvas.drawBitmap(this.image, getX()-10, getY()-10, null);
        //call child objects to paint themselves
        for (Artist child : mChildren) {
            onCanvas.save();
            onCanvas.translate(child.getX(), child.getY());
            onCanvas.clipRect(0, 0, child.getW(), child.getH());
            child.draw(onCanvas);
            onCanvas.restore();
        }
    }

    public float getW() {
        return this.image.getWidth();
    }

    public float getH() {
        return this.image.getHeight();
    }

    
}
