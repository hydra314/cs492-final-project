package com.example.android.githubsearchwithsqlite.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface SavedReposDao {
    @Insert
    void insert(GitHubRepo repo);

    @Delete
    void delete(GitHubRepo repo);
}
