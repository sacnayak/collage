package edu.cmu.ssnayak.collage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Minimal test app for SSUI Mobile Project #2 (Collage).  To use this 
 * test framework implement the body of buildTest().
 * 
 * @author Scott Hudson
 *
 */
public class TestActivity extends Activity {
	
	protected Artist buildTest() {
		// create and return a test Artist tree here...
        Artist child = new ArtistBase();
        child.addChild(new ArtistBase());
        return /* replace this: */ new ArtistBase();
	}

	protected Artist buildSimpleFrame() {
        SimpleFrame simpleFrame = new SimpleFrame(20, 20, 400, 400);
        return simpleFrame;
    }

    protected Artist buildSolidBackDrop() {
        SolidBackDrop solidBackDrop = new SolidBackDrop(10, 10, 400, 800, Color.GRAY);
        return solidBackDrop;
    }

    protected Artist buildIcon() {
        Bitmap iconBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_launcher);
        Bitmap iconScaled = Bitmap.createScaledBitmap(iconBitmap, 300, 300, false);
        Icon icon = new Icon(10, 10, iconScaled);
        return icon;
    }

    protected NinePartImage buildNinePartImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.bluebutton);
        NinePatch patches = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
        NinePartImage ninePartImage = new NinePartImage(10,10,200,200,patches);
        return ninePartImage;
    }

    protected TextArtist buildTextArtist() {
        TextArtist textArtist = new TextArtist(0, 0, "SSUI Rocks!!!!", Typeface.DEFAULT_BOLD, 50f);
        return textArtist;
    }

    protected Pile buildPile() {
        Pile pile = new Pile(5, 200, 200, 100);
        pile.addChild(new SolidBackDrop(0, 100, 900, 900, Color.GRAY));
        pile.addChild(new SimpleFrame(0, 0, 100, 100));
        return pile;
    }

    protected Row buildRow() {
        Row row = new Row(0, 0, 500, 500);
        row.addChild(new SolidBackDrop(0, 0, 50, 150, Color.GRAY));
        row.addChild(new SolidBackDrop(0, 0, 50, 150, Color.BLACK));
        row.addChild(new SimpleFrame(0,0,500,2));//to check visuall if the artists are center aligned
        return row;
    }

    protected Column buildColumn() {
        Column col = new Column(0, 0, 500, 500);
        col.addChild(new SolidBackDrop(0, 0, 50, 150, Color.GRAY));
        col.addChild(new SolidBackDrop(0, 0, 50, 150, Color.BLACK));
        col.addChild(new SolidBackDrop(0, 0, 50, 150, Color.GRAY));
        col.addChild(new SolidBackDrop(0, 0, 50, 150, Color.BLACK));
        col.addChild(new SolidBackDrop(0, 0, 50, 150, Color.GRAY));
        col.addChild(new SolidBackDrop(0, 0, 50, 150, Color.BLACK));
        col.addChild(new SolidBackDrop(0, 0, 50, 150, Color.GRAY));
        col.addChild(new SolidBackDrop(0, 0, 50, 150, Color.BLACK));
        //col.addChild(new SimpleFrame(0,0,2,500));//to check visually if the artists are center aligned

        return col;
    }

    protected Circle buildCircle() {
        Circle circle = new Circle(10, 10, 500, 500, 255, 255, 100);
        circle.addChild(new SolidBackDrop(0, 0, 50, 50, Color.BLUE));
        circle.addChild(new SolidBackDrop(0, 0, 50, 50, Color.RED));
        circle.addChild(new SolidBackDrop(0, 0, 50, 50, Color.GREEN));
        circle.addChild(new SolidBackDrop(0, 0, 50, 50, Color.BLACK));
        //circle.addChild(new SolidBackDrop(0, 0, 50, 50, Color.RED));
        return circle;
    }

    protected OvalClip buildOvalClip() {
        OvalClip ovalClip = new OvalClip(0,0,400,600);
        ovalClip.addChild(new SolidBackDrop(0, 0, 1000, 1000, Color.GRAY));
        ovalClip.addChild(new SolidBackDrop(200, 0, 1000, 1000, Color.BLACK));
        return ovalClip;
    }

    protected GoldenRectangle buildGoldenRectangle() {
        GoldenRectangle goldenRectangle = new GoldenRectangle(0, 0, 250, 404.5f);
        goldenRectangle.addChild(new SolidBackDrop(0,0, 600, 600, Color.GRAY));
        goldenRectangle.addChild(new SolidBackDrop(0,0, 600, 600, Color.BLACK));
        goldenRectangle.addChild(new SolidBackDrop(0,0, 600, 600, Color.RED));
        goldenRectangle.addChild(new SolidBackDrop(0,0, 600, 600, Color.GRAY));
        goldenRectangle.addChild(new SolidBackDrop(0,0, 600, 600, Color.GRAY));
        goldenRectangle.addChild(new SolidBackDrop(0,0, 600, 600, Color.BLACK));
        goldenRectangle.addChild(new SolidBackDrop(0,0, 600, 600, Color.RED));
//        goldenRectangle.addChild(new SolidBackDrop(0,0, 400, 600, Color.WHITE));
//        goldenRectangle.addChild(new SolidBackDrop(0,0, 400, 600, Color.GRAY));
//        goldenRectangle.addChild(new SolidBackDrop(0,0, 400, 600, Color.BLACK));
//        goldenRectangle.addChild(new SolidBackDrop(0,0, 400, 600, Color.WHITE));
        return goldenRectangle;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// let the base class take care of bookkeeping stuff...
        super.onCreate(savedInstanceState);

        // create an ArtistView widget and install a generated test tree there
        ArtistView root = new ArtistView(this);
        root.setChildArtist(buildGoldenRectangle());
        
        // put that widget in a frame so we have a layout object above it to 
        // listen to the fact that it doesn't want to be expanded to fill the
        // screen (the frame gets expanded, but then the root object stays 
        // it's original size within the frame).  

        FrameLayout frame = new FrameLayout(this);
        frame.addView(root);
        setContentView(frame);
    }
    

	protected Artist buildTest1(){
		SolidBackDrop rootArtist = new SolidBackDrop(0, 0, 400, 800, Color.WHITE);
		putAll(rootArtist);
		Pile p = new Pile(5, 200, 100, 100);
		p.addChild(new SolidBackDrop(0, 0, 900, 900, Color.GRAY));
		p.addChild(new SimpleFrame(0, 0, 100, 100));
		rootArtist.addChild(p);
		putAll(p);
		p.addChild(new SolidBackDrop(0, 0, 20, 20, Color.GRAY));
		Row r = new Row(5, 310, 350, 50);
		rootArtist.addChild(r);
		putAll(r);
		Column col = new Column(5, 370, 50, 200);
		rootArtist.addChild(col);
		putAll(col);

		return rootArtist;
	}
    
    protected Artist buildTest2(){
		SolidBackDrop rootArtist = new SolidBackDrop(0, 0, 400, 800, Color.WHITE);
		SolidBackDrop s = new SolidBackDrop(0, 0, 50, 50, Color.GREEN);
		rootArtist.addChild(s);
		s.setPosition(-40, -40);
		SolidBackDrop s2 = new SolidBackDrop(0, 0, 50, 50, Color.BLUE);
		rootArtist.addChild(s2);
		SolidBackDrop s3 = new SolidBackDrop(0, 0, 50, 50, Color.RED);
		rootArtist.addChild(s3);
		rootArtist.moveChildLast(s);
		rootArtist.removeChild(s3);

		Circle c = new Circle(100, 100, 300, 400, 150, 200, 100);
		rootArtist.addChild(c);
		for(int i = 0; i<8; i++){
			c.addChild(new SolidBackDrop(0, 0, 20, 20, Color.BLUE));
		}
		SolidBackDrop s4 = new SolidBackDrop(100, 100, 50, 50, Color.MAGENTA);
		c.addChild(s4);
		rootArtist.addChild(s4);

		OvalClip o = new OvalClip(5, 200, 50, 50);
		rootArtist.addChild(o);
		o.addChild(new SolidBackDrop(0, 0, 50, 50, Color.BLUE));
		putAll(o, true);

		return rootArtist;
	}
    
    protected Artist buildTest3(){
		SolidBackDrop rootArtist = new SolidBackDrop(0, 0, 400, 800, Color.WHITE);

		try{
			rootArtist.addChild(null);
			Log.d("ssui_grading", "Add null child:PASS");
		} catch (Exception e){
			Log.d("ssui_grading", "Add null Child:FAIL");
		}

		try{
			if(rootArtist.getChildAt(-1) == null)
				Log.d("ssui_grading", "Get bad Child:PASS");
			else
				Log.d("ssui_grading", "Get bad Child:FAIL");
		} catch (Exception e){
			Log.d("ssui_grading", "Get bad Child:FAIL");
		}

		try{
			if(rootArtist.findChild(new SolidBackDrop(0, 0, 400, 800, Color.WHITE)) == -1 && rootArtist.findChild(null) == -1)
				Log.d("ssui_grading", "Find bad Child:PASS");
			else
				Log.d("ssui_grading", "Find bad Child:FAIL");
		} catch (Exception e){
			Log.d("ssui_grading", "Find bad Child:FAIL");
		}


		try{
			rootArtist.moveChildLater(new SolidBackDrop(0, 0, 400, 800, Color.WHITE));
			rootArtist.moveChildLater(null);
			Log.d("ssui_grading", "Move bad Child:PASS");
		} catch (Exception e){
			Log.d("ssui_grading", "Move bad Child:FAIL");
		}

		try{
			SolidBackDrop s = new SolidBackDrop(0, 0, 50, 50, Color.GREEN);
			s.setPosition(null);
			s.setSize(null);
			Log.d("ssui_grading", "Set position/size null:PASS");
		} catch (Exception e){
			Log.d("ssui_grading", "Set position/size null:FAIL");
		}

		return rootArtist;
	}
	
  protected void putAll(Artist rootArtist){putAll(rootArtist, false);}
    
	protected void putAll(Artist rootArtist, boolean allAtOrigin){
		if(allAtOrigin){
			rootArtist.addChild(new SimpleFrame(0, 0, 20, 20));
			rootArtist.addChild(new SolidBackDrop(0, 0, 20, 20, Color.BLUE));
			rootArtist.addChild(new Icon(0, 0, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)));
			Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.bluebutton);
			NinePatch patches = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
			rootArtist.addChild(new NinePartImage(0, 0, 200, 20, patches));
			rootArtist.addChild(new TextArtist(0, 0, "SSUI RoCkS!!!!", Typeface.DEFAULT_BOLD, 50f));
		}
		else{
			rootArtist.addChild(new SimpleFrame(5, 5, 20, 20));
			rootArtist.addChild(new SolidBackDrop(5, 30, 20, 20, Color.BLUE));
			rootArtist.addChild(new Icon(5, 55, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)));
			Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.bluebutton);
			NinePatch patches = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
			rootArtist.addChild(new NinePartImage(30, 5, 200, 20, patches));
			rootArtist.addChild(new TextArtist(30, 30, "SSUI RoCkS!!!!", Typeface.DEFAULT_BOLD, 50f));
		}
	}
}
