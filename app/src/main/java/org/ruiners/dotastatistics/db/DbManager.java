package org.ruiners.dotastatistics.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.ruiners.dotastatistics.models.match.MatchModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class DbManager {
    private static final int VERSION = 1;

    @SuppressLint("StaticFieldLeak")
    private static final DbManager INSTANCE = new DbManager();

    private static final String TEXT_COLUMN = "TEXT_COLUMN";
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String DB_NAME = "MyDatabase.db";

    static DbManager getInstance(Context context) {
        INSTANCE.context = context.getApplicationContext();
        return INSTANCE;
    }

    private final Executor executor = Executors.newSingleThreadExecutor();
    private Context context;
    private SQLiteDatabase database;

    void insert(final String text) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
//                insertInternal(text);
                insertRoom(text);
            }
        });
    }

    void readAll(final ReadAllListener<Matches> listener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                readAllRoom(listener);
//                readAllInternal(listener);
            }
        });
    }

    void clean() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
//                cleanInternal();
                cleanRoom();
            }
        });
    }

    private void cleanRoom() {
        MatchesDao dao = AppDatabase.getInstance(context).getMatchesDao();
        dao.delete(dao.getAllMatches().get(0));
    }

    private void checkInitialized() {
        if (database != null) {
            return;
        }

        SQLiteOpenHelper helper = new SQLiteOpenHelper(context, DB_NAME, null, VERSION +2) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                createDatabase(db);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            }
        };

        database = helper.getWritableDatabase();
    }

    private void createDatabase(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE '" + TABLE_NAME + "' (ID INTEGER PRIMARY KEY, " + TEXT_COLUMN + " TEXT NOT NULL)");
    }

    private void insertInternal(String text) {
        checkInitialized();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TEXT_COLUMN, text);
        long insert = database.insert(TABLE_NAME, null, contentValues);
//        database.execSQL("INSERT INTO " + TABLE_NAME + " (" + TEXT_COLUMN + ") VALUES (?)", new Object[]{text});
    }

    private void insertRoom(String text) {
        Matches matches= new Matches();
        AppDatabase.getInstance(context).getMatchesDao().insertAll(matches);
    }

    private void readAllRoom(final ReadAllListener<Matches> listener) {
        List<Matches> list = AppDatabase.getInstance(context).getMatchesDao().getAllMatches();
        ArrayList<Matches> matchesArrayList = new ArrayList<Matches>();
        for (Matches matches: list) {
            matchesArrayList.add(matches);
        }
        listener.onReadAll(matchesArrayList);
    }

    private void readAllInternal(final ReadAllListener<String> listener) {
        checkInitialized();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor == null) {
            listener.onReadAll(Collections.<String>emptyList());
            return;
        }
        final List<String> result = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                result.add(cursor.getString(cursor.getColumnIndex(TEXT_COLUMN)));
            }
        } finally {
            cursor.close();
        }
        listener.onReadAll(result);
    }

    private void cleanInternal() {
        checkInitialized();
        database.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public interface ReadAllListener<T> {
        void onReadAll(final Collection<T> allItems);
    }
}