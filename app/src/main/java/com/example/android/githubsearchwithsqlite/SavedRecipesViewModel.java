package com.example.android.githubsearchwithsqlite;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.githubsearchwithsqlite.data.Recipes;
import com.example.android.githubsearchwithsqlite.data.SavedRecipesRepository;

import java.util.List;

public class SavedRecipesViewModel extends AndroidViewModel {
    private SavedRecipesRepository mRepository;

    public SavedRecipesViewModel(Application application) {
        super(application);
        mRepository = new SavedRecipesRepository(application);
    }

    public void InsertSavedRecipe(Recipes recipe) {
        mRepository.insertSavedRecipe(recipe);
    }

    public void deleteSavedRecipe(Recipes recipe) {
        mRepository.deleteSavedRecipe(recipe);
    }

    public LiveData<List<Recipes>> getAllRecipes() {
        return mRepository.getAllRecipes();
    }
}
