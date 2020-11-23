package com.example.etbd1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ShopHome extends AppCompatActivity {


    private SliderAdapter sliderAdapter;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private ImageView shopLogo;
    private TextView shopName, shopDistance, shopDescriptionHeading, shopDescription, homeOrOffersTab, detailsTab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoproductsfragment);

        final SliderView sliderView = findViewById(R.id.imageSlider);


        sliderAdapter = new SliderAdapter(this.getApplicationContext());

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
        sliderAdapter.renewItems(sliderItemList);
        //renewItems(view);

        // View Pager
       // mPager = findViewById(R.id.shopsViewPager);
        pagerAdapter = new PageViewAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);



        homeOrOffersTab = findViewById(R.id.homeOrOffersTab);
        detailsTab = findViewById(R.id.detailsTab);

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
        });
    }
}
