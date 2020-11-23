package com.example.etbd1.ui;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataSorting {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Categories> categoriesArrayList = new ArrayList<>();
    private ArrayList<SubCategories> subCategoriesArrayList;
    private Context context;
    private Categories categories;
    private SubCategories subCategories;
    public DataSorting(Context context) {
        this.context=context;
    }
    public void dbConnection(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("data");
        findCategories();
    }

    private void findCategories() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    Categories categories_1= dataSnapshot.getValue(Categories.class);
                    Log.e("DataSorting: ",categories_1.getCategoryName()+"");
                }
                /*for (DataSnapshot categoryDSS:snapshot.getChildren()){
                    subCategoriesArrayList =new ArrayList<>();
                    categories = new Categories();
                    String categoryName=categoryDSS.getKey().toString();
                    categories.setCategoryName(categoryName);

                    categoriesArrayList.add(categories);
                }*/
                Log.e("DataSorting: ",categoriesArrayList.size()+"");
                //addSubCategories(categoriesArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private ArrayList<Categories> addSubCategories(final ArrayList<Categories> categoriesArrayList) {

        for (final Categories categories:categoriesArrayList){
            databaseReference = databaseReference.child(categories.getCategoryName());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    subCategoriesArrayList = new ArrayList<>();
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        subCategories = new SubCategories();
                        subCategories.setSubcategoryName(dataSnapshot.getKey());
                        subCategoriesArrayList.add(subCategories);
                    }
                    categories.setSubCategoriesArrayList(subCategoriesArrayList);
                    Log.e("Subcategories: ",categories.getSubCategoriesArrayList().size()+"");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        return  categoriesArrayList;
    }


    public Context getContext() {
        return context;
    }
}
/*

                    if (!categoryName.equals("")){
                            DatabaseReference databaseReference1=databaseReference.child(categoryName);
                            String dataSnapshot1 = databaseReference.child(categoryName).getKey();
                            databaseReference1.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot snapshot) {
        for (DataSnapshot dataSnapshot1:snapshot.getValue()){
        subCategories = new SubCategories();
        subCategories.setSubcategoryName(dataSnapshot1.getKey());
        subCategoriesArrayList.add(subCategories);
        }
        categories.setSubCategoriesArrayList(subCategoriesArrayList);
        }

@Override
public void onCancelled(@NonNull DatabaseError error) {

        }
        });
        }*/
