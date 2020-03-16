package com.example.android.githubsearchwithsqlite.utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.githubsearchwithsqlite.data.Recipes;
import com.google.gson.Gson;

import java.net.URI;
import java.util.ArrayList;

public class EdamamUtils {
    private static final String TAG = EdamamUtils.class.getSimpleName();
    private final static String EDAMAM_SEARCH_BASE_URL = "https://api.edamam.com/search";
    private final static String EDAMAM_SEARCH_QUERY_PARAM = "q";
    private final static String EDAMAM_SEARCH_MAX_INGREDIENTS_PARM = "ingr";
    private final static String EDAMAM_SEARCH_DISH_TYPE_PARAM = "dishType";
    private final static String EDAMAM_SEARCH_DIET_TYPE_PARAM = "diet";
    private final static String EDAMAM_SEARCH_MEAL_TYPE_PARAM = "mealType";
    private final static String EDAMAM_APPID_PARAM = "app_id";
    private final static String EDAMAM_APPKEY_PARAM = "app_key";

    private final static String EDAMAM_APPID = "72ee8eeb";
    private final static String EDAMAM_APPKEY = "16f13bf0d0e9ca91df9660f95142b7c9";

    static class RecipeResults {
        RecipeListItem[] hits;
    }

    static class RecipeListItem {
        RecipeDetails recipe;
    }

    static class RecipeDetails {
        String label;
        String image;
        String source;
        String source_url;
        double calories;
    }

    public static String buildEdamamSearchURL(String q) {
        return Uri.parse(EDAMAM_SEARCH_BASE_URL).buildUpon()
                .appendQueryParameter(EDAMAM_APPID_PARAM, EDAMAM_APPID)
                .appendQueryParameter(EDAMAM_APPKEY_PARAM, EDAMAM_APPKEY)
                .appendQueryParameter(EDAMAM_SEARCH_QUERY_PARAM, q)
                .build()
                .toString();
    }

    public static ArrayList<Recipes> parseRecipeSearchResults(String json) {
        Gson gson = new Gson();
        RecipeResults results = gson.fromJson(json, RecipeResults.class);
        if (results != null && results.hits != null) {
            ArrayList<Recipes> recipeItems = new ArrayList<>();

            for(RecipeListItem listItem : results.hits) {
                Recipes recipe = new Recipes();
                recipe.label = listItem.recipe.label;
                recipe.image = listItem.recipe.image;
                recipe.calories = (int)listItem.recipe.calories;
                recipe.source = listItem.recipe.source;
                recipe.source_url = listItem.recipe.source_url;

                recipeItems.add(recipe);
            }

            return recipeItems;

        } else {
            return null;
        }
    }
}


