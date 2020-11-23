package com.example.etbd1.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.etbd1.ui.main.Products.AllProducts;
import com.example.etbd1.HomeProductsAdapter;
import com.example.etbd1.MainActivity;
import com.example.etbd1.R;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment implements HomeProductsAdapter.HomeProductAdapterEvent {

    private ImageView imgProduct;
    private TextView txtProductName,txtProductPrice,txtProductNewPrice,txtProductDescription;
    private Button btnGoToShop;
    private AllProducts product;
   // private FirebaseAnalytics mFirebaseAnalytics;
    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_details, container, false);
        txtProductName = view.findViewById(R.id.txtProductName);
        txtProductPrice = view.findViewById(R.id.txtProductPrice);
        txtProductNewPrice = view.findViewById(R.id.txtProductNewPrice);
        txtProductDescription = view.findViewById(R.id.txtProductDescription);
        imgProduct = view.findViewById(R.id.imgProduct);
        btnGoToShop = view.findViewById(R.id.btnGoToShop);





        product = ((MainActivity) getActivity()).getProducts();

        txtProductName.setText(product.getProductName());
        txtProductPrice.setText(product.getDisc());
        txtProductNewPrice.setText(product.getDisc());
        txtProductDescription.setText(product.getShopName());
        btnGoToShop.setText("Visit "+product.getShopName());
        btnGoToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ShopActivity.class));
            }
        });
      //  mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());

        /*Fragment currentFragment = getFragmentManager().findFragmentByTag("Product_Details_Fragment");

        Bundle bundle = new Bundle();*/
       // Fragment currentFragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        /*bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Product_Details_Fragment");
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY,  "Fragment");

        if (currentFragment.getTag()=="Product_Details_Fragment"){

            bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "Product_Details_Fragment");
            bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Product_Details_Fragment");
        }*/
        //mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);

        //Bundle productDetailsBundle = new Bundle();
        /*if (currentFragment.getTag()=="Product_Details_Fragment") {
            mFirebaseAnalytics.setCurrentScreen((Activity) this.getContext(), "Product Details Fragment", "ProductDetailsFragment");
            bundle.putString("product_name", product.getProductName());
            bundle.putString("product_discount", product.getDiscount());
            bundle.putString("product_quantity", product.getQuantity());
            bundle.putString("product_shop_name", product.getShopName());
            mFirebaseAnalytics.logEvent("product_details_bundle", bundle);
        }*/
        return view;
    }

    @Override
    public void onItemClick(AllProducts products) {
        //this.product = products;
        /*Toast.makeText(getContext(),product.getProductName()+"  "+product.getDiscount(), Toast.LENGTH_LONG).show();
        txtProductName.setText(product.getProductName());
        txtProductPrice.setText(product.getDiscount());
        txtProductNewPrice.setText(product.getDiscount());
        txtProductDescription.setText(product.getShopName());*/
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.e("tag","abc____ "+product.getProductName());
        //Log.e("Taag","A product details activity on start");
    }
}
