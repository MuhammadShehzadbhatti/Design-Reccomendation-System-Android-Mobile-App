package com.example.etbd1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.etbd1.ui.main.Products.AllProducts;

import java.util.ArrayList;

public class HomeProductsAdapter extends RecyclerView.Adapter<HomeProductsAdapter.HomeProductsVhCardView>{
    private AllProducts currentProduct;


    private HomeProductAdapterEvent homeProductAdapterEvent;
    int pImg ;
    String pName ;
    private SendDataToFragment sendDataToFragment = new SendDataToFragment() {
        @Override
        public void sendObject(AllProducts currentProductItems) {

        }
    };
    //private Context context;
    private ArrayList<AllProducts> homeProductsAL = new ArrayList<>();
    private ArrayList<AllProducts> selectedProductAL = new ArrayList<>();
    public HomeProductsAdapter(HomeProductAdapterEvent homeProductAdapterEvent) {
        this.homeProductAdapterEvent = homeProductAdapterEvent;
    }

    public HomeProductsAdapter( ArrayList<AllProducts> homeProductsList, HomeProductAdapterEvent homeProductAdapterEvent){
        this.homeProductsAL = homeProductsList;
        this.homeProductAdapterEvent = homeProductAdapterEvent;
    }

    @NonNull
    @Override
    public HomeProductsVhCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview,parent,false);
        HomeProductsVhCardView homeProductsVhCardView = new HomeProductsVhCardView(view);
        return homeProductsVhCardView;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductsVhCardView holder, final int position) {
        currentProduct = homeProductsAL.get(position);
       // final Intent intent = new Intent(context,MainActivity.class);
        pImg = currentProduct.getProductImage();
        pName = currentProduct.getProductName();
        holder.homeCarViewProductImage.setImageResource(pImg);
        holder.homeCardViewProductName.setText(pName);
        holder.homeShopLogo.setImageResource(currentProduct.getShopLogo());
        holder.homeShopName.setText(currentProduct.getShopName());
        holder.homeProductDiscount.setText(currentProduct.getDisc());
        holder.homeProductQuantity.setText(currentProduct.getQuantity());
       /* intent.putExtra("pName",currentProduct.getProductName());
        intent.putExtra("pImage",currentProduct.getProductImage());
        intent.putExtra("pShopName",currentProduct.getShopName());
        intent.putExtra("pDiscount",currentProduct.getDiscount());*/
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText((Context) homeProductAdapterEvent,"Current Product: "+currentProduct.getProductName(),Toast.LENGTH_LONG).show();
                AllProducts allProducts = homeProductsAL.get(position);
                homeProductAdapterEvent.onItemClick(homeProductsAL.get(position));
                //intent.putExtra("key",homeProductsAL.get(position).getProductName());
                //v.getContext().startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return homeProductsAL.size();
    }

    public class HomeProductsVhCardView extends RecyclerView.ViewHolder{
        View view;
        ImageView homeCarViewProductImage, homeShopLogo;
        TextView homeCardViewProductName, homeShopName, homeProductQuantity, homeProductDiscount;
        Button btnDetails;

        public HomeProductsVhCardView(@NonNull final View itemView) {
            super(itemView);
            homeCarViewProductImage = itemView.findViewById(R.id.homeProductImage);
            homeCardViewProductName = itemView.findViewById(R.id.homeProductName);
            homeShopName = itemView.findViewById(R.id.homeShopNameOnAd);
            homeProductQuantity = itemView.findViewById(R.id.homeProductQuantity);
            homeProductDiscount = itemView.findViewById(R.id.homeProductPercentOff);
            homeShopLogo = itemView.findViewById(R.id.homeShoplogo);
            //btnDetails = itemView.findViewById(R.id.homeDetailsButton);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position=getAdapterPosition();
                   homeProductAdapterEvent.onItemClick(homeProductsAL.get(position));
                   AppCompatActivity activity=(AppCompatActivity)itemView.getContext();
                  /* ProductDetailsFragment productDetailsFragment=new ProductDetailsFragment();
                   // Fragment containers was replaced by the homefragment in main activity now productsdeatilfragment is indirectly also hosted by that main activity so replace containers with new fragment
                   activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,productDetailsFragment).addToBackStack(null).commit();*/
               }
           });
        }

        /*@Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Toast.makeText(v.getContext(),homeProductsAL.get(position).getProductName(),Toast.LENGTH_LONG).show();
            homeProductAdapterEvent.onItemClick(homeProductsAL.get(position));
        }*/
    }
    public interface HomeProductAdapterEvent{
        void onItemClick(AllProducts allProducts);
    }
}
