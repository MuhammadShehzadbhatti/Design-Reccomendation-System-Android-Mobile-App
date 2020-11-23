package com.example.etbd1.ui.main.Products;

import android.net.Uri;

import com.example.etbd1.ui.main.Shops.ShopsModel;

public class AllProducts  {
    private int productImage,shopLogo;
    private Double current_price,raw_price,discount;
    private String productName,quantity,shopName,currency,disc;
    private String image_url, variation_0_image, variation_0_thumbnail, variation_1_image,variation_1_thumbnail;
    public AllProducts(int p1, String s, String s1, int grocerapp, String s2){

    }
    // for shop
    public AllProducts(int productImage, String productName) {
        super();
        this.productImage = productImage;
        this.productName = productName;
    }

    //for home


    public AllProducts(int productImage, String productName, String quantity, String shopName, int shopLogo, String disc) {
        this.productImage = productImage;
        this.productName = productName;
        this.quantity = quantity;
        this.disc = disc;
        this.shopName = shopName;
        this.shopLogo = shopLogo;
    }

    public AllProducts() {
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public Double getRaw_price() {
        return raw_price;
    }

    public void setRaw_price(Double raw_price) {
        this.raw_price = raw_price;
    }

    public Double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Double current_price) {
        this.current_price = current_price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVariation_0_image() {
        return variation_0_image;
    }

    public void setVariation_0_image(String variation_0_image) {
        this.variation_0_image = variation_0_image;
    }

    public String getVariation_0_thumbnail() {
        return variation_0_thumbnail;
    }

    public void setVariation_0_thumbnail(String variation_0_thumbnail) {
        this.variation_0_thumbnail = variation_0_thumbnail;
    }

    public String getVariation_1_image() {
        return variation_1_image;
    }

    public void setVariation_1_image(String variation_1_image) {
        this.variation_1_image = variation_1_image;
    }

    public String getVariation_1_thumbnail() {
        return variation_1_thumbnail;
    }

    public void setVariation_1_thumbnail(String variation_1_thumbnail) {
        this.variation_1_thumbnail = variation_1_thumbnail;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(int shopLogo) {
        this.shopLogo = shopLogo;
    }
}
