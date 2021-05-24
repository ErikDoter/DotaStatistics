package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.ruiners.dotastatistics.models.heroes.BestHeroes;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.presentation.HeroesPresenterImpl;
import org.ruiners.dotastatistics.presentation.MatchPresenterImpl;
import org.ruiners.dotastatistics.utils.HeroesAdapter;
import org.ruiners.dotastatistics.utils.MatchAdapter;

import java.util.ArrayList;

public class HeroesActivity extends BasicActivity implements HeroesPresenter.View {

    private  HeroesPresenter mHeroesPresenter;
    private String account_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        account_id = getIntent().getExtras().get("account_id").toString();
        mHeroesPresenter = new HeroesPresenterImpl(this);
        mHeroesPresenter.onOpen(account_id);
    }

    public void getBestHeroes(ArrayList<BestHeroes> heroes) {

        RecyclerView recyclerView = findViewById(R.id.all_heroes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HeroesAdapter heroesAdapter = new HeroesAdapter();
        heroesAdapter.data = heroes;
        recyclerView.setAdapter(heroesAdapter);
    }

}