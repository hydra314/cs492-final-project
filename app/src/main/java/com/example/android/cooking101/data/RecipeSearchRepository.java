package com.example.android.cooking101.data;

import android.util.Log;

import com.example.android.cooking101.utils.EdamamUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class RecipeSearchRepository implements RecipeSearchAsyncTask.Callback {
    private static final String TAG = RecipeSearchRepository.class.getSimpleName();
    private MutableLiveData<List<Recipes>> mSearchResults;
    private MutableLiveData<Status> mLoadingStatus;

    private String mCurrentQuery;

    public RecipeSearchRepository() {
        mSearchResults = new MutableLiveData<>();
        mSearchResults.setValue(null);

        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);
    }

    public LiveData<List<Recipes>> getSearchResults() {
        return mSearchResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }

    @Override
    public void onSearchFinished(List<Recipes> searchResults) {
        mSearchResults.setValue(searchResults);
        if (searchResults != null) {
            mLoadingStatus.setValue(Status.SUCCESS);
        } else {
            mLoadingStatus.setValue(Status.ERROR);
        }
    }

    public void loadSearchResults(String query) {
        mCurrentQuery= query;
        String url = EdamamUtils.buildEdamamSearchURL(query);
        mSearchResults.setValue(null);
        Log.d(TAG, "executing search with url: " + url);
        mLoadingStatus.setValue(Status.LOADING);

        new RecipeSearchAsyncTask(this).execute(url);
    }
}
