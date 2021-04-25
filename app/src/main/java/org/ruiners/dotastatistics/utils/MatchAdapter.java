package org.ruiners.dotastatistics.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ruiners.dotastatistics.R;
import org.ruiners.dotastatistics.models.match.MatchModel;

import java.util.ArrayList;

import javax.sql.DataSource;

public class MatchAdapter extends RecyclerView.Adapter<MatchViewHolder> {
    public ArrayList<MatchModel> data;


    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, null, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        if(position != data.size()) {
            MatchModel model = data.get(position);
            holder.bind(model, position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}