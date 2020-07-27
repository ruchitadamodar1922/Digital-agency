package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mgestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mgestureDetector=new GestureDetectorCompat(this, new GestureListner());
    }



    private  class GestureListner extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Uri number=Uri.parse("tel:+91");
            Intent call=new Intent(Intent.ACTION_DIAL,number);
            startActivity(call);
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Toast.makeText(MainActivity.this, "single tap", Toast.LENGTH_SHORT).show();
            Intent i=getPackageManager().getLaunchIntentForPackage("com.whatsapp");
            startActivity(i);
            return super.onSingleTapConfirmed(e);

        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Intent email=new Intent(Intent.ACTION_SEND);
            email.setData(Uri.parse("mailto:"));
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email,"choose client"));
            return super.onDoubleTap(e);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.examplemenu,menu);
        return true;
    }
    public void shortcuts(MenuItem item){
        Intent intent=new Intent(MainActivity.this,keys.class);
        startActivity(intent);
    }

    public void exit(MenuItem item){
        finish();
        System.exit(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mgestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


}
