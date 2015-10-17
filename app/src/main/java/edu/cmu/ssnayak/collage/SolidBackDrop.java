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

    /**
     * Constructor for a solid rectangular artist object
     * as per contract
     * @param x
     * @param y
     * @param w
     * @param h
     * @param color
     */
    public SolidBackDrop(float x, float y, float w, float h, int color) {
        super();
        initialize(x, y, w, h, color);
    }

    /**
     * private utility method to initialize class member
     * variables
     * @param x
     * @param y
     * @param w
     * @param h
     * @param color
     */
    private void initialize(float x, float y, float w, float h, int color) {
        setPosition(new PointF(x, y));
        setSize(w, h);
        this.mColor = color;
    }

    /**
     * Overriding the parent draw implementation to do specialized
     * drawing for a rectangular solid block
     * @param onCanvas
     */
    @Override
    public void draw(Canvas onCanvas) {
        //draw current SolidBackDrop object
        Paint rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setColor(this.mColor);
        onCanvas.drawRect(0, 0, getW(), getH(), rectPaint);
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
