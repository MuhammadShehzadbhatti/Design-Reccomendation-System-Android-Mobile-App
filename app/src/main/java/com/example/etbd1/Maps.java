package com.example.etbd1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.etbd1.ui.LocationsOnMapModel;
import com.example.etbd1.ui.main.Shops.ShopsModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.measurement.module.Analytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Maps extends Fragment implements OnMapReadyCallback {
    GoogleMap googleMap;
    SupportMapFragment supportMapFragment;
    MapView mMapView;
    ArrayList<ShopsModel> andLongs=new ArrayList<>();
    Bundle params;
    String fragment_id, fragment_name,fragment_view,fragment_activity,fragment_tag ;
    public Maps(ArrayList<ShopsModel> locationsOnMapModel) {
        this.andLongs = locationsOnMapModel;
        this.params=params;
        // Required empty public constructor
    }

    public Maps() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);



        return view;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        // TODO Auto-generated method stub
        super.onAttachFragment(fragment);
        fragment_id = String.valueOf(fragment.getId());
        fragment_name = String.valueOf(fragment.getView());
        fragment_view = String.valueOf(fragment.getView());
        fragment_activity = String.valueOf(fragment.getActivity());
        fragment_tag = String.valueOf(fragment.getTag());
        Toast.makeText(getContext(), String.valueOf(fragment.getId()), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState); mMapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        for (ShopsModel locations:andLongs){
            LatLng shopsLatLngs = new LatLng(locations.getLantitude(),locations.getLongitude());
            Toast.makeText(getContext()," Lati "+String.valueOf(locations.getLantitude())+" Longi "+String.valueOf(locations.getLongitude())+" Size "+String.valueOf(andLongs.size()),Toast.LENGTH_LONG).show();
            googleMap.addMarker(new MarkerOptions()
                    .position(shopsLatLngs)
                    .title(String.valueOf(locations.getShopName()))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shopsLatLngs,16));


        }
    }

}
