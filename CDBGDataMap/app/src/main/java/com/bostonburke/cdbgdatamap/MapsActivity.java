package com.bostonburke.cdbgdatamap;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        int year = this.getIntent().getIntExtra("year", 2009);
        this.year = year;
        drawCircles(year);
    }

    private void drawCircles(int year){
        // Homelessness density by city and year
        Double[] rhoChicago = {5.3, 5.27, 2.96, 6.10};
        Double[] rhoLA = {100., 62., 39.3, 32.68};
        Double[] rhoSF = {34.6, 31.98, 32.13, 36.8};
        Double[] rhoMiami = {2.9, 9.5, 10.7, 11.64};
        Double[] rhoBoston = {0.0, 3.4, 2.69, 2.81};
        Double[] rhoSeattle = {31.7, 31.69, 27.15, 29.78};
        Double[] rhoWash = {0.0, 5.18, 5.09, 4.84};
        Double[] rhoNY = {2.68, 3.55, 3.09, 3.81};

        // compute the index need to access
        int index = year - 2009;

        // add Chicago circle
        int alpha = (int) (rhoChicago[index] * 1 + 100);
        Circle cChicago = mMap.addCircle(new CircleOptions()
                .center(new LatLng(41.881832, -87.623177))
                .radius(160000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(alpha,255,102,0)));
        cChicago.setClickable(true);

        alpha = (int) (rhoLA[index] * 1 + 100);
        Circle cLA = mMap.addCircle(new CircleOptions()
                .center(new LatLng(34.052235, -118.243683))
                .radius(160000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(alpha, 255, 102, 0)));
        cLA.setClickable(true);

        alpha = (int) (rhoSF[index] * 1 + 100);
        Circle cSF = mMap.addCircle(new CircleOptions()
                .center(new LatLng(37.7749, -122.4194))
                .radius(160000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(alpha, 255, 102, 0)));
        cSF.setClickable(true);

        alpha = (int) (rhoMiami[index] * 1 + 100);
        Circle cMiami = mMap.addCircle(new CircleOptions()
                .center(new LatLng(25.778135, -80.179100))
                .radius(160000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(alpha, 255, 102, 0)));
        cMiami.setClickable(true);

        alpha = (int) (rhoBoston[index] * 1 + 100);
        Circle cBoston = mMap.addCircle(new CircleOptions()
                .center(new LatLng(42.3601, -71.0589))
                .radius(160000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(alpha, 255, 102, 0)));
        cBoston.setClickable(true);

        alpha = (int) (rhoSeattle[index] * 1 + 100);
        Circle cSeattle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(47.6062, -122.3321))
                .radius(160000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(alpha, 255, 102, 0)));
        cSeattle.setClickable(true);

        alpha = (int) (rhoWash[index] * 1 + 100);
        Circle cWashington = mMap.addCircle(new CircleOptions()
                .center(new LatLng(38.9072, -77.0369))
                .radius(160000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(alpha, 255, 102, 0)));
        cWashington.setClickable(true);

        alpha = (int) (rhoNY[index] * 1 + 100 + 100);
        Circle cNewYork = mMap.addCircle(new CircleOptions()
                .center(new LatLng(40.7128, -74.0059))
                .radius(160000)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(alpha, 255, 102, 0)));
        cNewYork.setClickable(true);

        // Move camera to position
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39, -98), 3f));

        // Set circle click listener
        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle circle) {
                Intent intent = new Intent(MapsActivity.this, DisplayResults.class);
                intent.putExtra("city center", circle.getCenter());
                startActivity(intent);
            }
        });
    }

    public static int getYear(){
        return year;
    }
}
