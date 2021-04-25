package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.ruiners.dotastatistics.presentation.ProfilePresenterImpl;


public class MainActivity extends AppCompatActivity  {
    private SignInActivity mSignInActivity;

    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = new Intent(this, SignInActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonSignIn = findViewById(R.id.signin);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


}