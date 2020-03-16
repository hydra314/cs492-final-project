package com.example.android.cooking101;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.cooking101.data.Recipes;
import com.example.android.cooking101.data.Status;

import java.util.List;

public class SearchActivity extends AppCompatActivity
        implements RecipeSearchAdapter.OnSearchResultClickListener {
    private static final String Tag = MainActivity.class.getSimpleName();

    private RecyclerView mSearchResultsRV;
    private EditText mSearchBoxET;
    private ProgressBar mLoadingIndicatorPB;
    private TextView mErrorMessageTV;

    private RecipeSearchAdapter mRecipeSearchAdapter;

    private RecipeSearchViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchBoxET = findViewById(R.id.recipe_search_box);
        mSearchResultsRV = findViewById(R.id.rv_search_results);
        mSearchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        mSearchResultsRV.setHasFixedSize(true);

        mRecipeSearchAdapter = new RecipeSearchAdapter(this);
        mSearchResultsRV.setAdapter(mRecipeSearchAdapter);

        mLoadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        mErrorMessageTV = findViewById(R.id.tv_error_message);

        mViewModel = new ViewModelProvider(this).get(RecipeSearchViewModel.class);

        mViewModel.getSearchResults().observe(this, new Observer<List<Recipes>>() {
            @Override
            public void onChanged(List <Recipes> recipes) {
                mRecipeSearchAdapter.updateSearchResults(recipes);
            }
        });

        mViewModel.getLoadingStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status == Status.LOADING) {
                    mLoadingIndicatorPB.setVisibility(View.VISIBLE);
                } else if (status == Status.SUCCESS) {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mSearchResultsRV.setVisibility(View.VISIBLE);
                    mErrorMessageTV.setVisibility(View.INVISIBLE);
                } else {
                    mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
                    mSearchResultsRV.setVisibility(View.INVISIBLE);
                    mErrorMessageTV.setVisibility(View.VISIBLE);
                }
            }
        });

        Button searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = mSearchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    doRecipeSearch(searchQuery);
                }
            }
        });
    }

    @Override
    public void onSearchResultClicked(Recipes recipe) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE, recipe);
        startActivity(intent);
    }

    private void doRecipeSearch(String searchQuery) {
        mViewModel.loadSearchResults(searchQuery);
    }
}