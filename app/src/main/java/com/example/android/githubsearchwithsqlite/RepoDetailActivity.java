package com.example.android.githubsearchwithsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.githubsearchwithsqlite.data.GitHubRepo;

import java.util.List;

public class RepoDetailActivity extends AppCompatActivity {
    public static final String EXTRA_GITHUB_REPO = "GitHubRepo";

    private GitHubRepo mRepo;
    private boolean mIsSaved = false;

    private SavedReposViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        mViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(SavedReposViewModel.class);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_GITHUB_REPO)) {
            mRepo = (GitHubRepo)intent.getSerializableExtra(EXTRA_GITHUB_REPO);

            TextView repoNameTV = findViewById(R.id.tv_repo_name);
            repoNameTV.setText(mRepo.full_name);

            TextView repoDescriptionTV = findViewById(R.id.tv_repo_description);
            repoDescriptionTV.setText(mRepo.description);

            TextView repoStarsTV = findViewById(R.id.tv_repo_stars);
            repoStarsTV.setText(Integer.toString(mRepo.stargazers_count));
        }

        final ImageView repoBookmarkIV = findViewById(R.id.iv_repo_bookmark);
        repoBookmarkIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRepo != null) {
                    mIsSaved = !mIsSaved;
                    if (mIsSaved) {
                        repoBookmarkIV.setImageResource(R.drawable.ic_bookmark_black_24dp);
                        mViewModel.insertSavedRepo(mRepo);
                    } else {
                        repoBookmarkIV.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                        mViewModel.deleteSavedRepo(mRepo);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_repo:
                viewRepoOnWeb();
                return true;
            case R.id.action_share:
                shareRepo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void viewRepoOnWeb() {
        if (mRepo != null) {
            Uri repoUri = Uri.parse(mRepo.html_url);
            Intent webIntent = new Intent(Intent.ACTION_VIEW, repoUri);

            PackageManager pm = getPackageManager();
            List<ResolveInfo> activities = pm.queryIntentActivities(webIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (activities.size() > 0) {
                startActivity(webIntent);
            }
        }
    }

    private void shareRepo() {
        if (mRepo != null) {
            String shareText = getString(R.string.share_repo_text, mRepo.full_name, mRepo.html_url);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            shareIntent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(shareIntent, null);
            startActivity(chooserIntent);
        }
    }
}
