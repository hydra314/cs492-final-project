package com.example.android.githubsearchwithsqlite.data;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "repos")
public class GitHubRepo implements Serializable {
    @PrimaryKey
    @NonNull
    public String full_name;

//    @ColumnInfo(name = "url")
    public String html_url;

    public String description;
    public int stargazers_count;
}
