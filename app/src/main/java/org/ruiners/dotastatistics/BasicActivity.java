package org.ruiners.dotastatistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.ruiners.dotastatistics.models.heroes.BestHeroes;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.presentation.NavigationPresenterImpl;

public class BasicActivity extends AppCompatActivity {
    private NavigationPresenter presenter;
    private final static String KEY_AUTH_CHECK = "auth_check";
    private final static String KEY_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = new NavigationPresenterImpl(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                presenter.onLogoutClicked();
                break;
            case R.id.AllMatches:
                presenter.onMatchesClicked();
                break;
            case R.id.profile:
                presenter.onProfileClicked();
                break;
            case R.id.Heroes:
                presenter.onHeroesClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMatch(MatchModel model) {
        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
        Intent intent = new Intent(this, MatchActivity.class);
        String id32 = settings.getString(KEY_ID, "0");
        intent.putExtra("account_id", id32);
        intent.putExtra("match", model.match_id);
        startActivity(intent);
    }

    public void showAllMatches(BestHeroes model) {
        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
        Intent intent = new Intent(this, MatchesActivity.class);
        String id32 = settings.getString(KEY_ID, "0");
        intent.putExtra("account_id", id32);
        Log.d("hero_id", model.hero_id);
        intent.putExtra("hero_id", model.hero_id);
        intent.putExtra("page", 1);
        startActivity(intent);
    }
}