package org.ruiners.dotastatistics.repository;

import android.text.style.AlignmentSpan;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.ruiners.dotastatistics.models.match.MatchModel;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class MatchRepository {
    public List<MatchModel> getRecentMatches(String account_id) {
        String URL = "https://api.opendota.com/api/players/" + account_id + "/recentMatches";
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.Get(URL);
        String response = httpUtil.answer;
        Type type = Types.newParameterizedType(List.class, MatchModel.class);
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List<MatchModel>> jsonAdapter = moshi.adapter(type);
        try {
            List<MatchModel> matches;
            matches = jsonAdapter.fromJson(response);
            return matches;
        } catch (IOException e) {
            return null;
        }
    }
}
