package org.ruiners.dotastatistics.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.ruiners.dotastatistics.R;
import org.ruiners.dotastatistics.models.match.MatchModel;

public class MatchViewHolder extends RecyclerView.ViewHolder {
    private final LinearLayout item;
    private final ImageView heroImage;
    private final TextView mode;
    private final TextView result;
    private final TextView length;
    private final TextView kda;

    public MatchViewHolder(@NonNull View itemView) {
        super(itemView);
        item = itemView.findViewById(R.id.item);
        heroImage = itemView.findViewById(R.id.hero_image);
        mode = itemView.findViewById(R.id.mode);
        result = itemView.findViewById(R.id.result);
        length = itemView.findViewById(R.id.length);
        kda = itemView.findViewById(R.id.kda);
    }

    public void bind(MatchModel model, int position) {
        if(position % 2 == 0) {
            item.setBackgroundColor(Color.DKGRAY);
        }
        mode.setText(model.mode);

        if(model.win) {
            result.setText("Win");
            result.setTextColor(Color.GREEN);
        } else {
            result.setText("Lose");
            result.setTextColor(Color.RED);
        }
        Integer min = model.duration / 60;
        Integer sec = model.duration % 60;
        if (sec / 10 == 0) {
            length.setText(min.toString() + ":" + "0" + sec.toString());
        } else {
            length.setText(min.toString() + ":" + sec.toString());
        }
        kda.setText(model.kills.toString() + "/" + model.deaths.toString() + "/" + model.assists.toString());
        try {
            Picasso.get().load("https://steamcdn-a.akamaihd.net" + model.hero.icon).into(heroImage);
        } catch (NullPointerException e) {
            return;
        }
    }
}