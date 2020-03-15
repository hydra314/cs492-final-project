package com.example.android.githubsearchwithsqlite.utils;

import android.net.Uri;

import com.example.android.githubsearchwithsqlite.data.Recipes;
import com.google.gson.Gson;

import java.net.URI;
import java.util.ArrayList;

public class EdamamUtils {
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
        ArrayList<Recipes> items;
    }

    public static String buildEdamamSearchURL(String q) {
        return Uri.parse(EDAMAM_SEARCH_BASE_URL).buildUpon()
                .appendQueryParameter(EDAMAM_APPID_PARAM, EDAMAM_APPID)
                .appendQueryParameter(EDAMAM_APPKEY_PARAM, EDAMAM_APPKEY)
                .build()
                .toString();
    }

    public static ArrayList<Recipes> parseRecipeSearchResults(String json) {
        Gson gson = new Gson();
        RecipeResults results = gson.fromJson(json, RecipeResults.class);
        if (results != null && results.items != null) {
            return results.items;
        } else {
            return null;
        }
    }
}


