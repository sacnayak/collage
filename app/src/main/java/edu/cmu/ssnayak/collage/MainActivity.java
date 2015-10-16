package edu.cmu.ssnayak.collage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Typeface;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArtistView root = new ArtistView(this);

        //create a bitmap out of the image
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.twitter_logo);

        //create scaled bitmap according to size of layout
        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
        //Icon icon = new Icon(0, 0, scaledBitmap);

        //SimpleFrame simpleFrame = new SimpleFrame(0, 0, 100, 100);

        //root.setChildArtist(simpleFrame);

        //ImageView imgView = new ImageView(this);
        //imgView.setImageBitmap(scaledBitmap);

//        Typeface typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
//        TextArtist textArtist = new TextArtist(0,0,"Hello World!", typeface, 80);
//        root.setChildArtist(textArtist);

        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ninepatch_source);
        NinePatch ninePatch = new NinePatch(bitmap, bitmap.getNinePatchChunk(), null);
        NinePartImage ninePartImage = new NinePartImage(0, 0, 50, 50, ninePatch);
        root.setChildArtist(ninePartImage);
        LinearLayout frame = (LinearLayout) findViewById(R.id.frame);
        frame.addView(root);
    }

}
