package org.ruiners.dotastatistics;

public interface ProfilePresenter {
    void onShowMatchClicked();

    void onViewDestroyed();

    interface View {

        void showMatchId(String id);

    }
}
