package app.ran.user.hamroprohit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.rey.material.widget.ProgressView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.ran.user.hamroprohit.History_listview;
import app.ran.user.hamroprohit.R;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AppBarLayout.OnOffsetChangedListener {
    ProgressView progresView;
    private AppBarLayout appBarLayout;

    TextView tx;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(this);
        appBarLayout.setExpanded(false);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Refer History");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//attach snackbar to fragment
        progresView = (ProgressView) findViewById(R.id.image_Load);
        // progresView.setVisibility(View.VISIBLE);
        imageView = (ImageView) findViewById(R.id.view);
        tx = (TextView) findViewById(R.id.txview);
        imageView.setVisibility(View.GONE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        tx.setVisibility(View.GONE);
        //getimage();
        //checkinternet();




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
   /* private void getimage() {
        Gson_parser.listener("refer_image", ApiUrl.url_image, null, this);
        Log.e("getimage", "getimage");
    }

    public void checkinternet() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!checkinternet.isNetWorkAvailableNow(getBaseContext())) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertUtils.displaySnackBar(Referactivity.this, "Cannot connect to the internet!Please Try Again.", R.color.red);
                            appBarLayout.setExpanded(true);
                            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.table1);
                            if (fragment instanceof History_listview) {
                                ((History_listview) fragment).getresult(2);
                            }
                            tx.setVisibility(View.VISIBLE);
                            //  tx.setText("Refer Your Friend And Get Offer Of Worth Rs. 500");
                        }
                    });
                }
            }
        });
        th.start();

    }
*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (2):

                if (resultCode == Activity.RESULT_OK) {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.table1);
                    if (fragment instanceof History_listview) {
                        ((History_listview) fragment).getresult(1);
                    }
                } else {
                    //  Log.e("referactivity", "refer not success");
                }
                break;
        }
    }


  /*  @Override
    public void update(ArrayList<String> browser, String urltype) {
        appBarLayout.setExpanded(true);
        final String img, description;
        if (browser != null) {
            img = browser.get(0);
            description = browser.get(1);
            Log.e("IMage & desc", img + " " + description);


            if (img != null) {
                Log.e("IMage & desc 2", img + " " + description);

                Picasso.with(this).load(img).noPlaceholder().into(imageView, new Snackbar.Callback() {
                    @Override
                    public void onSuccess() {
                        // progresView.setVisibility(View.GONE);
                        // tb2.setVisibility(View.GONE);
                        tx.setVisibility(View.INVISIBLE);
                        imageView.setVisibility(View.VISIBLE);
                        //appBarLayout.setExpanded(true);
                    }

                    @Override
                    public void onError() {
                        imageView.setVisibility(View.GONE);
                        // progresView.setVisibility(View.GONE);
                        //  tb2.setVisibility(View.GONE);
                        appBarLayout.setExpanded(true);
                        tx.setBackgroundColor(getResources().getColor(R.color.referimg));
                        tx.setVisibility(View.VISIBLE);
                        if(img==null) {
                            tx.setText("Refer Your Friend And Get Offer Of Worth Rs. 500");
                        }
                    }
                });


            } else {
                Log.e("IMage & desc 3", img + " " + description);
                imageView.setVisibility(View.GONE);
                appBarLayout.setExpanded(true);
                // progresView.setVisibility(View.GONE);
                // tb2.setVisibility(View.GONE);
                tx.setVisibility(View.VISIBLE);
                tx.setText(description);
                tx.setBackgroundColor(getResources().getColor(R.color.referimg));

            }
        }
    }
    @Override
    public void update2(String status) {
        Log.e("referactivity", status);
    }*/

   /* @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.table1);
        if (fragment instanceof History_listview) {
            ((History_listview) fragment).swipe_offset(verticalOffset);
        }
        //  History_listview fragment2 = (History_listview) getFragmentManager().findFragmentById(R.id.table1);
        // fragment.swipe_offset(verticalOffset);
    }
*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    @Override
    public void onClick(View v) {

    }
}
