package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/**
 * Created by snayak on 10/9/15.
 * a class that displays a text string in a given font, size, style, and 
 * color.  This Artist should have an intrinsic size which matches 
 * the bounding box of the text drawn within it
 */
public class TextArtist extends ArtistBase {

    //member variables required for TextArtist object
    protected String mText;
    protected Typeface mFace;
    protected float mTextSize;

    /**
     * constructor to intantiate a TextArtist object as per
     * contract
     * @param x
     * @param y
     * @param text
     * @param face
     * @param textSize
     */
    public TextArtist(float x, float y, String text, Typeface face, float textSize) {
        super();
        initialize(x, y, text, face, textSize);
    }

    /**
     * private utility method to initialize all member variables
     * @param x
     * @param y
     * @param text
     * @param face
     * @param textSize
     */
    public void initialize(float x, float y, String text, Typeface face, float textSize) {
        setPosition(new PointF(x, y));
        super.isIntrinsic = true;

        this.mText = text;
        this.mFace = face;
        this.mTextSize = textSize;
        //set the paint characteristics
        setPaint(face, textSize);

        setBounds(x, y);
    }

    /**
     * Set default paint style for TextArtist.
     * Can be modified by the client if required
     * @param face
     * @param textSize
     */
    public void setPaint(Typeface face, float textSize) {
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(face);
        textPaint.setTextSize(textSize);
        this.mPaint = textPaint;
    }

    /**
     * Define and set rectbounds for the Text
     * @param x
     * @param y
     */
    private void setBounds(float x, float y) {
        Rect bounds = new Rect();
        mPaint.getTextBounds(mText,0,mText.length(),bounds);
        int height = bounds.height();
        int width = bounds.width();
        this.mBoundingRect = new RectF(getX(),0,width,height);
    }

    /**
     * Overriding parent draw method in order to perform specialized
     * text drawing on canvas
     * @param onCanvas
     */
    @Override
    public void draw(Canvas onCanvas) {
        //draw text
        //the (0,0) of the text is at the bottom left of it's bounding box. So offset height in order
        //to print in view
        //FIXME notorious hack to offset text. Cleaner method?
        onCanvas.drawText(this.mText, 0, (0 + this.mBoundingRect.height()), mPaint);
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
    @Override
    public float getW() {
        return this.mBoundingRect.width();
    }

    /**
     * Overriding parent class implementation for
     * ArtistView to render right height during onMeasure
     * @return
     */
    @Override
    public float getH() {
        return this.mBoundingRect.height();
    }

}
