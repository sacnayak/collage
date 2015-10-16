package edu.cmu.ssnayak.collage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArtistView root = new ArtistView(this);


        LinearLayout frame = (LinearLayout) findViewById(R.id.frame);
        frame.addView(root);
    }

}
