package com.example.android.cooking101;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.cooking101.data.Recipes;

public class RecipeDetailActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE = "Recipe";

    private Recipes mRecipe;
    private boolean mIsSaved = false;

    private SavedRecipesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(SavedRecipesViewModel.class);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(EXTRA_RECIPE)) {
            mRecipe = (Recipes)intent.getSerializableExtra(EXTRA_RECIPE);

            TextView recipeNameTV = findViewById(R.id.tv_recipe_name);
            recipeNameTV.setText(mRecipe.label);
        }

    }


}
