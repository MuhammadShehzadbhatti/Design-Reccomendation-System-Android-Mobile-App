package com.example.etbd1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.R.drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.etbd1.ui.Categories;
import com.example.etbd1.ui.HomeDefinition;
import com.example.etbd1.ui.LocationsOnMapModel;
import com.example.etbd1.ui.ProductDetailsFragment;
import com.example.etbd1.ui.SubCategories;
import com.example.etbd1.ui.main.DataCollectionInterface;
import com.example.etbd1.ui.main.Numbers;
import com.example.etbd1.ui.main.Products.AllProducts;
import com.example.etbd1.ui.main.Shops.ShopsModel;
import com.example.etbd1.ui.main.user.SignUp;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DataCollectionInterface {
    private AllProducts products;
    SpaceNavigationView spaceNavigationView;
    Fragment fragment = null;
    public static FirebaseAnalytics mFirebaseAnalytics;
    public static FirebaseAuth firebaseAuth;
    public static FirebaseUser firebaseUser;
    private Toolbar toolbar;
    public static Dictionary<String,ArrayList<Product>> productsDictionary;
    public static FirebaseDatabase database;
    public static DatabaseReference myRef;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction ;
    ProductDetailsFragment productDetailsFragment;
    private FirebaseStorage firebaseStorage;
    private StorageReference mStorageRef;
    ArrayList<Uri> shoplogoUriList;
    ArrayList<ShopsModel> shopsModelArrayList;
    Dictionary<String, ShopsModel> shopsDict;
    private ArrayList<Categories> categoriesArrayList=new ArrayList<>();
    public ArrayList<SubCategories> subCategoriesArrayList;
    HashMap<Categories,ArrayList<SubCategories>> categoriesSubCategoriesHashMap;
    Categories categories;
    SubCategories subCategories;
    ArrayList<SubCategories> subCategoriesArrayList1;
    DataSnapshot dataSnapshotSC;
    public MainActivity(){
        //getAllShops();
        /*firebaseStorage = FirebaseStorage.getInstance();
        mStorageRef = firebaseStorage.getReferenceFromUrl("gs://etbd-1.appspot.com");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("data");
        getAllCategories();*/

    }
    /*public MainActivity(){
        if (findViewById(R.id.fragment_container)!=null){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,new ProductDetailsFragment()).commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProductDetailsFragment()).commit();

        }
    }
    public MainActivity(String openFragment){


    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spaceNavigationView = findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.ic_home_black_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("CATEGORY", R.drawable.category));


        categoriesSubCategoriesHashMap=new HashMap<>();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        getAllCategories();
        //mStorageRef = firebaseStorage.getReference();

        if (findViewById(R.id.fragment_container)!=null){

            Intent intent = getIntent();
            if (intent.getStringExtra("open_Fragment")=="ProductDetailsFragment"){
                /*for (ShopsModel shopsModel:getAllCategories()){
                   *//* if (shopsModel.getShopID()==intent.getStringExtra("shop_Information")){

                    }*//*
                }*/
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new ProductDetailsFragment()).commit();
            }

        }
        else if (findViewById(R.id.fragment_container)==null){

            Intent intent = getIntent();
            if (intent.getStringExtra("open_Fragment")=="ProductDetailsFragment"){
               // getAllCategories();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new ProductDetailsFragment()).commit();
            }
            else{
                fragmentTransaction.replace(R.id.fragment_container,new HomeFragment()).commit();
            }
        }
        productsDictionary = new Dictionary<String, ArrayList<Product>>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Enumeration<String> keys() {
                return null;
            }

            @Override
            public Enumeration<ArrayList<Product>> elements() {
                return null;
            }

            @Override
            public ArrayList<Product> get(Object key) {
                return null;
            }

            @Override
            public ArrayList<Product> put(String key, ArrayList<Product> value) {
                return null;
            }

            @Override
            public ArrayList<Product> remove(Object key) {
                return null;
            }
        };

        if (findViewById(R.id.fragment_container)!=null){

        }
       // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment(), "homeFragment").commit();
        //myRef.setValue(getSupportFragmentManager().findFragmentById(R.id.fragment_container));
        //Test sending muliple locations to Map Location Model
        // Obtain the FirebaseAnalytics instance.

        Bundle params = new Bundle();
        /*String shop_name=shops_latLong1.getShopName();
        String shop_category=shops_latLong1.getCategory();*/

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {

            @Override
            public void onCentreButtonClick() {
                //Toast.makeText(MainActivity.this,"Open Maps", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intent);*/
                /*List<Integer> shopSliderImages = new ArrayList<>();
                shopSliderImages.add(R.drawable.slider_image1);
                shopSliderImages.add(R.drawable.xdimageslider);*/



                spaceNavigationView.setCentreButtonSelectable(true);
                fragment = new Maps();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                //Toast.makeText(MainActivity.this, itemIndex + " Home " + itemName, Toast.LENGTH_SHORT).show();
                if (itemIndex == 0) {
                    fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    /*Intent intent = new Intent(MainActivity.this,HomeFragment.class);
                    startActivity(intent);*/
                }
                if (itemIndex == 1) {
                    fragment = new ProductsCategory();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                //Toast.makeText(MainActivity.this, itemIndex + " Category " + itemName, Toast.LENGTH_SHORT).show();
            }

        });


        // working casual nav bar
