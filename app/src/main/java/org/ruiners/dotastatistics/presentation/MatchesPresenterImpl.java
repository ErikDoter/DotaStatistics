package org.ruiners.dotastatistics.presentation;

import org.ruiners.dotastatistics.MatchesPresenter;
import org.ruiners.dotastatistics.models.hero.HeroModel;
import org.ruiners.dotastatistics.models.match.GameModes;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.repository.MatchRepository;

import java.util.ArrayList;

public class MatchesPresenterImpl implements MatchesPresenter {
    private MatchesPresenter.View mView;
    private MatchRepository repository;

    public MatchesPresenterImpl(MatchesPresenter.View view) {
        mView = view;
        repository = new MatchRepository();
    }

    @Override
    public void onLoad(String id, Integer page) {
        ArrayList<MatchModel> matches = repository.getAllMatches(id, page);
        ArrayList<HeroModel> heroes = repository.getHeroes();
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
        mView.showMatches(matches);
    }
}
