package org.ruiners.dotastatistics.utils;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.ruiners.dotastatistics.BasicActivity;
import org.ruiners.dotastatistics.MatchesActivity;
import org.ruiners.dotastatistics.ProfileActivity;
import org.ruiners.dotastatistics.R;
import org.ruiners.dotastatistics.models.heroes.BestHeroes;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.profile.Profile;

public class HeroesViewHolder extends RecyclerView.ViewHolder {
    private final LinearLayout item;
    private final ImageView heroImage;
    private final TextView name;
    private final TextView matches;
    private final TextView win_rate;

    public HeroesViewHolder(@NonNull View itemView) {
        super(itemView);
        item = itemView.findViewById(R.id.item);
        heroImage = itemView.findViewById(R.id.hero_image1);
        name = itemView.findViewById(R.id.hero_name);
        matches = itemView.findViewById(R.id.mathes);
        win_rate = itemView.findViewById(R.id.win_rate);
    }

    public void bind(BestHeroes model, int position) {
        if(position % 2 == 0) {
            item.setBackgroundColor(Color.DKGRAY);
        }
        double winrate = ((double)model.win / (double)model.games) * 100;
        win_rate.setText(String.format("%,.2f", winrate)+"%");
        if(winrate >= 50) {
            win_rate.setTextColor(Color.GREEN);
        } else {
            win_rate.setTextColor(Color.RED);
        }
        name.setText(model.hero.localized_name);
        matches.setText(model.games.toString());

        try {
            Picasso.get().load("https://steamcdn-a.akamaihd.net" + model.hero.icon).into(heroImage);
        } catch (NullPointerException e) {
            return;
        }
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasicActivity activity;
                try {
                    activity = (BasicActivity) item.getContext();
                } catch (ClassCastException e) {
                    return;
                }
                activity.showAllMatches(model);
            }
        });
    }
}