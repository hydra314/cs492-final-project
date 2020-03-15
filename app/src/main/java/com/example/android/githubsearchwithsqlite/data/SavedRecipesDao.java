package com.example.android.githubsearchwithsqlite.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedRecipesDao {
    @Insert
    void insert(Recipes recipe);

    @Delete
    void delete(Recipes recipe);

    @Query("SELECT * FROM recipes")
    LiveData<List<Recipes>> getAllRecipes();

}
