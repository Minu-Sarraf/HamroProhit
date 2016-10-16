package app.ran.user.hamroprohit;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.listener.OnStateChangeListener;

public class SplashScreen extends AppCompatActivity implements OnStateChangeListener {

    private static final long SPLASH_TIME_OUT = 3000;
    String generatedSvgPath;
    FillableLoader fillableLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text);
        Typeface fontHindi = Typeface.createFromAsset(getAssets(),
                "fonts/Ananda Lipi Bold Cn Bt.ttf");
      //  fillableLoader = (FillableLoader) findViewById(R.id.fillableLoader);
        tv.setTypeface(fontHindi);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                 startActivity(i);
              //  finish();


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
    public void onStateChange(int state) {
        //  ((SplashScreen)this).showStateHint(state);
        switch (state) {
            case State.FILL_STARTED:
                Log.e("splash", "startted");
                break;
            case State.FINISHED:
                Log.e("splash", "finished");
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
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
