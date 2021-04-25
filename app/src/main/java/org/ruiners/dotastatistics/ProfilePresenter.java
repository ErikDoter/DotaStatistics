package org.ruiners.dotastatistics;

import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.profile.ProfileModel;

import java.util.ArrayList;

public interface ProfilePresenter {
    void onLoad(String id);

    void onViewDestroyed();

    interface View {
        void showProfile(ProfileModel profileModel, double win_rate);
        void showRecentMatches(ArrayList<MatchModel> matches);
    }
}
