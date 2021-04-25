package org.ruiners.dotastatistics;

import org.ruiners.dotastatistics.models.match.MatchModel;

import java.util.ArrayList;

public interface MatchesPresenter {
    void onLoad(String id, Integer page);
    interface View {
        void showMatches(ArrayList<MatchModel> matches);
    }
}
