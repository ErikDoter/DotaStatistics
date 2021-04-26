package org.ruiners.dotastatistics.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MatchesDao {

    // Добавление Person в бд
    @Insert
    void insertAll(Matches... matches);

    // Удаление Person из бд
    @Delete
    void delete(Matches matches);

    // Получение всех Person из бд
    @Query("SELECT * FROM matches")
    List<Matches> getAllMatches();

}
