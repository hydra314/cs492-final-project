package com.example.android.githubsearchwithsqlite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BrowseActivity extends AppCompatActivity {
    private static String TAG = BrowseActivity.class.getSimpleName();
    private RecyclerView mRecipeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);


        RecyclerView browseRecipesRV = findViewById(R.id.rv_browse_recipes);
        browseRecipesRV.setLayoutManager(new LinearLayoutManager(this));
        browseRecipesRV.setHasFixedSize(true);
    }

    @Override
    public void onSearchResultClicked(Recipes recipe)
    }

}
