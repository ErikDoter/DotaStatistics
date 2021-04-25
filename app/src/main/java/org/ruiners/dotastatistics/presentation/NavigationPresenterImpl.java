package org.ruiners.dotastatistics.presentation;

import android.content.Intent;
import android.content.SharedPreferences;

import org.ruiners.dotastatistics.BasicActivity;
import org.ruiners.dotastatistics.MainActivity;
import org.ruiners.dotastatistics.MatchesActivity;
import org.ruiners.dotastatistics.NavigationPresenter;
import org.ruiners.dotastatistics.ProfileActivity;

public class NavigationPresenterImpl implements NavigationPresenter {
    BasicActivity mView;
    private final static String KEY_AUTH_CHECK = "auth_check";
    private final static String KEY_ID = "id";

    public NavigationPresenterImpl(BasicActivity view) {
        mView = view;
    }

    @Override
    public void onLogoutClicked() {
        SharedPreferences.Editor editor = mView.getSharedPreferences("Settings", mView.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_AUTH_CHECK, false);
        editor.apply();
        Intent intent = new Intent(mView, MainActivity.class);
        mView.startActivity(intent);
    }

    @Override
    public void onProfileClicked() {
        SharedPreferences settings = mView.getSharedPreferences("Settings", mView.MODE_PRIVATE);
        Intent intent = new Intent(mView, ProfileActivity.class);
        String id32 = settings.getString(KEY_ID, "0");
        intent.putExtra("account_id", id32);
        mView.startActivity(intent);
    }

    @Override
    public void onMatchesClicked() {
        SharedPreferences settings = mView.getSharedPreferences("Settings", mView.MODE_PRIVATE);
        Intent intent = new Intent(mView, MatchesActivity.class);
        String id32 = settings.getString(KEY_ID, "0");
        intent.putExtra("account_id", id32);
        intent.putExtra("page", 1);
        mView.startActivity(intent);
    }

    @Override
    public void onFavoritesClicked() {
    }
}
