package edu.cmu.ssnayak.collage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

/**
 * Implementation of ArtistPrebase for a simple manual
 * layout of an Artist Object consumed in ArtistView
 *
 * Custom Artist implementations should inherit this class
 *
 */
public class ArtistBase extends ArtistPrebase {
	protected PointF mPos;
	protected float mWidth;
	protected float mHeight;
	
	protected Artist mParent;
	protected ArrayList<Artist> mChildren;
	
	protected boolean isIntrinsic;
	
	protected Paint mPaint;
	protected RectF mBoundingRect;
	
	public ArtistBase() {
		initialize();
	}
	
	protected void initialize() {
		mPos = new PointF(0f,0f);
		mChildren = new ArrayList<Artist>();
	}
	
	@Override
	public void setX(float x) {
		if (mPos == null) mPos = new PointF(0f,0f);
		mPos.set(x, mPos.y);
	}

	@Override
	public void setY(float y) {
		if (mPos == null) mPos = new PointF(0f,0f);
		mPos.set(mPos.x, y);
	}

	@Override
	public float getX() {
		return (mPos == null ? 0 : mPos.x);
	}

	@Override
	public float getY() {
		return (mPos == null ? 0 : mPos.y);
	}

	@Override
	public void setW(float w) {
		if (!this.sizeIsIntrinsic()) {
			mWidth = w;
		}
	}

	@Override
	public void setH(float h) {
		if (!this.sizeIsIntrinsic()) {
			mHeight = h;
		}
	}

	@Override
	public float getW() {
		return mWidth;
	}

	@Override
	public float getH() {
		return mHeight;
	}

	@Override
	public Artist getParent() {
		return mParent;
	}

	@Override
	public void setParent(Artist newParent) {
		mParent = newParent;
	}

	@Override
	public int getNumChildren() {
		lazyLoadMChildren();
		return mChildren.size();
	}

	@Override
	public Artist getChildAt(int index) {
		if (index < 0 || index >= mChildren.size()) return null;
		return mChildren.get(index);
	}

	@Override
	public int findChild(Artist child) {
		if (child == null) return -1;
		return mChildren.indexOf(child);
	}

	@Override
	public void addChild(Artist child) {
		if (child == null) return;
		if (child.getParent() != null)
			child.getParent().removeChild(child);
		child.setParent(this);
		mChildren.add(child);
	}

	@Override
	public void removeChildAt(int index) {
		if (index < 0 || index >= mChildren.size()) return;
		Artist child = mChildren.remove(index);
		if (child != null) child.setParent(null);
	}

	@Override
	public void removeChild(Artist child) {
		if (child == null) return;
		if (mChildren.remove(child)) child.setParent(null);
	}

	@Override
	public void moveChildFirst(Artist child) {
		if (child != null && mChildren.contains(child)) {
			mChildren.remove(child);
			mChildren.add(0, child);
		}
	}

	@Override
	public void moveChildLast(Artist child) {
		if (child != null && mChildren.contains(child)) {
			mChildren.remove(child);
			mChildren.add(child);
		}
	}

	@Override
	public void moveChildEarlier(Artist child) {
		int currdx = mChildren.indexOf(child);
		if (currdx > 0) {
			mChildren.remove(child);
			mChildren.add(currdx-1, child);
		}
	}

	@Override
	public void moveChildLater(Artist child) {
		int currdx = mChildren.indexOf(child);
		if (currdx > 0 && currdx != mChildren.size()-1) {
			mChildren.remove(child);
			mChildren.add(currdx+1, child);
		}
	}
	
	private void lazyLoadMChildren() {
		if (mChildren == null) mChildren = new ArrayList<Artist>();
	}

	@Override
	public void doLayout() {
		for (Artist child : mChildren)
			child.doLayout();
	}

	@Override
	public void draw(Canvas onCanvas) {
		for (Artist child : mChildren) {
			onCanvas.save();
			onCanvas.translate(child.getX(), child.getY());
			onCanvas.clipRect(0, 0, child.getW(), child.getH());
			child.draw(onCanvas);
			onCanvas.restore();
		}
	}
}
