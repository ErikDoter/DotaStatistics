package org.ruiners.dotastatistics.repository;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.ruiners.dotastatistics.ProfileRepository;
import org.ruiners.dotastatistics.models.profile.ProfileModel;
import org.ruiners.dotastatistics.models.profile.Wl;

import java.io.IOException;

public class ProfileRepositoryImpl implements ProfileRepository {
    @Override
    public ProfileModel getProfile(String id) {
        String URL = "https://api.opendota.com/api/players/" + id;
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.Get(URL);
        String response = httpUtil.answer;
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ProfileModel> jsonAdapter = moshi.adapter(ProfileModel.class);
        ProfileModel profileModel;
        try {
             profileModel = jsonAdapter.fromJson(response);
        } catch (IOException e) {
            return new ProfileModel();
        }
        HttpUtil http = new HttpUtil();
        URL = "https://api.opendota.com/api/players/" + id + "/wl";
        http.Get(URL);
        String resp = http.answer;
        Moshi m = new Moshi.Builder().build();
        JsonAdapter<Wl> adapter = m.adapter(Wl.class);
        try {
            Wl wl = adapter.fromJson(resp);
            profileModel.win_lose = wl;
        } catch (IOException e) {
            return new ProfileModel();
        }
        return profileModel;
    }

}
