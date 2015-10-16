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

    protected String mText;
    protected Typeface mFace;
    protected float mTextSize;

    public TextArtist(float x, float y, String text, Typeface face, float textSize) {
        super();
        initialize(x, y, text, face, textSize);
    }

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

    private void setPaint(Typeface face, float textSize) {
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(face);
        textPaint.setTextSize(textSize);
        this.mPaint = textPaint;
    }

    private void setBounds(float x, float y) {
        Rect bounds = new Rect();
        mPaint.getTextBounds(mText,0,mText.length(),bounds);
        int height = bounds.height();
        int width = bounds.width();
        this.mBoundingRect = new RectF(getX(),0,width,height);
    }

    public void draw(Canvas onCanvas) {
        //draw text
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

    @Override
    public float getW() {
        return this.mBoundingRect.width();
    }

    public float getH() {
        return this.mBoundingRect.height();
    }

}
