package com.example.android.githubsearchwithsqlite.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SavedReposRepository {
    private SavedReposDao mDAO;

    public SavedReposRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDAO = db.savedReposDao();
    }

    public void insertSavedRepo(GitHubRepo repo) {
        new InsertAsyncTask(mDAO).execute(repo);
    }

    public void deleteSavedRepo(GitHubRepo repo) {
        new DeleteAsyncTask(mDAO).execute(repo);
    }

    public LiveData<List<GitHubRepo>> getAllRepos() {
        return mDAO.getAllRepos();
    }

    public LiveData<GitHubRepo> getRepoByName(String fullName) {
        return mDAO.getRepoByName(fullName);
    }

    private static class InsertAsyncTask extends AsyncTask<GitHubRepo, Void, Void> {
        private SavedReposDao mAsyncTaskDAO;
        InsertAsyncTask(SavedReposDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(GitHubRepo... gitHubRepos) {
            mAsyncTaskDAO.insert(gitHubRepos[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<GitHubRepo, Void, Void> {
        private SavedReposDao mAsyncTaskDAO;
        DeleteAsyncTask(SavedReposDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(GitHubRepo... gitHubRepos) {
            mAsyncTaskDAO.delete(gitHubRepos[0]);
            return null;
        }
    }
}
