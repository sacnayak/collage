package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by snayak on 10/16/15.
 *
 * places its children so that their centers (not top left corners) lie
 * positioned at equal angles around a circular perimeter of a given
 * size.  So for example, if there were five child objects they would
 * be placed every 360/5 = 72°.  Or if there were four child objects,
 * their centers would be positioned 90° apart, i.e., at the four
 * compass points.  Note that this layout ignores any overlap that
 * might occur between children, simply calculating their locations
 * and drawing them there in the order found in the child list.
 *
 */
public class Circle extends ArtistBase{

    public static float CIRCLE_DEGREES = 360;

    protected float mlayoutRadius;
    protected PointF mCenter;

    public Circle(float x, float y, float w, float h, float layoutCenterX, float layoutCenterY, float layoutRadius) {
        setPosition(x, y);
        setSize(w, h);
        initialize(layoutCenterX, layoutCenterY, layoutRadius);
    }

    private void initialize(float layoutCenterX, float layoutCenterY, float layoutRadius) {
        setLayoutRadius(layoutRadius);
        setLayoutCenter(layoutCenterX, layoutCenterY);
        setBoundingRect(getX(), getY(), getX()+getW(), getY()+getH());
    }

    @Override
    public void doLayout() {
        //grab the number of children for the circle
        int numChildren = 0;
        if(mChildren != null) {
            numChildren = mChildren.size();
        } else {
            return;
        }
        if(numChildren == 0) return;

        //Calculate the degrees of separation b/w each child
        float degreeOfSeparation = CIRCLE_DEGREES/(float) numChildren;
        float translatedCenterX = this.mCenter.x - getX();
        float translatedCenterY = this.mCenter.y - getY();
        //initialize the x,y's for the first child in the iteration child
        float x = translatedCenterX + this.mlayoutRadius;
        float y = translatedCenterY;
        int count = 1;
        for (Artist child : mChildren) {
            //set the co-ordinates of each child starting from y=0, x=layoutRadius
            child.setX(x - child.getW()/(float) 2);
            child.setY(y - child.getH()/(float) 2);

            //math to calculate new co-ordinates
            x = translatedCenterX + (float) (this.mlayoutRadius*Math.cos(Math.toRadians(degreeOfSeparation*count)));
            y = translatedCenterY + (float) (this.mlayoutRadius*Math.sin(Math.toRadians(degreeOfSeparation*count)));
            count++;
            //call doLayout for each of it's children to traverse down the tree
            child.doLayout();
        }
    }

//    /**
//     * Test Code
//     * Uncomment/Comment below code to see alignment
//     * @param onCanvas
//     */
//    public void draw(Canvas onCanvas) {
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.BLACK);
//        paint.setStrokeWidth(4);
//
//        float translatedCenterX = this.mCenter.x - getX();
//        float translatedCenterY = this.mCenter.y - getY();
//
//        onCanvas.drawPoint(translatedCenterX, translatedCenterY, paint);
//        onCanvas.drawCircle(translatedCenterX, translatedCenterY, this.mlayoutRadius, paint);
//
//        for (Artist child : mChildren) {
//            onCanvas.save();
//            onCanvas.translate(child.getX(), child.getY());
//            onCanvas.clipRect(0, 0, child.getW(), child.getH());
//            child.draw(onCanvas);
//            onCanvas.restore();
//        }
//    }


    public void setLayoutRadius(float layoutRadius) {
        this.mlayoutRadius = layoutRadius;
    }

    public void setLayoutCenterX(float x) {
        if (mCenter == null) {
            mCenter = new PointF(0f,0f);
        }
        mCenter.set(x, mCenter.y);
    }

    public void setLayoutCenterY(float y) {
        if (mCenter == null) {
            mCenter = new PointF(0f,0f);
        }
        mCenter.set(mCenter.x, y);
    }

    public void setLayoutCenter(float x, float y) {
        setLayoutCenterX(x);
        setLayoutCenterY(y);
    }

    public void setLayoutCenter(PointF pos) {
        if (pos != null) {
            setLayoutCenter(pos.x, pos.y);
        }
    }

    private void setBoundingRect(float x, float y, float w, float h) {
        this.mBoundingRect = new RectF(x, y, x+w, y+h);
    }
}
