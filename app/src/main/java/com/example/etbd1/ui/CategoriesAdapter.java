package com.example.etbd1.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.etbd1.R;
import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryVhCardView> {
    private ArrayList<Categories> categoriesArrayList;
    private Categories newCategories;
    private Context context;

    public CategoriesAdapter(ArrayList<Categories> categoriesArrayList, Context context) {
        this.categoriesArrayList = categoriesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryVhCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_cartview,parent,false);
        CategoryVhCardView categoryVhCardView= new CategoryVhCardView(view);

        return categoryVhCardView;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVhCardView holder, int position) {

        newCategories=categoriesArrayList.get(position);
        holder.categoryName.setText(newCategories.getCategoryName());
        //holder.availableShops.setText(newCategories.getSubCategoriesArrayList().size());// SubCategories are treated as shops
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    public class CategoryVhCardView extends RecyclerView.ViewHolder {
        ImageView categoryImageView;
        TextView categoryName,availableShops;


        public CategoryVhCardView(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.txtNameCategory1);
            availableShops = itemView.findViewById(R.id.txtShopsInCategory1);
        }
    }
}
