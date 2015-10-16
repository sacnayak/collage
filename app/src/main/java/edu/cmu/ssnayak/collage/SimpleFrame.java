package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by snayak on 10/9/15.
 *
 * a class that draws a framed rectangle (using  a single pixel wide 
 * black line) at its edges.
 */
public class SimpleFrame extends ArtistBase {


    public SimpleFrame(float x, float y, float w, float h) {
        super();
        initialize(x, y, w, h);
    }

    public void initialize(float x, float y, float w, float h) {
        setPosition(new PointF(x, y));
        setSize(w,h);
    }

    public void draw(Canvas onCanvas) {
        //draw current SimpleFrame object
        Paint rectPaint = new Paint();
        //passing in zero for hairline width (1px) of the color black
        rectPaint.setStrokeWidth(1);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setColor(Color.BLACK);
        onCanvas.drawRect(getX(), getY(), (getX()+getW()), (getY()+getH()), rectPaint);
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
