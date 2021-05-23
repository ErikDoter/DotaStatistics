package org.ruiners.dotastatistics;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.ruiners.dotastatistics.db.Matches;
import org.ruiners.dotastatistics.models.match.AllMatchModel;
import org.ruiners.dotastatistics.presentation.MatchPresenterImpl;
import org.ruiners.dotastatistics.repository.MatchDbRepository;

public class MatchActivity extends BasicActivity implements MatchPresenter.View {

    private MatchDbRepository mRepository;
    private MatchPresenter matchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = new MatchDbRepository(this.getApplication());
        setContentView(R.layout.activity_match_loading);
        long match = Long.parseLong(getIntent().getExtras().get("match").toString());
        Matches mMatches = new Matches();
        mMatches.match_id = match;
//        View buttonAdd = findViewById(R.id.add);
//        Log.d("match", String.valueOf(match));
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mRepository.insert(mMatches);
//                Log.d("123", String.valueOf(mRepository.GetAllMatchesDb().get(2).match_id));
//            }
//        });
        matchPresenter = new MatchPresenterImpl(this);
        matchPresenter.onOpen(match);
    }

    public void showMatch(AllMatchModel match) {
        TextView loading = findViewById(R.id.loading);
        loading.setVisibility(View.INVISIBLE);
        TextView whoWin = findViewById(R.id.victory);
        whoWin.setText(match.win);
        TextView match_id = findViewById(R.id.match_id);
        match_id.setText(String.valueOf(match.match_id));
        TextView skill = findViewById(R.id.skill);
        skill.setText(match.skill);
        TextView mode = findViewById(R.id.mode);
        mode.setText(match.mode);
        TextView duration = findViewById(R.id.duration);
        duration.setText(match.duration_all);
        TextView radiant_score = findViewById(R.id.radiant_score);
        radiant_score.setText(String.valueOf(match.radiant_score));
        TextView dire_score = findViewById(R.id.dire_score);
        dire_score.setText(String.valueOf(match.dire_score));
    }
}