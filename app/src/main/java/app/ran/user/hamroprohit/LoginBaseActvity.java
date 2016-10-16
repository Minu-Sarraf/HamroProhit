package app.ran.user.hamroprohit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;


/**
 * Created by sabin on 8/1/15.
 */
public class LoginBaseActvity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 0;
    // Logcat tag
    private static final String TAG = "MainActivity";

    // Profile pic image size in pixels
    private static final int PROFILE_PIC_SIZE = 400;

    // Google client to interact with Google API
    private static GoogleApiClient mGoogleApiClient;

    /**
     * A flag indicating that a PendingIntent is in progress and prevents us
     * from starting further intents.
     */
    private boolean mIntentInProgress;

    private boolean mSignInClicked;

    private ConnectionResult mConnectionResult;

    public SignInButton btnSignIn;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    MyBus.getInstance().register(this);
        // Button click listeners
        Log.e("loginbase", "loginbase");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }



    protected void onStop() {
        super.onStop();
      /*  if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
            Log.e("stop","stop");
        }*/
    }

    /**
     * Method to resolve any signin errors
     */
    private void resolveSignInError() {
        hideProgressDialog();
        Log.e("loginbase", "resolveout");
        if (mConnectionResult.hasResolution()) {
            try {
                Log.e("loginbase", "loginbasetry");
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
                Log.e("loginbase", "loginbasecatch");
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }
            mIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
                showProgressDialog();
            }
        }
    }

    @Override
    public void onConnected(Bundle arg0) {
        mSignInClicked = false;
        // Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
        Log.e("loginbase", "connected");
        // Get user's information
        getProfileInformation();

        // Update the UI after signin
        updateUI(true);

    }

    /**
     * Updating the UI, showing/hiding buttons and profile layout
     */
    public void updateUI(boolean isSignedIn) {
        Log.e("base", "updateui");
        hideProgressDialog();
        if (isSignedIn) {
            Intent intent = new Intent(this, Main2Activity.class);
            //  intent.putExtra("email",);
            startActivity(intent);
            finish();

        } else {
          //  btnSignIn.setVisibility(View.VISIBLE);

        }
    }

    /**
     * Fetching user's information name, email, profile pic
     */
    private void getProfileInformation() {
        try {
            Log.e("base", "profile");
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                String personName = currentPerson.getDisplayName();
                String personPhotoUrl = currentPerson.getImage().getUrl();
                String personGooglePlusProfile = currentPerson.getUrl();
                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

                personPhotoUrl = personPhotoUrl.substring(0,
                        personPhotoUrl.length() - 2)
                        + PROFILE_PIC_SIZE;


                SharedPreferences prefs = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("loggedin", true);
                editor.putString("name", personName);
                editor.putString("email", email);
                editor.putString("username", email);
                editor.putString("profilepic", personPhotoUrl);
                if (currentPerson.hasCover()) {
                    String personCoverUrl = currentPerson.getCover().getCoverPhoto().getUrl();
                    editor.putString("coverpic", personCoverUrl);
                }
                editor.commit();
                // by default the profile url gives 50x50 px image only
                // we can replace the value with whatever dimension we want by
                // replacing sz=X

                //     new SocialLoginTask(this,email,personName).execute();

            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
        updateUI(false);
    }

    /**
     * Button on click listener
     */
    @Override
    public void onClick(View v) {
        Log.e("loginbaseactivity", "click" + v.getId());
        switch (v.getId()) {
            case R.id.btn_sign_in:
                // Signin button clicked
                Log.e("loginbaseactivity", "click");
                signInWithGplus();
                break;
        }
    }
    /**
     * Sign-in into google
     */
    public void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            Log.e("loginbase", "signin");
            resolveSignInError();
        }
    }

    /**
     * Sign-out from google
     */
    public static void signOutFromGplus(boolean a) {
        if (a) {
            if (mGoogleApiClient.isConnected()) {
                Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                mGoogleApiClient.disconnect();
                //  mGoogleApiClient.connect();
                //  updateUI(false);

            }
        }
    }

    /**
     * Revoking access from google
     */
    private void revokeGplusAccess() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status arg0) {
                            Log.e(TAG, "User access revoked!");
                            mGoogleApiClient.connect();
                            updateUI(false);
                        }

                    });
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //MyBus.getInstance().unregister(this);
    }


    protected void showProgressDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading....");
            dialog.setIndeterminate(true);
        }
        dialog.show();
    }

    protected void hideProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


}
