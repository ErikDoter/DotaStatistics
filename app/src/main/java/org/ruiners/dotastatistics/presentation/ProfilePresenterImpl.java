package org.ruiners.dotastatistics.presentation;

import android.util.Log;

import androidx.annotation.Nullable;

import org.ruiners.dotastatistics.ProfileRepository;
import org.ruiners.dotastatistics.ProfilePresenter;
import org.ruiners.dotastatistics.models.hero.HeroModel;
import org.ruiners.dotastatistics.models.match.GameModes;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.profile.ProfileModel;
import org.ruiners.dotastatistics.repository.MatchRepository;
import org.ruiners.dotastatistics.repository.ProfileRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ProfilePresenterImpl implements ProfilePresenter {
    @Nullable
    private ProfilePresenter.View mView;
    private ProfileRepository profileRepository;
    private MatchRepository matchRepository;

    public ProfilePresenterImpl(View view) {
        mView = view;
        profileRepository = new ProfileRepositoryImpl();
        matchRepository = new MatchRepository();
    }


    @Override
    public void onViewDestroyed() {
        mView = null;
    }

    @Override
    public void onLoad(String id) {
        ProfileModel profileModel = profileRepository.getProfile(id);
        double win = (double)profileModel.win_lose.win;
        double lose = (double)profileModel.win_lose.lose;
        double win_rate = win/(win+lose);
        mView.showProfile(profileModel, win_rate);
        ArrayList<MatchModel> matches = matchRepository.getRecentMatches(id);
        ArrayList<HeroModel> heroes = matchRepository.getHeroes();
        for (int i = 0; i < matches.size(); i++) {
            for(HeroModel hero: heroes) {
                if(matches.get(i).hero_id == hero.id) {
                    matches.get(i).hero = hero;
                }
            }
            matches.get(i).mode = GameModes.getInstance().getData(matches.get(i).game_mode);
            matches.get(i).win = false;
            if(matches.get(i).radiant_win && matches.get(i).player_slot < 128) {
                matches.get(i).win = true;
            }
            if (!matches.get(i).radiant_win && matches.get(i).player_slot > 127) {
                matches.get(i).win = true;
            }
        }
        mView.showRecentMatches(matches);
    }
}
