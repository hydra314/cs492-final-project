package com.example.android.cooking101;

import com.example.android.cooking101.data.Recipes;
import com.example.android.cooking101.data.RecipeSearchRepository;
import com.example.android.cooking101.data.Status;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class RecipeSearchViewModel extends ViewModel {
    private RecipeSearchRepository mRepository;
    private LiveData<List<Recipes>> mSearchResults;
    private LiveData<Status> mLoadingStatus;

    public RecipeSearchViewModel() {
        mRepository = new RecipeSearchRepository();
        mSearchResults = mRepository.getSearchResults();
        mLoadingStatus = mRepository.getLoadingStatus();
    }

    public void loadSearchResults(String query) {
        mRepository.loadSearchResults(query);
    }

    public LiveData<List<Recipes>> getSearchResults() {
        return mSearchResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

}
