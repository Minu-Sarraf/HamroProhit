package app.ran.user.hamroprohit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends LoginBaseActvity implements  BaseSliderView.OnSliderClickListener, OnClickListener {


    private static final int REQUEST_READ_CONTACTS = 0;


    // UI references.

    SliderLayout mSlider;
    private PagerIndicator customIndicator;
    SignInButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSlider = (SliderLayout)findViewById(R.id.slider);
        customIndicator = (PagerIndicator) findViewById(R.id.custom_indicator);
        for(int i=0; i<5;i++){
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .image(R.drawable.home)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            mSlider.addSlider(defaultSliderView);
            mSlider.setCustomIndicator(customIndicator);
            btnSignIn.setVisibility(View.GONE);
            btnSignIn.setOnClickListener(this);
            //}
        }

    }


    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mSlider.stopAutoCycle();
        super.onStop();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sign_in) {
            // Signin button clicked

            Log.e("loginbaseactivity", "click");
            signInWithGplus();
            //showProgressDialog();
        }

    }
}

