package app.ran.user.hamroprohit.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.android.gms.common.SignInButton;

import app.ran.user.hamroprohit.R;

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
        btnSignIn=(SignInButton)findViewById(R.id.btn_sign_in);
        for(int i=0; i<5;i++){
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .image(R.drawable.home)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            mSlider.addSlider(defaultSliderView);
            mSlider.setCustomIndicator(customIndicator);
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

