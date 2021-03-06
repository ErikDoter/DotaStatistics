package org.ruiners.dotastatistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.profile.ProfileModel;
import org.ruiners.dotastatistics.presentation.ProfilePresenterImpl;
import org.ruiners.dotastatistics.utils.MatchAdapter;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class ProfileActivity extends BasicActivity implements ProfilePresenter.View {

    private ProfilePresenter mProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mProfilePresenter = new ProfilePresenterImpl(this);
        String account_id = getIntent().getExtras().get("account_id").toString();
        mProfilePresenter.onLoad(account_id);
    }

    @Override
    protected void onDestroy() {
        mProfilePresenter.onViewDestroyed();
        super.onDestroy();
    }

    @Override
    public void showProfile(ProfileModel profileModel, double win_rate) {
        TextView nickname = (TextView)findViewById(R.id.nickname);
        nickname.setText(profileModel.profile.personaname);
        ImageView avatar = (ImageView)findViewById(R.id.avatar);
        Picasso.get().load(profileModel.profile.avatarfull).into(avatar);
        TextView mmr = (TextView)findViewById(R.id.mmr);
        mmr.setText(String.valueOf(profileModel.mmr_estimate.estimate) + "mmr");
        TextView wins = (TextView)findViewById(R.id.wins);
        wins.setText(String.valueOf(profileModel.win_lose.win));
        TextView loses = (TextView)findViewById(R.id.loses);
        loses.setText(String.valueOf(profileModel.win_lose.lose));
        TextView winrate = (TextView)findViewById(R.id.winrate);
        winrate.setText(String.format("%.2f", win_rate*100) + "%");
    }

    @Override
    public void showRecentMatches(ArrayList<MatchModel> matches) {
        RecyclerView recyclerView = findViewById(R.id.recent_matches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchAdapter matchAdapter = new MatchAdapter();
        matchAdapter.data = matches;
        recyclerView.setAdapter(matchAdapter);
    }

}