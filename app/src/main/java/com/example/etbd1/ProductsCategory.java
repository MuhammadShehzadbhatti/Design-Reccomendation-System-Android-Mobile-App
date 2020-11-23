package com.example.etbd1;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.etbd1.ui.Categories;
import com.example.etbd1.ui.CategoriesAdapter;
import com.example.etbd1.ui.DataSorting;
import com.example.etbd1.ui.SubCategories;
import com.example.etbd1.ui.main.Products.AllProducts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsCategory extends Fragment implements HomeProductsAdapter.HomeProductAdapterEvent {
    private AllProducts allProducts;
    private ImageView productImageView;
    private TextView txtProductName,txtProductOriginalPrice, txtProductDiscountPrice, txtProductDescription;
    private Button btnGoToShop;
    private ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    private ArrayList<SubCategories> subCategoriesArrayList = new ArrayList<>();
    private FirebaseDatabase database;
    RecyclerView recyclerViewCategories;
    RecyclerView.LayoutManager layoutManagerCategories;
    RecyclerView.Adapter categoriesAdapter;
    private DatabaseReference myRef;
    SubCategories subCategories;
    HashMap<Categories,ArrayList<SubCategories>> categoriesArrayListHashMap;
    DataSorting dataSorting;
    int numberOfCategory;
    public ProductsCategory(ArrayList<Categories> categoriesArrayList) {
        // Required empty public constructor
        this.categoriesArrayList = categoriesArrayList;
    }
    public ProductsCategory(){
        dataSorting= new DataSorting(getContext());
        dataSorting.dbConnection();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate( R.layout.fragment_products_categories, container, false);

        categoriesArrayListHashMap =new HashMap<>();
        int shopLogoUrl = R.drawable.cat_img1;
        Log.e("size",categoriesArrayList+"");

        numberOfCategory = categoriesArrayList.size();

        recyclerViewCategories = view.findViewById(R.id.categoriesRecyclerView);
        recyclerViewCategories.setHasFixedSize(true);
        layoutManagerCategories = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        categoriesAdapter = new CategoriesAdapter(categoriesArrayList,getContext());
        recyclerViewCategories.setLayoutManager(layoutManagerCategories);
        recyclerViewCategories.setAdapter(categoriesAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private Categories addSubCategory(Categories categories, ArrayList<SubCategories> subCategoriesArrayList) {

        categories.setSubCategoriesArrayList(subCategoriesArrayList);

        return categories;
    }

    private void getCategoriesAndSubCategories() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("data");
        //Log.e("getAllCategories.....: ",categoriesArrayList+"");
        for (final Categories categories: categoriesArrayList){
            subCategoriesArrayList = new ArrayList<>();
            Log.e("getAllCategories.....: ",categories.getCategoryName()+"");
            myRef = myRef.child(categories.getCategoryName());
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        //Log.e("categories.....: ", finalCategory.getCategoryName()+"");
                        subCategories = new SubCategories();
                        subCategories.setSubcategoryName(dataSnapshot.getKey());
                        if (subCategoriesArrayList.contains(subCategories)){
                            subCategoriesArrayList.add(subCategories);
                        }

                    }
                    categories.setSubCategoriesArrayList(subCategoriesArrayList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

   /* private void getIncomings() {
        productImageView.setImageResource(getActivity().getIntent().getExtras().getInt("getProductImage"));
        txtProductName.setText(getActivity().getIntent().getExtras().getString("getProductName"));
    }*/
/*
    @Override
    public void sendObject(AllProducts currentProductItems) {
        this.allProducts = currentProductItems;
        productImageView.setImageResource(allProducts.getProductImage());
        txtProductName.setText(allProducts.getProductName());
    }*/

    @Override
    public void onItemClick(AllProducts allProducts) {
        this.allProducts = allProducts;
    }

    /*@Override
    public ArrayList<Categories> getCategories() {
        return categoriesArrayList;
    }

    @Override
    public void setCategories(Categories categories) {
        if (!categoriesArrayList.isEmpty() && !categoriesArrayList.contains(categories)) {
            categoriesArrayList.add(categories);
            for (Categories category: categoriesArrayList){
                Log.e("categories True: ", categories.getCategoryName() + "");
            }
        }
        else {

            categoriesArrayList.add(categories);
            for (Categories category: categoriesArrayList){
                Log.e("categories True: ", categories.getCategoryName() + "");
            }
        }
    }*/
}
