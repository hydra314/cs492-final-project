package com.example.android.githubsearchwithsqlite.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GitHubRepo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SavedReposDao savedReposDao();
    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "github_repos_db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
