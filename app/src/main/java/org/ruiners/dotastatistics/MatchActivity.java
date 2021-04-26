package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.ruiners.dotastatistics.db.AppDatabase;
import org.ruiners.dotastatistics.db.Matches;
import org.ruiners.dotastatistics.repository.MatchDbRepository;

public class MatchActivity extends BasicActivity {

    private MatchDbRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = new MatchDbRepository(this.getApplication());
        setContentView(R.layout.activity_match);
        long match = Long.parseLong(getIntent().getExtras().get("match").toString());
        Matches mMatches = new Matches();
        mMatches.match_id = match;
        View buttonAdd = findViewById(R.id.add);
        Log.d("match", String.valueOf(match));
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepository.insert(mMatches);
                Log.d("123", String.valueOf(mRepository.GetAllMatchesDb().get(2).match_id));
            }
        });
    }
}