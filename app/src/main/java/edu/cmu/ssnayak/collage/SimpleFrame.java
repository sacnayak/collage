package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by snayak on 10/9/15.
 *
 * a class that draws a framed rectangle (using  a single pixel wide 
 * black line) at its edges.
 */
public class SimpleFrame extends ArtistBase {

    /**
     * Constructor for the SimpleFrame artist drawing object
     * as per contract
     * @param x
     * @param y
     * @param w - width to be defined one pixel more than required
     *          by client
     * @param h - height to be defined one pixel more than required
     *          by client
     */
    public SimpleFrame(float x, float y, float w, float h) {
        super();
        initialize(x, y, w, h);
    }

    /**
     * Private utility method to intantiate class variables
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void initialize(float x, float y, float w, float h) {
        setPosition(new PointF(x, y));
        setSize(w, h);
    }

    /**
     * Overriding parent draw method to perform specialized drawing
     * of a simple hairline rectangular frame
     * @param onCanvas
     */
    @Override
    public void draw(Canvas onCanvas) {
        //draw current SimpleFrame object
        Paint rectPaint = new Paint();
        //passing in zero for hairline width (1px) of the color black
        rectPaint.setStrokeWidth(1);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(Color.BLACK);
        //Subtract one since drawRect is exclusive of the last pixel (right and bottom sides)
        //FIXME cleaner way to include drawRect corners without clipping?
        onCanvas.drawRect(0, 0, getW()-1, getH()-1, rectPaint);
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
