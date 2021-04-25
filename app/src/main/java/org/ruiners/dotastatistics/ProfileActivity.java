package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.profile.ProfileModel;
import org.ruiners.dotastatistics.presentation.ProfilePresenterImpl;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements ProfilePresenter.View {

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

    }
}