//
//  MainActivity.java
//  com.hp.linkreadersdk
//  LinkReaderSDK
//
//  Copyright (c) 2015 HP. All rights reserved.

package com.hp.linkreadersdksample;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hp.linkreadersdk.EasyReadingCallback;
import com.hp.linkreadersdk.EasyReadingFragment;
import com.hp.linkreadersdk.ErrorCode;
import com.hp.linkreadersdk.utils.Constants;
import com.hp.linkreadersdksample.util.ProductUtil;

public class MainActivity extends ActionBarActivity implements NetworkErrorDialogFragment.DialogListener
        , AuthenticationErrorDialogFragment.DialogListener {

    private Button startScanButton;

    enum AuthState {
        AUTHORIZING, AUTHORIZED, NOT_AUTHORIZED
    }

    private static String AUTH_STATE = "AUTH_STATE";
    private AuthState authState = AuthState.AUTHORIZING;

    private static EasyReadingFragment easyReadingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.hp.linkreadersdksample.R.layout.activity_main);

        startScanButton = (Button) findViewById(com.hp.linkreadersdksample.R.id.start_scan_button);
        final LinearLayout linearLayout = (LinearLayout) findViewById(com.hp.linkreadersdksample.R.id.main_layout);
        final LinearLayout startScanFragment = (LinearLayout) findViewById(com.hp.linkreadersdksample.R.id.start_scan);
        startScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(linearLayout.getId(), easyReadingFragment).commit();
                startScanFragment.setVisibility(View.GONE);
            }
        });

        if (savedInstanceState == null) {
            setAuthState(AuthState.AUTHORIZING);
            easyReadingFragment = EasyReadingFragment.initWithClientID(Credentials.CLIENT_ID, Credentials.CLIENT_SECRET, new EasyReadingCallback() {
                @Override
                public void onAuthenticationSuccess(String successMessage) {
                    MainActivity.this.onAuthenticationSuccess(successMessage);
                }

                @Override
                public void onAuthenticationError(ErrorCode errorCode) {
                    MainActivity.this.onAuthenticationError(errorCode);
                }
            }, this);
        }

        insertProductDetails();
    }

    private void insertProductDetails(){
        Product product1 = new Product( "product_1","Cheese Pizza (70 g)", "Veg", "272g","9.8g", "21mg", "3.8g",
                "12g",   "A,B1,B2,B3,B5,B12,E,K", "pizza sauce,cheese,basil", R.drawable.cheese_pizza,2);
        Product product2 = new Product( "product_2","Hamburger", "Non-Veg", "354g","136g", "56mg", "5g",
                "20g",   "B12", "Beef, Cheese, Onion", R.drawable.hamburger,1);
        Product product3 = new Product( "product_3","Falafel", "Veg", "57g","3g", "90g", "2.8h",
                "2g",   "A,B,C", "Beans, onions, scallions, Parsley, Garlic, Cumin, Coriander",R.drawable.falafel,1);
        Product product4 = new Product( "product_4", "Chicken Tandoori", "Non-Veg", "227g","10g", "87mg", "0g",
                "27g",   "K,C", "Yogurt, lemon juice, allspice, black pepper, cayenne pepper, cinnamon, cumin", R.drawable.tandoori_chicken,2);
        Product product5 = new Product( "product_5", "Wada-Pav", "Veg", "300g","250g", "70mg", "6g",
                "5g",   "A,C", "Ginger, Chilly, Asafoetida, Nuts",R.drawable.wadapav,1);
        Product product6 = new Product( "product_6","Chicken Sandwich", "Non-Veg", "515g","29g", "60mg", "0g",
                "24g",   "A,C,B6,B12,D", "potatoes, cream, onions, chicken, cheese",R.drawable.chicken_sandwich,1);

        ProductUtil.setProduct(product1);
        ProductUtil.setProduct(product2);
        ProductUtil.setProduct(product3);
        ProductUtil.setProduct(product4);
        ProductUtil.setProduct(product5);
        ProductUtil.setProduct(product6);

    }
    private void setAuthState(AuthState authorizing) {
        authState = authorizing;
        switch (authorizing) {
            case AUTHORIZING:
                showProgress();
                resetMessage();
                break;
            case AUTHORIZED:
                hideProgress();
                resetMessage();
                startScanButton.setEnabled(true);
                break;
            case NOT_AUTHORIZED:
                final TextView message = (TextView) findViewById(com.hp.linkreadersdksample.R.id.message);
                message.setText(com.hp.linkreadersdksample.R.string.not_authorized);
                hideProgress();
                startScanButton.setVisibility(View.INVISIBLE);
                break;
        }
        Log.d("AuthState", authorizing + "");
    }

    private void resetMessage() {
        final TextView message = (TextView) findViewById(com.hp.linkreadersdksample.R.id.message);
        message.setText("");
    }

    private void onAuthenticationSuccess(String successMessage) {
        setAuthState(AuthState.AUTHORIZED);
    }

    private void showAuthenticationNetworkFailed() {
        NetworkErrorDialogFragment networkErrorDialogFragment = new NetworkErrorDialogFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(networkErrorDialogFragment, NetworkErrorDialogFragment.TAG);
        ft.commitAllowingStateLoss();
    }

    private void showAuthenticationFailed() {
        AuthenticationErrorDialogFragment authenticationErrorDialogFragment = new AuthenticationErrorDialogFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(authenticationErrorDialogFragment, AuthenticationErrorDialogFragment.TAG);
        ft.commitAllowingStateLoss();
    }

    public void reauthenticate() {

        setAuthState(AuthState.AUTHORIZING);
        easyReadingFragment.reauthenticate(Credentials.CLIENT_ID, Credentials.CLIENT_SECRET, new EasyReadingCallback() {
            @Override
            public void onAuthenticationSuccess(String successMessage) {
                Log.d(Constants.LOG_TAG, "MainActivity:onAuthenticationSuccess:142");
                MainActivity.this.onAuthenticationSuccess(successMessage);
            }

            @Override
            public void onAuthenticationError(ErrorCode errorCode) {
                Log.d(Constants.LOG_TAG, "MainActivity:onAuthenticationError:148");
                MainActivity.this.onAuthenticationError(errorCode);
            }
        }, this);
    }

    private void showProgress() {
        final ProgressBar progress = (ProgressBar) findViewById(com.hp.linkreadersdksample.R.id.progress);
        progress.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        final ProgressBar progress = (ProgressBar) findViewById(com.hp.linkreadersdksample.R.id.progress);
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(AUTH_STATE, authState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        AuthState authState = (AuthState) savedInstanceState.getSerializable(AUTH_STATE);
        setAuthState(authState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void onAuthenticationError(ErrorCode errorCode) {
        Log.d("Auth", "ErrorCode " + errorCode.name());
        hideProgress();
        if (errorCode == ErrorCode.CONNECTION_ERROR) {
            showAuthenticationNetworkFailed();
        } else {
            showAuthenticationFailed();
        }
    }

    @Override
    public void onReauthenticationClick() {
        reauthenticate();
    }

    @Override
    public void onCancelClick() {
        setAuthState(AuthState.NOT_AUTHORIZED);
    }

    @Override
    public void onConfirmation() {
        setAuthState(AuthState.NOT_AUTHORIZED);
    }
}