package com.example.android.cooking101;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.android.cooking101.data.Recipes;

public class RecipeDetailActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE = "Recipe";

    private Recipes mRecipe;
    private boolean mIsSaved = false;
    private SavedRecipesViewModel mViewModel;


    private TextView recipeNameTV;
    private ImageView mRecipeImageTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeNameTV = findViewById(R.id.tv_recipe_name);
        mRecipeImageTV = findViewById(R.id.iv_recipe_image);

        mViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(SavedRecipesViewModel.class);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(EXTRA_RECIPE)) {
            mRecipe = (Recipes)intent.getSerializableExtra(EXTRA_RECIPE);


            fillInLayout(mRecipe);
        }

        final ImageView recipeBookmarkIV = findViewById(R.id.iv_recipe_bookmark);
        recipeBookmarkIV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mRecipe != null) {
                    if(!mIsSaved) {
                        mViewModel.InsertSavedRecipe(mRecipe);
                    } else {
                        mViewModel.deleteSavedRecipe(mRecipe);
                    }
                }
            }
        });

        mViewModel.getRecipeByName(mRecipe.label).observe(this, new Observer<Recipes>() {
            @Override
            public void onChanged(Recipes recipes) {
                if(recipes != null) {
                    mIsSaved = true;
                    recipeBookmarkIV.setImageResource(R.drawable.ic_bookmark_black_24dp);
                } else {
                    mIsSaved = false;
                    recipeBookmarkIV.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                }
            }
        });
    }

    private void fillInLayout(Recipes recipe) {
        recipeNameTV.setText(mRecipe.label);

        Glide.with(this).load(recipe.image).into(mRecipeImageTV);
    }
}
