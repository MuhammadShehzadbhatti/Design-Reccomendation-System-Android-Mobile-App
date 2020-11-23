package com.example.etbd1.ui.main;

import com.example.etbd1.ui.Categories;
import com.example.etbd1.ui.SubCategories;

import java.util.ArrayList;

public interface DataCollectionInterface {
    public ArrayList<Categories> getCategories();
    public void setCategories(Categories categories);
    //public ArrayList<SubCategories> getSubCategories();
    public ArrayList<Categories> getCategoriesSubCategories();
}
