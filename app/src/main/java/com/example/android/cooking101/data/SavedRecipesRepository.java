
package com.example.android.cooking101.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SavedRecipesRepository {
    private SavedRecipesDao mDAO;

    public SavedRecipesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDAO = db.savedRecipesDao();
    }

    public void insertSavedRecipe(Recipes recipe) {
        new InsertAsyncTask(mDAO).execute(recipe);
    }

    public void deleteSavedRecipe(Recipes recipe) {
        new DeleteAsyncTask(mDAO).execute(recipe);
    }

    public LiveData<List<Recipes>> getAllRecipes() {
        return mDAO.getAllRecipes();
    }

    private static class InsertAsyncTask extends AsyncTask<Recipes, Void, Void> {
        private SavedRecipesDao mAsyncTaskDAO;
        InsertAsyncTask(SavedRecipesDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(Recipes... recipes) {
            mAsyncTaskDAO.insert(recipes[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Recipes, Void, Void> {
        private SavedRecipesDao mAsyncTaskDAO;
        DeleteAsyncTask(SavedRecipesDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(Recipes... gitHubRepos) {
            mAsyncTaskDAO.delete(gitHubRepos[0]);
            return null;
        }
    }
}
