package org.ruiners.dotastatistics.presentation;

import android.os.Message;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.ruiners.dotastatistics.MatchPresenter;
import org.ruiners.dotastatistics.models.hero.HeroModel;
import org.ruiners.dotastatistics.models.match.AllMatchModel;
import org.ruiners.dotastatistics.models.match.GameModes;
import org.ruiners.dotastatistics.models.profile.ProfileModel;
import org.ruiners.dotastatistics.repository.MatchRepository;

import java.io.IOException;
import java.util.ArrayList;
import android.os.Handler;
import java.util.logging.LogRecord;

public class MatchPresenterImpl extends Presenter implements MatchPresenter {
    private MatchPresenter.View mview;
    private MatchRepository repository;

    public MatchPresenterImpl(MatchPresenter.View view){
        mview = view;
        repository = new MatchRepository();
    }

    @Override
    public void onOpen(long match_id) {
        repository.getMatch(this, match_id);
    }

    @Override
    public void onLoad(String response) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<AllMatchModel> jsonAdapter = moshi.adapter(AllMatchModel.class);
        AllMatchModel allMatchModel;
        try {
            allMatchModel = jsonAdapter.fromJson(response);
        } catch (IOException e) {
            return;
        }
        ArrayList<HeroModel> heroes = repository.getHeroes();
        allMatchModel.mode = GameModes.getInstance().getData(allMatchModel.game_mode);
        for (int i = 0; i < allMatchModel.players.size(); i++) {
            if(allMatchModel.players.get(i).isRadiant) {
                allMatchModel.radiants_players.add(allMatchModel.players.get(i));
            } else {
                allMatchModel.dire_players.add(allMatchModel.players.get(i));
            }
            for (HeroModel hero : heroes) {
                if (allMatchModel.players.get(i).hero_id == hero.id) {
                    allMatchModel.players.get(i).hero = hero;
                }
            }
        }
        Integer hours = allMatchModel.duration / 3600;
        Integer minutes = allMatchModel.duration / 60 % 60;
        Integer seconds = allMatchModel.duration % 60;
        String hours_string = hours.toString();
        String minutes_string = minutes.toString();
        if (minutes / 10 < 1) {
            minutes_string = "0" + minutes_string;
        }
        String seconds_string = seconds.toString();
        if (seconds / 10 < 1) {
            seconds_string = "0" + seconds_string;
        }
        allMatchModel.duration_all = hours_string + ":" + minutes_string + ":" + seconds_string;
        if(allMatchModel.radiant_win) {
            allMatchModel.win = "Radiant win";
        } else {
            allMatchModel.win = "Dire win";
        }
        if(allMatchModel.skill == 3) {
            allMatchModel.skill_str = "Normal";
        }
        if(allMatchModel.skill == 2) {
            allMatchModel.skill_str = "High";
        }
        if(allMatchModel.skill == 1) {
            allMatchModel.skill_str = "Very High";
        }
        mview.showMatch(allMatchModel);
    }
}
