package com.example.etbd1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.etbd1.HomeFragment;
import com.example.etbd1.MainActivity;
import com.example.etbd1.PageViewAdapter;
import com.example.etbd1.R;
import com.example.etbd1.SliderAdapter;
import com.example.etbd1.SliderItem;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PageViewAdapter pageViewAdapter;
    private SliderView sliderView;
    private SliderAdapter sliderAdapter;
    private TabLayout viewPagerTabsLayout;
    private ImageButton btnBackToProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        btnBackToProduct = findViewById(R.id.backToProduct);
        btnBackToProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ProductDetailsFragment productDetailsFragment=new ProductDetailsFragment();
                Intent openActivity = new Intent(getApplicationContext(),MainActivity.class);
                openActivity.putExtra("open_Fragment","ProductDetailsFragment");
                openActivity.putExtra("product_Infromation: ","Product 1");
                openActivity.putExtra("shop_Information: ","Shop_1");

                startActivity(openActivity);
                //Log.e("Tag FragActivity: "," "+productDetailsFragment.getActivity().toString());
                //productDetailsFragment.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProductDetailsFragment(),"ProductDetailsFragment").commit();
                //fragmentTransaction.replace(R.id.fragment_containerHomeDef,productDetailsFragment,"ProductDetailsFragment").comit();
               // targetActivity.putExtra("Target_Fragement",)
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProductDetailsFragment(), "ProductDetailsFragment").commit();
            }
        });
        viewPager = findViewById(R.id.viewPagerShops);
        viewPagerTabsLayout = findViewById(R.id.viewPagerTabsLayout);

        pageViewAdapter = new PageViewAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pageViewAdapter);
        viewPagerTabsLayout.setupWithViewPager(viewPager);
        viewPagerTabsLayout.setSelectedTabIndicatorColor(Color.parseColor(("#FFBD2E")));

        /*viewPagerTabsLayout.addTab(viewPagerTabsLayout.newTab().setText("Home"));
        viewPagerTabsLayout.addTab(viewPagerTabsLayout.newTab().setText("Details"));
        viewPagerTabsLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/

        sliderAdapter = new SliderAdapter(this.getApplicationContext());
        sliderView = findViewById(R.id.imageSlider);

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
    }
}
