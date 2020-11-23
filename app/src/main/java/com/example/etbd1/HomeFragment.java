package com.example.etbd1;


import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.etbd1.ui.Categories;
import com.example.etbd1.ui.DataCollection.Actions;
import com.example.etbd1.ui.DataCollection.ActivityAttribution;
import com.example.etbd1.ui.DataCollection.ScrollModel;
import com.example.etbd1.ui.HomeDefinition;
import com.example.etbd1.ui.ProductDetailsFragment;
import com.example.etbd1.ui.main.Products.AllProducts;
import com.example.etbd1.ui.main.Shops.ShopsModel;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeProductsAdapter.HomeProductAdapterEvent {

    private SliderAdapter sliderAdapter;
    private RecyclerView latestOfferRV, offersNearYouRV, shopsNearYouRV;
    private RecyclerView.LayoutManager latestOffersLM, offersNearYouLM, shopsNearYouLM;
    private RecyclerView.Adapter adapterRV;
    AllProducts allProducts;
    private FirebaseAnalytics mFirebaseAnalytics;
    private ActivityAttribution activityAttribution;
    private ArrayList<AllProducts> allNearShopsArrayList;
    private static int firstVisibleInListview;
    Actions actions;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    ScrollModel scrollModel;
    int y;

    ArrayList<ScrollModel> arrayListScrollModel;
    public HomeFragment() {
        // Required empty public constructor
    }

    Bundle bundle=new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.homefragment,container,false);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        activityAttribution = new ActivityAttribution();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());

        ArrayList<Categories> getShopsList=new ArrayList<>();
        //getShopsList = ((MainActivity) getActivity()).getAllCategories();
        //Log.e("getAllCategories: ",getShopsList+"");
        //Fragment fragment = new FragmentManager(getContext());
        // Slider start
       /* final SliderView sliderView = view.findViewById(R.id.imageSlider_Home);
        sliderAdapter = new SliderAdapter(this.getContext());

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

        //Using one adapter for whole application and providing different data that will be filtered here:

        // LatestOffers Start Here

        ArrayList<AllProducts> allLatestOffersArrayList = new ArrayList<>();

        //filter data here according to upload time and add to the list as following:

        //creating data object
        allLatestOffersArrayList.add(new AllProducts(R.drawable.p1,"Product 1", "150g", "Shop 1", R.drawable.grocerapp,"15%"));
        allLatestOffersArrayList.add(new AllProducts(R.drawable.p2,"Product 2", "110g", "Shop 2", R.drawable.grocerapp,"10%"));
        allLatestOffersArrayList.add(new AllProducts(R.drawable.p3,"Product 3", "120g", "Shop 3", R.drawable.grocerapp,"20%"));
        allLatestOffersArrayList.add(new AllProducts(R.drawable.p4,"Product 4", "130g", "Shop 4", R.drawable.grocerapp,"25%"));
        allLatestOffersArrayList.add(new AllProducts(R.drawable.p5,"Product 5", "140g", "Shop 5", R.drawable.grocerapp,"5$"));
        allLatestOffersArrayList.add(new AllProducts(R.drawable.p6,"Product 6", "150g", "Shop 6", R.drawable.grocerapp,"30%"));

        // assigning data to views

        latestOfferRV = view.findViewById(R.id.laters_Offers_Slider);
        latestOfferRV.setHasFixedSize(true);
        //latestOffersLM = new GridLayoutManager(getContext(),  2, GridLayoutManager.HORIZONTAL,false);
        latestOffersLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        adapterRV = new HomeProductsAdapter(allLatestOffersArrayList,  this);
        latestOfferRV.setLayoutManager(latestOffersLM);
        latestOfferRV.setAdapter(adapterRV);

        // LatestOffers End Here


        // Offers Near You Start Here

        ArrayList<AllProducts> allNearOffersArrayList = new ArrayList<>();

        //filter data here, according to upload current user and ad's location and add to the list as following:

        //creating data object

        allNearOffersArrayList.add(new AllProducts(R.drawable.p1,"Product 1", "150g", "Shop 1", R.drawable.grocerapp,"15%"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p2,"Product 2", "110g", "Shop 2", R.drawable.grocerapp,"10%"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p3,"Product 3", "120g", "Shop 3", R.drawable.grocerapp,"20%"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p4,"Product 4", "130g", "Shop 4", R.drawable.grocerapp,"25%"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p5,"Product 5", "140g", "Shop 5", R.drawable.grocerapp,"5$"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p6,"Product 6", "150g", "Shop 6", R.drawable.grocerapp,"30%"));

        // assigning data to views

        offersNearYouRV = view.findViewById(R.id.offers_Near_You_Slider);
        offersNearYouRV.setHasFixedSize(true);
        offersNearYouLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        adapterRV = new HomeProductsAdapter(allNearOffersArrayList, this);
        offersNearYouRV.setLayoutManager(offersNearYouLM);
        offersNearYouRV.setAdapter(adapterRV);

        // Offers Near You  End Here



        // Shops Near You Start Here

        allNearShopsArrayList = new ArrayList<>();

        //filter data here, according to the registered shops and sort them according to the minimum distance from current user and add to the list as following:

        //creating data object

        allNearShopsArrayList.add(new AllProducts(R.drawable.p1,"Product 1", "150g", "Shop 1", R.drawable.grocerapp,"15%"));
        allNearShopsArrayList.add(new AllProducts(R.drawable.p2,"Product 2", "110g", "Shop 2", R.drawable.grocerapp,"10%"));
        allNearShopsArrayList.add(new AllProducts(R.drawable.p3,"Product 3", "120g", "Shop 3", R.drawable.grocerapp,"20%"));
        allNearShopsArrayList.add(new AllProducts(R.drawable.p4,"Product 4", "130g", "Shop 4", R.drawable.grocerapp,"25%"));
        allNearShopsArrayList.add(new AllProducts(R.drawable.p5,"Product 5", "140g", "Shop 5", R.drawable.grocerapp,"5$"));
        allNearShopsArrayList.add(new AllProducts(R.drawable.p6,"Product 6", "150g", "Shop 6", R.drawable.grocerapp,"30%"));

        // assigning data to views

        shopsNearYouRV = view.findViewById(R.id.shops_Near_Your_Slider);
        shopsNearYouRV.setHasFixedSize(true);
        shopsNearYouLM = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        adapterRV = new HomeProductsAdapter(allNearShopsArrayList, this);
        shopsNearYouRV.setLayoutManager(shopsNearYouLM);
        //final ;
        shopsNearYouRV.setAdapter(adapterRV);
        ArrayList<RecyclerView> arrayList=new ArrayList<>();
        arrayList.add(shopsNearYouRV);
        arrayList.add(offersNearYouRV);
        arrayList.add(latestOfferRV);
        arrayListScrollModel = new ArrayList<>();
        scrollModel = new ScrollModel();
        //RecyclerView recyclerViewTest;
        for (final RecyclerView recyclerViewFor: arrayList){
            if (recyclerViewFor.toString()!= scrollModel.getScrollItemName()){
                // final String recVName;
                recyclerViewFor.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (dx > 0) {

                            y=dx;
                            // Scrolling up
                            //Log.e("dx: "," "+allNearShopsArrayList.get(dx).getProductName());
                            recyclerViewFor.getAdapter().getItemId(dx);
                            //scrollModel.setScrollItemName(recyclerViewFor.getScrollX().to);
                            // Log.e("Scrolling up "," "+recyclerViewFor.getLayoutManager());
                            String currentRViewname = recyclerViewFor.toString();
                            String[] separatred = currentRViewname.split("app:id/");
                            scrollModel.setScrollItemName(separatred[1]);
                            scrollModel.setScrollAt(HomeFragment.this.toString());
                            scrollModel.setScrollDirection("x-axis");
                            scrollModel.setCount(scrollModel.getCount()+1);
                           // scrollModel.setScrollPosition();
                            Log.e("Scrolling up "," "+recyclerViewFor.getAdapter());
                            databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid()).child("scrollModel");
                        } else {
                            // Scrolling down
                            Log.e("Scrolling down "," "+recyclerViewFor);
                            scrollModel.setScrollItemName(recyclerViewFor.toString());
                            scrollModel.setScrollAt(HomeFragment.this.toString());
                            scrollModel.setScrollDirection("x-axis");
                            scrollModel.setCount(scrollModel.getCount()+1);

                            databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid()).child("scrollModel");
                        }
                        databaseReference.push().setValue(scrollModel);
                    }
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {

                        } else {
                            // Do something
                        }
                    }
                });

                //databaseReference.setValue(scrollModel);

            }

        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onStop() {
        super.onStop();
        for (ScrollModel scrollModel: arrayListScrollModel){
            databaseReference.setValue(scrollModel);

        }
    }

    @Override
    public void onItemClick(AllProducts allProducts) {
        this.allProducts = allProducts;
        //setting the project to main activity and details activity can access mainactivity's get method because details fragment is also hosted by the the mainactivity
        ((MainActivity) getActivity()).setProducts(this.allProducts);
        // Fragment containers was replaced by the homefragment in main activity now productsdeatilfragment is indirectly also hosted by that main activity so replace containers with new fragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProductDetailsFragment productDetailsFragment=new ProductDetailsFragment();

        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, allProducts.getProductName()+" __clicked");
        MainActivity.mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);

        //Toast.makeText(getContext(),allProducts.getProductName()+"  "+allProducts.getDiscount(), Toast.LENGTH_LONG).show();
        fragmentTransaction.replace(R.id.fragment_container,productDetailsFragment,"Product_Details_Fragment").commit();

    }
    @Override
    public void onStart() {
        super.onStart();
        //activityAttribution.setActionType("open");
        Log.e("Tag","Homefragment OnStart");
        Log.e("tag","shops near you _____ "+ shopsNearYouRV);
    }
}
