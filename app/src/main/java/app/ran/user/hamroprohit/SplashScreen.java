package app.ran.user.hamroprohit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rey.material.widget.ProgressView;

import app.ran.user.hamroprohit.Login.LoginActivity;

public class SplashScreen extends AppCompatActivity  {

    private static final long SPLASH_TIME_OUT = 3000;
    String generatedSvgPath;

    ProgressView pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text);
        pg=(ProgressView)findViewById(R.id.splash);
        Typeface fontHindi = Typeface.createFromAsset(getAssets(),
                "fonts/Ananda Lipi Bold Cn Bt.ttf");
        //  fillableLoader = (FillableLoader) findViewById(R.id.fillableLoader);
        tv.setTypeface(fontHindi);
        final SharedPreferences prefs = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //editor.putBoolean("loggedin", true);
        pg.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                pg.setVisibility(View.INVISIBLE);
                if(prefs.getBoolean("loggedin",false)){
                    Intent i = new Intent(SplashScreen.this,Main2Activity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);

        // fillableLoader.setSvgPath(Path.path);
    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
