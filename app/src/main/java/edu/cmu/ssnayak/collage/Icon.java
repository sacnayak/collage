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

    //additional member variable to store bitmap image
    protected Bitmap image;

    /**
     * Constructor for Icon Artist Object as per contract
     * @param x
     * @param y
     * @param image
     */
    public Icon(float x, float y, Bitmap image) {
        super();
        initialize(x, y, image);
    }

    /**
     * private utility method to instantiate all member variables
     * @param x
     * @param y
     * @param image
     */
    public void initialize(float x, float y, Bitmap image) {
        //Icon has an intrinsic size and cannot be changed by the parent
        super.isIntrinsic = true;
        setPosition(new PointF(x, y));
        if(image!=null) {
            this.image = image;
        }
    }

    /**
     * Overriding parent class implementation for drawing
     * of an icon on canvas
     * @param onCanvas
     */
    @Override
    public void draw(Canvas onCanvas) {
        //draw current SimpleFrame object
        onCanvas.drawBitmap(this.image, 0, 0, null);
        //call child objects to paint themselves
        for (Artist child : mChildren) {
            onCanvas.save();
            onCanvas.translate(child.getX(), child.getY());
            onCanvas.clipRect(0, 0, child.getW(), child.getH());
            child.draw(onCanvas);
            onCanvas.restore();
        }
    }

    /**
     * Overriding parent class implementation for
     * ArtistView to render right width during onMeasure
     * @return
     */
    public float getW() {
        return this.image.getWidth();
    }

    /**
     * Overriding parent class implementation for
     * ArtistView to render right height during onMeasure
     * @return
     */
    public float getH() {
        return this.image.getHeight();
    }

    
}
