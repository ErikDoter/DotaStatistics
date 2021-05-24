package org.ruiners.dotastatistics.presentation;

import android.os.Message;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.ruiners.dotastatistics.HeroesPresenter;
import org.ruiners.dotastatistics.MatchPresenter;
import org.ruiners.dotastatistics.MatchesPresenter;
import org.ruiners.dotastatistics.models.hero.HeroModel;
import org.ruiners.dotastatistics.models.heroes.BestHeroes;
import org.ruiners.dotastatistics.models.match.AllMatchModel;
import org.ruiners.dotastatistics.models.match.GameModes;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.profile.ProfileModel;
import org.ruiners.dotastatistics.repository.HeroesRepository;
import org.ruiners.dotastatistics.repository.MatchRepository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.os.Handler;

import java.util.List;
import java.util.logging.LogRecord;

public class HeroesPresenterImpl extends Presenter implements HeroesPresenter {
    private HeroesPresenter.View mview;
    private HeroesRepository repository;
    private MatchRepository matchRepository;

    public HeroesPresenterImpl(HeroesPresenter.View view){
        mview = view;
        repository = new HeroesRepository();
        matchRepository = new MatchRepository();
    }

    @Override
    public void onOpen(String id) {
        repository.getBestHeroes(this, id);
    }

    @Override
    public void onLoad(String response) {
        Type type = Types.newParameterizedType(List.class, BestHeroes.class);
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ArrayList<BestHeroes>> jsonAdapter = moshi.adapter(type);
        ArrayList<BestHeroes> bestHeroes;
        try {
            bestHeroes = jsonAdapter.fromJson(response);
        } catch (IOException e) {
            return;
        }
        ArrayList<HeroModel> heroes = matchRepository.getHeroes();
        for (int i = 0; i < bestHeroes.size(); i++) {
            for (HeroModel hero : heroes) {
                if (Integer.parseInt(bestHeroes.get(i).hero_id) == hero.id) {
                    Log.d("hero", bestHeroes.get(i).hero_id);
                    bestHeroes.get(i).hero = hero;
                }
            }
        }
        mview.getBestHeroes(bestHeroes);
    }
}
