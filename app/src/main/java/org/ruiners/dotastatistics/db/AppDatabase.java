package org.ruiners.dotastatistics.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Matches.class /* AThirdEntityType.class */}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MatchesDao getMatchesDao();

    private static AppDatabase instance;

    static synchronized AppDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }


    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "dota_base")
                .build();
    }
}
