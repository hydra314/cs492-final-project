package com.example.android.githubsearchwithsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import com.example.android.githubsearchwithsqlite.data.Recipes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeSearchAdapter extends RecyclerView.Adapter<RecipeSearchAdapter.SearchResultViewHolder> {
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

    class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mSearchResultTV;

        SearchResultViewHolder(View itemView) {
            super(itemView);
            mSearchResultTV = itemView.findViewById(R.id.tv_recipe_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mResultClickListener.onSearchResultClicked(
                            mSearchResultsList.get(getAdapterPosition())
                    );
                }
            });
        }

        void bind(Recipes recipe) {
            mSearchResultTV.setText(recipe.label);
        }
    }

}
