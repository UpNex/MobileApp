package com.hp.linkreadersdksample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.nio.charset.CharacterCodingException;

/**
 * Created by ramnivasindani on 9/27/15.
 */
public class UserActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_form);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = User.getUserInstance();
                EditText userName = (EditText) findViewById(R.id.name);
                if(userName.getText().toString().isEmpty()){
                    userName.setError(getResources().getString(R.string.user_required));
                    return;
                }
                EditText userPhone = (EditText) findViewById(R.id.phone);
                EditText userAge = (EditText) findViewById(R.id.age);

                CheckBox mushroom = (CheckBox) findViewById(R.id.mushroom);
                CheckBox nuts = (CheckBox) findViewById(R.id.nuts);
                CheckBox rice = (CheckBox) findViewById(R.id.rice);
                CheckBox wheat = (CheckBox) findViewById(R.id.wheat);
                CheckBox poultry = (CheckBox) findViewById(R.id.poultry);

                user.setUserName(userName.getText().toString());
                if(!(userPhone.getText().toString().isEmpty())){
                    user.setUserPhone(userPhone.getText().toString());
                }

                if(!(userAge.getText().toString().isEmpty())){
                    user.setUserAge(Integer.valueOf(userAge.getText().toString()));
                }

                if(mushroom.isChecked()){
                    user.setAllergies(getResources().getString(R.string.mushroom));
                }

                if(nuts.isChecked()){
                    user.setAllergies(getResources().getString(R.string.nuts));
                }

                if(rice.isChecked()){
                    user.setAllergies(getResources().getString(R.string.rice));
                }
                if(wheat.isChecked()){
                    user.setAllergies(getResources().getString(R.string.wheat));
                }

                if(poultry.isChecked()){
                    user.setAllergies(getResources().getString(R.string.poultry));
                }
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
                overridePendingTransition(0, 0);
                return;
            }
        });
    }
}
