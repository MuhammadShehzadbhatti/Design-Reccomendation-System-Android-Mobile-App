package com.example.etbd1.ui.main.Shops;

import android.net.Uri;

import com.example.etbd1.ui.main.Products.AllProducts;

import java.util.ArrayList;

public class ShopsModel {
    private String shopName;
    private String shopDescription;
    double lantitude, longitude;
    int shopID;
    Uri ShopLogoUrl;
    private ArrayList<AllProducts> allProductsArrayList;

    public ShopsModel() {
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public void setLantitude(double lantitude) {
        this.lantitude = lantitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public Uri getShopLogoUrl() {
        return ShopLogoUrl;
    }

    public void setShopLogoUrl(Uri shopLogoUrl) {
        ShopLogoUrl = shopLogoUrl;
    }

    public ArrayList<AllProducts> getAllProductsArrayList() {
        return allProductsArrayList;
    }

    public void setAllProductsArrayList(ArrayList<AllProducts> allProductsArrayList) {
        this.allProductsArrayList = allProductsArrayList;
    }

    public double getLantitude() {
        return lantitude;
    }

    public double getLongitude() {
        return longitude;
    }


}
