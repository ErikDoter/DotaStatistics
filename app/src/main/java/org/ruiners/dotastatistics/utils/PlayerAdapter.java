package org.ruiners.dotastatistics.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.ruiners.dotastatistics.R;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.match.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerViewHolder> {
    public List<Player> data;



    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item, null, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        if(position != data.size()) {
            Player model = data.get(position);
            holder.bind(model, position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
