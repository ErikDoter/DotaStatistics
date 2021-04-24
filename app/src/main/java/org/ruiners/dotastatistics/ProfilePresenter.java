package org.ruiners.dotastatistics;

import org.ruiners.dotastatistics.models.profile.ProfileModel;

public interface ProfilePresenter {
    void onLoad();

    void onViewDestroyed();

    interface View {
        void showProfile(ProfileModel profileModel, double win_rate);
    }
}
