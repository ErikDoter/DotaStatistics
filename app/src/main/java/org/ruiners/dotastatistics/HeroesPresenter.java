package org.ruiners.dotastatistics;

import org.ruiners.dotastatistics.models.heroes.BestHeroes;
import org.ruiners.dotastatistics.models.match.AllMatchModel;
import org.ruiners.dotastatistics.models.match.MatchModel;

import java.util.ArrayList;

public interface HeroesPresenter {
    void onOpen(String id);
    void onLoad(String response);
    interface View {
        void getBestHeroes(ArrayList<BestHeroes> heroes);
    }
}
