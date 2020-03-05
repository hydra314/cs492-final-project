package com.example.android.githubsearchwithsqlite;

import android.app.Application;

import com.example.android.githubsearchwithsqlite.data.GitHubRepo;
import com.example.android.githubsearchwithsqlite.data.SavedReposRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SavedReposViewModel extends AndroidViewModel {
    private SavedReposRepository mRepository;

    public SavedReposViewModel(Application application) {
        super(application);
        mRepository = new SavedReposRepository(application);
    }

    public void insertSavedRepo(GitHubRepo repo) {
        mRepository.insertSavedRepo(repo);
    }

    public void deleteSavedRepo(GitHubRepo repo) {
        mRepository.deleteSavedRepo(repo);
    }

    public LiveData<List<GitHubRepo>> getAllRepos() {
        return mRepository.getAllRepos();
    }

    public LiveData<GitHubRepo> getRepoByName(String fullName) {
        return mRepository.getRepoByName(fullName);
    }
}