/*        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        MenuItem logOut = menu.findItem(R.id.action_signOut);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Search...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.toolbar_menu,menu);
       return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_signOut:
                Log.d("signOut: ", "signout");
                signout();
                break;
            case R.id.action_search:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
                searchView.setQueryHint("Search...");

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
                break;
        }
        return true;
    }
    private void signout(){
        firebaseAuth.signOut();
        startActivity(new Intent(this, HomeDefinition.class));
    }
    public AllProducts getProducts() {
        return products;
    }

    public void setProducts(AllProducts products) {
        this.products = products;
    }

    //working casual navBar
   /* private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.add:
                    selectedFragment = new ShopProductsFragment();
                    break;
                case R.id.map:
                    selectedFragment = new ProductsCategory();
                    break;
            }
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };*/
    public void setObjectAdapter(AllProducts product) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        //getAllCategories().clear();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //getAllCategories().clear();
        //Toast.makeText(this,  getApplicationContext(),getSupportFragmentManager().findFragmentById(R.id.fragment_container).toString(),Toast.LENGTH_LONG).show();
    }
    public ArrayList<Categories> getAllCategories(){
        //categoriesArrayList = new ArrayList<>();
        firebaseStorage = FirebaseStorage.getInstance();
        mStorageRef = firebaseStorage.getReferenceFromUrl("gs://etbd-1.appspot.com");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("data");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //categories = new Categories();
                for(final DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    categories=new Categories();
                    categories.setCategoryName(dataSnapshot.getKey());
                    if (!categoriesArrayList.contains(categories)){
                        categoriesArrayList.add(categories);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.e("Categories List:  ",categoriesArrayList.size()+"");
        //getCategoriesSubCategories_1(categoriesArrayList);
        return categoriesArrayList;
    }

    @Override
    public ArrayList<Categories> getCategories() {
        return categoriesArrayList;
    }

    @Override
    public void setCategories(final Categories categories) {
            //Log.e("Categories List:  ",categories.getCategoryName()+"");


    }

    @Override
    public ArrayList<Categories> getCategoriesSubCategories() {return new ArrayList<>();}



    public ArrayList<Categories> getCategoriesSubCategories_1(final Categories categories) {
        //for (final Categories categories:categoriesArrayList){
            //subCategoriesArrayList1 = new ArrayList<>();
            myRef = myRef.child(categories.getCategoryName());
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    subCategoriesArrayList = new ArrayList<>();
                    for (DataSnapshot snapshot1:snapshot.getChildren()){
                        subCategories = new SubCategories();
                        subCategories.setSubcategoryName(snapshot1.getKey());
                        subCategoriesArrayList.add(subCategories);
                    }
                    //Log.e("SubCategories: ",subCategoriesArrayList.size()+"");
                     //newCategory = addSubCategory(categories, subCategoriesArrayList);

                    int i = categoriesArrayList.indexOf(categories);
                    //Categories newCategory = categories.getSubCategoriesArrayList();
                    categoriesArrayList.set(i,categories);
                    categories.setSubCategoriesArrayList(subCategoriesArrayList);
                    Categories returnCategory = categoriesArrayList.get(i);
                    Log.e("CategoriesName: ",returnCategory.getCategoryName());
                    Log.e("subcategoryFromList: ",returnCategory.getSubCategoriesArrayList().size()+"");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        //}
        return categoriesArrayList;
    }

    private Categories addSubCategory(Categories categories, ArrayList<SubCategories> subCategoriesArrayList) {

        categories.setSubCategoriesArrayList(subCategoriesArrayList);

        return categories;
    }
    private ArrayList<AllProducts> addProductsToShop(){

        ArrayList<AllProducts> allNearOffersArrayList = new ArrayList<>();

        //filter data here, according to upload current user and ad's location and add to the list as following:

        //creating data object

        allNearOffersArrayList.add(new AllProducts(R.drawable.p1,"Product 1", "150g", R.drawable.grocerapp,"15%"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p2,"Product 2", "110g", R.drawable.grocerapp,"10%"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p3,"Product 3", "120g", R.drawable.grocerapp,"20%"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p4,"Product 4", "130g", R.drawable.grocerapp,"25%"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p5,"Product 5", "140g", R.drawable.grocerapp,"5$"));
        allNearOffersArrayList.add(new AllProducts(R.drawable.p6,"Product 6", "150g", R.drawable.grocerapp,"30%"));

        return allNearOffersArrayList;
    }

    public ArrayList<SubCategories> getSubCategories(final Categories categories){


        return subCategoriesArrayList;
    }

   /* @Override
    public ArrayList<SubCategories> getSubCategories() {
        return subCategoriesArrayList;
    }

    @Override
    public void setSubCategories(ArrayList<Categories> categoriesList) {

        //categoriesArrayList = categoriesList;

        firebaseStorage = FirebaseStorage.getInstance();
        mStorageRef = firebaseStorage.getReferenceFromUrl("gs://etbd-1.appspot.com");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("data");
        for (Categories categories: getAllCategories()){
            myRef.child(categories.getCategoryName()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        subCategories = new SubCategories();
                        String subCategoriesStr = dataSnapshot.getKey();
                        subCategories.setSubcategoryName(subCategoriesStr);
                        //if (!subCategoriesArrayList.contains(dataSnapshot.getKey())) {
                        //Log.e("categories True: ", subCategories.getSubcategoryName() + "");
                        if (!subCategories.getSubcategoryName().equals("men") && !subCategories.getSubcategoryName().equals("kids") && !subCategories.getSubcategoryName().equals("women")){
                            subCategoriesArrayList.add(subCategories);
                            for (SubCategories subcategory1: subCategoriesArrayList){
                                //Log.e("SubCategories True: ", subcategory1.getSubcategoryName() + "");
                            }
                        }
                        else {
                            *//*myRef.child(subCategories.getSubcategoryName()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot dataSnapshot1:snapshot.getChildren()){
                                        String subCategoriesStr = dataSnapshot1.getKey();
                                        subCategories.setSubcategoryName(subCategoriesStr);
                                        subCategoriesArrayList.add(subCategories);
                                        for (SubCategories subcategory1: subCategoriesArrayList){
                                            Log.e("SubCategories m/w/k: ", subcategory1.getSubcategoryName() + "");
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });*//*

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            categoriesSubCategoriesHashMap.put(categories,subCategoriesArrayList);
        }

        for (Categories c:categoriesSubCategoriesHashMap.keySet()){
            Log.e("HashMap key: ", c.getCategoryName()+"");
            for (SubCategories subCategories:categoriesSubCategoriesHashMap.get(c))  {
                Log.e("HashMap: ", subCategories.getSubcategoryName()+"");
            }
        }
    }*/
    /*private ArrayList<Uri> getShopLogo(){
       ArrayList<String> arrayListStringImgeNames = new ArrayList<>();
        arrayListStringImgeNames.add("1587086255ce78cd9b76abe16898d9f07a9ceb3d3c_thumbnail_600x.png");
        arrayListStringImgeNames.add("001186aa-2b77-4ffa-a790-3275efea6e71_200x200.png");
        arrayListStringImgeNames.add("3bc95f1c-2a0b-4ea3-8dd1-a862a67b61c6_200x200.png");
        arrayListStringImgeNames.add("635ee2d6-1bbe-4240-9419-42d168c28c04_200x200.png");
        arrayListStringImgeNames.add("929c798a-4763-4fe2-a417-f2a5a0b09345_200x200.png");
       // for (String imageName:arrayListStringImgeNames){
        StorageReference storageRef =mStorageRef.child("shopimages").child("shoplogo");
        //final  StorageReference storageRef = mStorageRef.child("shopLogo").child();

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                shoplogoUriList.add(uri);
                Log.e("shoplogoUriList: "," "+uri.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(),"unable to access the images from Storage",Toast.LENGTH_LONG).show();
            }
        });
      // }

        return shoplogoUriList;
    }*/

}

