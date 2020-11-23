package com.example.etbd1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.etbd1.ui.main.Products.AllProducts;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopProductsFragment extends Fragment {

    private Button btnHome, btnDetails;
    private RelativeLayout relativeLayoutHome, relativeLayoutDetails;
    private SliderAdapter sliderAdapter;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private ImageView shopLogo;
    private TextView shopName, shopDistance, shopDescriptionHeading, shopDescription, homeOrOffersTab, detailsTab;

    private RecyclerView recyclerViewLeft;
    private RecyclerView.LayoutManager rVLeftLayoutManager;
    private RecyclerView.Adapter rVLeftAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate( R.layout.shoproductsfragment, container, false);
        final SliderView sliderView = view.findViewById(R.id.imageSlider);
        //changing shop view start
        relativeLayoutHome = view.findViewById(R.id.shopAllProducts);
        relativeLayoutDetails = view.findViewById(R.id.shopDetails);

      //  relativeLayoutDetails.setVisibility(View.GONE);
       // btnHome.setBackgroundResource(R.drawable.border_set_shopbuttons_onclick);
       /* btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutDetails.setVisibility(View.GONE);
                relativeLayoutHome.setVisibility(View.VISIBLE);
                btnHome.setBackgroundResource(R.drawable.border_set_shopbuttons_onclick);
                btnDetails.setBackgroundResource(R.drawable.border_set_shopbuttons);
            }
        });
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutHome.setVisibility(View.GONE);
                relativeLayoutDetails.setVisibility(View.VISIBLE);
                btnDetails.setBackgroundResource(R.drawable.border_set_shopbuttons_onclick);
                btnHome.setBackgroundResource(R.drawable.border_set_shopbuttons);
            }
        });*/

        // changing view shop

        // Slider start
       /* sliderAdapter = new SliderAdapter(this.getContext());

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        List<SliderItem> sliderItemList = new ArrayList<>();
        List<Integer> sliderImages = new ArrayList<>();
        sliderImages.add(R.drawable.slider_image1);
        sliderImages.add(R.drawable.xdimageslider);

        //dummy data

        for (int i = 0; i < sliderImages.size(); i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);
            sliderItem.setImageUrl(sliderImages.get(i));
            sliderItemList.add(sliderItem);
        }
        sliderAdapter.renewItems(sliderItemList);*/
        // Slider End


        // Products start

        ArrayList<AllProducts> allProductsList= new ArrayList<>();
        allProductsList.add(new AllProducts(R.drawable.p1,"Product 1"));
        allProductsList.add(new AllProducts(R.drawable.p2,"Product 2"));
        allProductsList.add(new AllProducts(R.drawable.p3,"Product 3"));
        allProductsList.add(new AllProducts(R.drawable.p4,"Product 4"));
        allProductsList.add(new AllProducts(R.drawable.p5,"Product 5"));
        allProductsList.add(new AllProducts(R.drawable.p6,"Product 6"));

        recyclerViewLeft = view.findViewById(R.id.shopProductsLeft);
        recyclerViewLeft.setHasFixedSize(true);
        rVLeftLayoutManager = new GridLayoutManager(getContext(), 2);
        rVLeftAdapter = new ProductsAdapterShop(allProductsList, this.getContext());
        recyclerViewLeft.setLayoutManager(rVLeftLayoutManager);
        recyclerViewLeft.setAdapter(rVLeftAdapter);

        // Products end

















        /*//renewItems(view);

        // View Pager
        mPager = view.findViewById(R.id.shopsViewPager);
        pagerAdapter = new PageViewAdapter(getChildFragmentManager());
        mPager.setAdapter(pagerAdapter);

        homeOrOffersTab = view.findViewById(R.id.homeOrOffersTab);
        detailsTab = view.findViewById(R.id.detailsTab);

        homeOrOffersTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(0);
            }
        });

        detailsTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(1);
            }
        });*/

        /*mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        return view;
    }

    private void onChangeTab(int position) {

        /*if (position == 0){
            homeOrOffersTab.setText("A");
        }
        if (position == 1){
            detailsTab.setText("B");
        }*/

    }

    public void renewItems(View view) {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                // sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
            } else {
                //sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        sliderAdapter.renewItems(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (sliderAdapter.getCount() - 1 >= 0)
            sliderAdapter.deleteItem(sliderAdapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        sliderItem.setDescription("Slider Item Added Manually");
        //sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        sliderAdapter.addItem(sliderItem);
    }
}
