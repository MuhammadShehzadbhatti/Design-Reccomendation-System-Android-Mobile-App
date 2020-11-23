package com.example.etbd1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.etbd1.ui.main.Products.AllProducts;

import java.util.ArrayList;

public class ProductsAdapterShop extends RecyclerView.Adapter<ProductsAdapterShop.ProductsVHforCardView> {
    ArrayList<AllProducts> productsList;
    Context context;

    public ProductsAdapterShop(ArrayList<AllProducts> productsList, Context context){
        this.productsList = productsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsVHforCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_cardview, parent, false);
        ProductsVHforCardView productsVHforCardView = new ProductsVHforCardView(view);
        return productsVHforCardView;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsVHforCardView holder, int position) {
        AllProducts currentProduct = productsList.get(position);
        holder.shopCarViewProductImage.setImageResource(currentProduct.getProductImage());
        holder.shopCardViewProductName.setText(currentProduct.getProductName());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static class ProductsVHforCardView extends RecyclerView.ViewHolder{
        ImageView shopCarViewProductImage;
        TextView shopCardViewProductName;

        public ProductsVHforCardView(@NonNull View itemView) {
            super(itemView);
            shopCarViewProductImage = itemView.findViewById(R.id.productImage);
            shopCardViewProductName = itemView.findViewById(R.id.productName);
        }
    }

}
