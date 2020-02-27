package com.example.android.githubsearchwithsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.githubsearchwithsqlite.data.GitHubRepo;
import com.example.android.githubsearchwithsqlite.utils.GitHubUtils;

public class SavedReposActivity extends AppCompatActivity implements GitHubSearchAdapter.OnSearchResultClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_repos);

        RecyclerView savedReposRV = findViewById(R.id.rv_saved_repos);
        savedReposRV.setLayoutManager(new LinearLayoutManager(this));
        savedReposRV.setHasFixedSize(true);

        GitHubSearchAdapter adapter = new GitHubSearchAdapter(this);
        savedReposRV.setAdapter(adapter);
    }

    @Override
    public void onSearchResultClicked(GitHubRepo repo) {
        Intent intent = new Intent(this, RepoDetailActivity.class);
        intent.putExtra(RepoDetailActivity.EXTRA_GITHUB_REPO, repo);
        startActivity(intent);
    }
}
