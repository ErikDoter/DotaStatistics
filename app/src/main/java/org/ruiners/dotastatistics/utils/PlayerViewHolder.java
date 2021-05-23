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
import org.ruiners.dotastatistics.R;
import org.ruiners.dotastatistics.models.match.AllMatchModel;
import org.ruiners.dotastatistics.models.match.MatchModel;
import org.ruiners.dotastatistics.models.match.Player;

public class PlayerViewHolder extends RecyclerView.ViewHolder {
    private final LinearLayout item;
    private final ImageView heroImage;
    private final TextView player;
    private final TextView kda;
    private final TextView gpm;
    private final TextView xpm;

    public PlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        item = itemView.findViewById(R.id.player_item);
        heroImage = itemView.findViewById(R.id.hero_image_player);
        player = itemView.findViewById(R.id.player);
        gpm = itemView.findViewById(R.id.gpm);
        xpm = itemView.findViewById(R.id.xpm);
        kda = itemView.findViewById(R.id.kda_player);
    }

    public void bind(Player model, int position) {
        if(position % 2 == 0) {
            item.setBackgroundColor(Color.DKGRAY);
        }
        player.setText(model.personaname);
        xpm.setText(model.xp_per_min.toString());
        gpm.setText(model.gold_per_min.toString());
        kda.setText(model.kills.toString() + "/" + model.deaths.toString() + "/" + model.assists.toString());
        try {
            Picasso.get().load("https://steamcdn-a.akamaihd.net" + model.hero.icon).into(heroImage);
        } catch (NullPointerException e) {
            return;
        }
    }

}
