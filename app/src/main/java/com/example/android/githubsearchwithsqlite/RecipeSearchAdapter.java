package com.example.android.githubsearchwithsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;
import android.util.Log;
import com.example.android.githubsearchwithsqlite.data.Recipes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeSearchAdapter extends RecyclerView.Adapter<RecipeSearchAdapter.SearchResultViewHolder> {
    private static final String TAG = RecipeSearchAdapter.class.getSimpleName();
    private List<Recipes> mSearchResultsList;
    private OnSearchResultClickListener mResultClickListener;

    interface OnSearchResultClickListener {
        void onSearchResultClicked(Recipes recipe);
    }

    public RecipeSearchAdapter(OnSearchResultClickListener listener) {
        mResultClickListener = listener;
    }

    public void updateSearchResults(List<Recipes> searchResultsLists) {
        mSearchResultsList = searchResultsLists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mSearchResultsList != null) {
            return mSearchResultsList.size();
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_list_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.bind(mSearchResultsList.get(position));
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mSearchResultTV;
        private TextView mSearchResultDescriptionTV;
        private ImageView mRecipeImage;

        SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_recipe_title);
            mRecipeImage = itemView.findViewById(R.id.iv_recipe_image);
            itemView.setOnClickListener(this);
        }

        void bind(Recipes recipe) {
            Log.d(TAG, recipe.label);
            mSearchResultTV.setText(recipe.label);
            /*Glide.with(mRecipeImage.getContext()).load(recipe.image).into(mRecipeImage);*/

            String description = recipe.servings + " Servings | " + recipe.calories + " Calories";
            mSearchResultDescriptionTV.setText(description);
        }

        @Override
        public void onClick(View v) {
            Recipes recipe = mSearchResultsList.get(getAdapterPosition());
            mResultClickListener.onSearchResultClicked(recipe);
        }
    }
}
