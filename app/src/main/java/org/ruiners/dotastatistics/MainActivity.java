package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.ruiners.dotastatistics.presentation.ProfilePresenterImpl;
import org.ruiners.dotastatistics.repository.MatchRepositoryImpl;

public class MainActivity extends AppCompatActivity implements ProfilePresenter.View {

    private ProfilePresenter mProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View button = findViewById(R.id.getMatch);
        mProfilePresenter = new ProfilePresenterImpl(this, new MatchRepositoryImpl());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProfilePresenter.onShowMatchClicked();
            }
        });
    }

    public void showMatchId(String id) {
        TextView match = (TextView)findViewById(R.id.matchId);
        match.setText(id);
    }

    @Override
    protected void onDestroy() {
        mProfilePresenter.onViewDestroyed();
        super.onDestroy();
    }
}