package org.ruiners.dotastatistics.presentation;

import android.util.Log;

import androidx.annotation.Nullable;

import org.ruiners.dotastatistics.ProfileRepository;
import org.ruiners.dotastatistics.ProfilePresenter;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.profile.ProfileModel;
import org.ruiners.dotastatistics.repository.MatchRepository;
import org.ruiners.dotastatistics.repository.ProfileRepositoryImpl;

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
        List<MatchModel> matches = matchRepository.getRecentMatches(id);
    }
}
