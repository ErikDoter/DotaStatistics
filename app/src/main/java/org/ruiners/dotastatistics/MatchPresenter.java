package org.ruiners.dotastatistics;

import org.ruiners.dotastatistics.models.match.AllMatchModel;
import org.ruiners.dotastatistics.models.match.MatchModel;

import java.util.ArrayList;

public interface MatchPresenter {
    void onOpen(long match_id);
    void onLoad(String response);
    interface View {
        void showMatch(AllMatchModel match);
    }
}
