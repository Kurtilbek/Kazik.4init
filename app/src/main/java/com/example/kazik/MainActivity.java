package com.example.kazik;

//import static com.example.kazik.RuletActivity.MONEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {


    @Override
            protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
              setContentView(R.layout.activity_main);
        }
    public void goToRulet(View view){
        startActivity(new Intent(this, RuletActivity.class));
    }
}