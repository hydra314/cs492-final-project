package com.example.android.cooking101;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.cooking101.data.Recipes;
import com.example.android.cooking101.data.Status;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BrowseActivity extends AppCompatActivity
    implements RecipeSearchAdapter.OnSearchResultClickListener {
    private static String TAG = BrowseActivity.class.getSimpleName();
    private int tempAPICALLLIMIT = 0;

    private RecipeSearchAdapter mRecipeSearchAdapter;
    private RecipeSearchViewModel mViewModel;
    private RecyclerView browseRecipesRV;
    private TextView mErrorMessageTV;
    private ProgressBar mLoadingIndicatorPB;

    private List<String> mFoodItems = Arrays.asList(
            "Chicken",
            "Pork",
            "Steak",
            "Soup",
            "Bread",
            "Pasta",
            "Pie",
            "Beverage",
            "Vegetarian"
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        browseRecipesRV = findViewById(R.id.rv_browse_recipes);
        browseRecipesRV.setLayoutManager(new LinearLayoutManager(this));
        browseRecipesRV.setHasFixedSize(true);

        mRecipeSearchAdapter = new RecipeSearchAdapter(this);
        browseRecipesRV.setAdapter(mRecipeSearchAdapter);

        mLoadingIndicatorPB = findViewById(R.id.pb_browse_loading_indicator);
        mErrorMessageTV = findViewById(R.id.tv_browse_error_message);

        mViewModel = new ViewModelProvider(this).get(RecipeSearchViewModel.class);

        mViewModel.getSearchResults().observe(this, new Observer<List<Recipes>>() {
            @Override
            public void onChanged(List<Recipes> recipes) {
                mRecipeSearchAdapter.updateSearchResults(recipes);
            }
        });

        mViewModel.getLoadingStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if(status == Status.LOADING) {
                    mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                    mErrorMessageTV.setVisibility(View.INVISIBLE);
                    browseRecipesRV.setVisibility(View.INVISIBLE);
                } else if (status == Status.SUCCESS) {
                    browseRecipesRV.setVisibility(View.VISIBLE);
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mErrorMessageTV.setVisibility(View.INVISIBLE);
                } else {
                    mErrorMessageTV.setVisibility(View.VISIBLE);
                    browseRecipesRV.setVisibility(View.INVISIBLE);
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                }

            }
        });

        if(tempAPICALLLIMIT == 0) {
            tempAPICALLLIMIT = 1;
            Random rand = new Random();
            mViewModel.loadSearchResults(mFoodItems.get(rand.nextInt(mFoodItems.size())));
        }
    }

    @Override
    public void onSearchResultClicked(Recipes recipe) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE, recipe);
        startActivity(intent);
    }
}
