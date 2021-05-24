package org.ruiners.dotastatistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.presentation.MatchesPresenterImpl;
import org.ruiners.dotastatistics.utils.MatchAdapter;

import java.util.ArrayList;

public class MatchesActivity extends BasicActivity implements MatchesPresenter.View {
    private MatchesPresenter matchesPresenter;
    private String account_id;
    private String hero_id;
    private Integer page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        Intent intent = new Intent(this, MatchesActivity.class);
        matchesPresenter = new MatchesPresenterImpl(this);
        account_id = getIntent().getExtras().get("account_id").toString();
        try{
            hero_id = getIntent().getExtras().get("hero_id").toString();
        } catch (Exception e) {
            hero_id = "";
        }
        page = (Integer) getIntent().getExtras().get("page");
        matchesPresenter.onLoad(account_id, page, hero_id);
        Button next = findViewById(R.id.next);
        Button prev = findViewById(R.id.prev);
        if(page == 1) {
            prev.setVisibility(View.INVISIBLE);
        }
        TextView text = findViewById(R.id.page);
        text.setText(page.toString());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("account_id", account_id);
                intent.putExtra("hero_id", hero_id);
                intent.putExtra("page", page + 1);
                startActivity(intent);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("account_id", account_id);
                intent.putExtra("hero_id", hero_id);
                intent.putExtra("page", page - 1);
                startActivity(intent);
            }
        });
    }


    public void showMatches(ArrayList<MatchModel> matches){
        RecyclerView recyclerView = findViewById(R.id.all_matches);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MatchAdapter matchAdapter = new MatchAdapter();
        matchAdapter.data = matches;
        recyclerView.setAdapter(matchAdapter);
    }
}