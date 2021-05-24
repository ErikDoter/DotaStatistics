package org.ruiners.dotastatistics.repository;

import android.os.Handler;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.ruiners.dotastatistics.models.heroes.BestHeroes;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.presentation.Presenter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HeroesRepository {

    public void getBestHeroes(Presenter presenter, String account_id) {
        String URL = "https://api.opendota.com/api/players/" + account_id + "/heroes";
        HttpUtilCallback http = new HttpUtilCallback();
        Handler h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                presenter.onLoad(http.answer);
            }
        };
        http.Get(URL, h);
    }
}
