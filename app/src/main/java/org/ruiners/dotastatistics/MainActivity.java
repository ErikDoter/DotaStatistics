package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.SharedPreferences;


import org.ruiners.dotastatistics.presentation.ProfilePresenterImpl;


public class MainActivity extends AppCompatActivity  {
    private SignInActivity mSignInActivity;
    private final static String KEY_AUTH_CHECK = "auth_check";
    private final static String KEY_ID = "id";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
        boolean check_auth = settings.getBoolean(KEY_AUTH_CHECK, false);
        if (check_auth == false) {
            setContentView(R.layout.activity_main);
            Intent intent = new Intent(this, SignInActivity.class);
            View buttonSignIn = findViewById(R.id.signin);
            buttonSignIn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        } else {
            Intent intent = new Intent(this, ProfileActivity.class);
            String id32 = settings.getString(KEY_ID, "0");
            intent.putExtra("account_id", id32);
            startActivity(intent);
        }
    }


}