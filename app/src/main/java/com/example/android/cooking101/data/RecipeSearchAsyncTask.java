package com.example.android.cooking101.data;

import android.os.AsyncTask;

import com.example.android.cooking101.utils.EdamamUtils;
import com.example.android.cooking101.utils.NetworkUtils;

import java.io.IOException;
import java.util.List;

public class RecipeSearchAsyncTask extends AsyncTask<String, Void, String> {
    private Callback mCallback;

    public interface Callback {
        void onSearchFinished(List<Recipes> searchResults);
    }

    public RecipeSearchAsyncTask(Callback callback) {
        mCallback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String searchResults = null;
        try {
            searchResults = NetworkUtils.doHttpGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    @Override
    protected void onPostExecute(String s) {
        List<Recipes> searchResults = null;
        if (s != null) {
            searchResults = EdamamUtils.parseRecipeSearchResults(s);
        }
        mCallback.onSearchFinished(searchResults);
    }
}
