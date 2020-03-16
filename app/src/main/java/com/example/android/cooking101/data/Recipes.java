package com.example.android.cooking101.data;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipes implements Serializable {
    @NonNull
    @PrimaryKey
    public String label;

    public int calories;
    public String servings;
    public String source;
    public String source_url;

    public String image; 
}
