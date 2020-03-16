package com.example.android.cooking101;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.cooking101.data.Recipes;

import java.util.List;

public class BrowseActivity extends AppCompatActivity
    implements RecipeSearchAdapter.OnSearchResultClickListener {
    private static String TAG = BrowseActivity.class.getSimpleName();
    private int tempAPICALLLIMIT = 0;

    private RecipeSearchAdapter mRecipeSearchAdapter;
    private RecipeSearchViewModel mViewModel;
    private RecyclerView browseRecipesRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        browseRecipesRV = findViewById(R.id.rv_browse_recipes);
        browseRecipesRV.setLayoutManager(new LinearLayoutManager(this));
        browseRecipesRV.setHasFixedSize(true);

        mRecipeSearchAdapter = new RecipeSearchAdapter(this);
        browseRecipesRV.setAdapter(mRecipeSearchAdapter);

        mViewModel = new ViewModelProvider(this).get(RecipeSearchViewModel.class);

        mViewModel.getSearchResults().observe(this, new Observer<List<Recipes>>() {
            @Override
            public void onChanged(List<Recipes> recipes) {
                mRecipeSearchAdapter.updateSearchResults(recipes);
            }
        });

        if(tempAPICALLLIMIT == 0) {
            tempAPICALLLIMIT = 1;
            mViewModel.loadSearchResults("chicken");
        }

    }

    @Override
    public void onSearchResultClicked(Recipes recipe) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE, recipe);
        startActivity(intent);
    }


}
