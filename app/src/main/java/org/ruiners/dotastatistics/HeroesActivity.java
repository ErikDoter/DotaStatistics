package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HeroesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        String account_id = getIntent().getExtras().get("account_id").toString();
    }
}