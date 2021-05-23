package org.ruiners.dotastatistics.repository;

import android.app.Application;
import android.os.Handler;
import android.text.style.AlignmentSpan;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.ruiners.dotastatistics.db.AppDatabase;
import org.ruiners.dotastatistics.db.AppDatabase_Impl;
import org.ruiners.dotastatistics.db.Matches;
import org.ruiners.dotastatistics.db.MatchesDao;
import org.ruiners.dotastatistics.db.MatchesDao_Impl;
import org.ruiners.dotastatistics.models.hero.HeroModel;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.presentation.Presenter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MatchRepository {
    public ArrayList<MatchModel> getRecentMatches(String account_id) {
        String URL = "https://api.opendota.com/api/players/" + account_id + "/recentMatches";
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.Get(URL);
        String response = httpUtil.answer;
        Type type = Types.newParameterizedType(List.class, MatchModel.class);
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ArrayList<MatchModel>> jsonAdapter = moshi.adapter(type);
        try {
            ArrayList<MatchModel> matches;
            matches = jsonAdapter.fromJson(response);
            return matches;
        } catch (IOException e) {
            return null;
        }
    }

    public ArrayList<MatchModel> getAllMatches(String account_id, Integer page) {
        Integer offset = (page - 1) * 14;
        String URL = "https://api.opendota.com/api/players/" + account_id + "/matches?limit=14&offset=" + offset.toString();
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.Get(URL);
        String response = httpUtil.answer;
        Type type = Types.newParameterizedType(List.class, MatchModel.class);
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ArrayList<MatchModel>> jsonAdapter = moshi.adapter(type);
        try {
            ArrayList<MatchModel> matches;
            matches = jsonAdapter.fromJson(response);
            return matches;
        } catch (IOException e) {
            return null;
        }
    }

    public ArrayList<HeroModel> getHeroes() {
        String URL = "https://api.opendota.com/api/heroStats";
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.Get(URL);
        String response = httpUtil.answer;
        Type type = Types.newParameterizedType(List.class, HeroModel.class);
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ArrayList<HeroModel>> jsonAdapter = moshi.adapter(type);
        try {
            ArrayList<HeroModel> heroes;
            heroes = jsonAdapter.fromJson(response);
            return heroes;
        } catch (IOException e) {
            Log.d("getHeroes", "error with json");
            return null;
        }
    }

    public void getMatch(Presenter presenter, long match_id) {
        String id = String.valueOf(match_id);
        String URL = "https://api.opendota.com/api/matches/" + id;
        HttpUtilCallback http = new HttpUtilCallback();
        Handler h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                presenter.onLoad(http.answer);
            }
        };
        http.Get(URL, h);
    }
}