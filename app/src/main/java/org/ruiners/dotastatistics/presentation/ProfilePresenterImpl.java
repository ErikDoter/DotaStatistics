package org.ruiners.dotastatistics.presentation;

import androidx.annotation.Nullable;

import org.ruiners.dotastatistics.MatchRepository;
import org.ruiners.dotastatistics.ProfilePresenter;

public class ProfilePresenterImpl implements ProfilePresenter {
    @Nullable
    private ProfilePresenter.View mView;
    private MatchRepository matchRepository;

    public ProfilePresenterImpl(View view, MatchRepository repository) {
        mView = view;
        matchRepository = repository;
    }

    @Override
    public void onShowMatchClicked() {
        String id = matchRepository.getMatch();
        mView.showMatchId(id);
    }

    @Override
    public void onViewDestroyed() {
        mView = null;
    }
}
