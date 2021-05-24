package org.ruiners.dotastatistics.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ruiners.dotastatistics.R;
import org.ruiners.dotastatistics.models.heroes.BestHeroes;
import org.ruiners.dotastatistics.models.match.MatchModel;

import java.util.ArrayList;

import javax.sql.DataSource;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesViewHolder> {
    public ArrayList<BestHeroes> data;


    @NonNull
    @Override
    public HeroesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_item, null, false);
        return new HeroesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesViewHolder holder, int position) {
        if(position != data.size()) {
            BestHeroes model = data.get(position);
            holder.bind(model, position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}