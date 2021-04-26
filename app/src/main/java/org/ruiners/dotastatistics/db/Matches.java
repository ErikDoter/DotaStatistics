package org.ruiners.dotastatistics.db;


import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Matches {
    @PrimaryKey
    @NonNull
    public long match_id;
    String mode;
    Boolean win;
    Integer duration;
    Integer kills;
    Integer deaths;
    Integer assists;
    //Bitmap hero_image;
}