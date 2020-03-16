package com.example.android.githubsearchwithsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.githubsearchwithsqlite.data.Recipes;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements RecipeSearchAdapter.OnSearchResultClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SavedRecipesViewModel mViewModel;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        RecyclerView savedRecipesRV = findViewById(R.id.rv_saved_recipes);
        savedRecipesRV.setLayoutManager(new LinearLayoutManager(this));
        savedRecipesRV.setHasFixedSize(true);

        final RecipeSearchAdapter adapter = new RecipeSearchAdapter(this);
        savedRecipesRV.setAdapter(adapter);

        mViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(SavedRecipesViewModel.class);

        mViewModel.getAllRecipes().observe(this, new Observer<List<Recipes>>() {
            @Override
            public void onChanged(List<Recipes> recipes) {
                adapter.updateSearchResults(recipes);
            }
        });

        mDrawerLayout = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nv_nav_drawer);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nav_saved_recipes:
                return true;
            case R.id.nav_browse:
                Intent browseRecipesIntent = new Intent(this, BrowseActivity.class);
                startActivity(browseRecipesIntent);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onSearchResultClicked(Recipes recipe) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE, recipe);
        startActivity(intent);
    }
}
