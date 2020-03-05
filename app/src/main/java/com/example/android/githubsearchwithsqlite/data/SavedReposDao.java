package com.example.android.githubsearchwithsqlite.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SavedReposDao {
    @Insert
    void insert(GitHubRepo repo);

    @Delete
    void delete(GitHubRepo repo);

    @Query("SELECT * FROM repos")
    LiveData<List<GitHubRepo>> getAllRepos();

    @Query("SELECT * FROM repos WHERE full_name = :fullName LIMIT 1")
    LiveData<GitHubRepo> getRepoByName(String fullName);
}
