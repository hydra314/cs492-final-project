package com.example.android.githubsearchwithsqlite.data;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipes implements Serializable {
    public String label;
    public int calories;
    public String servings;
    public String source;
    public String source_url;
}
