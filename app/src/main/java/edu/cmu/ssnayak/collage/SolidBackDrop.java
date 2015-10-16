package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by snayak on 10/9/15.
 * a class that provides a solid color background covering its entire 
 * defined area.
 */
public class SolidBackDrop extends ArtistBase {

    protected int mColor;

    public SolidBackDrop(float x, float y, float w, float h, int color) {
        super();
        initialize(x, y, w, h, color);
    }

    public void initialize(float x, float y, float w, float h, int color) {
        setPosition(new PointF(x, y));
        setSize(w, h);
        this.mColor = color;
    }

    public void draw(Canvas onCanvas) {
        //draw current SolidBackDrop object
        Paint rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.FILL);
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
