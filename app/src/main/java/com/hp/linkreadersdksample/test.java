package com.hp.linkreadersdksample;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class test extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView tt= (TextView)findViewById(R.id.textview1);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String content = extras.getString("content");
            tt.setText(content);

        } else {}


    }


}
