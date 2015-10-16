package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
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
    protected Rect mBounds;

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

        setBounds();
    }

    private void setBounds() {
        this.mBounds = new Rect(100,100,100,100);
    }

    public void draw(Canvas onCanvas) {
        //draw text
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTypeface(this.mFace);
        textPaint.setTextSize(this.mTextSize);
        onCanvas.drawText(this.mText, getX(), getY(), textPaint);
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
        return this.mBounds.width();
    }

    public float getH() {
        return this.mBounds.height();
    }
}
