package org.ruiners.dotastatistics.repository;

import android.app.Application;

import androidx.room.Room;

import org.ruiners.dotastatistics.db.AppDatabase;
import org.ruiners.dotastatistics.db.Matches;
import org.ruiners.dotastatistics.db.MatchesDao;

import java.util.List;

public class MatchDbRepository {
    private MatchesDao mMatchesDao;
    private List<Matches> mMatches;
    public MatchDbRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application.getApplicationContext(), AppDatabase.class, "dota_base").allowMainThreadQueries().build();
        mMatchesDao = db.getMatchesDao();
        mMatches = mMatchesDao.getAllMatches();
    }

    public List<Matches> GetAllMatchesDb() {
        return mMatches;
    }


    public void insert(Matches matches) {
        mMatchesDao.insertAll(matches);
    }
}
