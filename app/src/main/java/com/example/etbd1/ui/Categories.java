package com.example.etbd1.ui;

import com.example.etbd1.ui.main.Shops.ShopsModel;

import java.util.ArrayList;

public class Categories {
    private String categoryName;
    private ArrayList<SubCategories>  subCategoriesArrayList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<SubCategories> getSubCategoriesArrayList() {
        return subCategoriesArrayList;
    }

    public void setSubCategoriesArrayList(ArrayList<SubCategories> subCategoriesArrayList) {
        this.subCategoriesArrayList = subCategoriesArrayList;
    }

}
