package com.bostonburke.cdbgdatamap;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Boston on 10/1/2016.
 */

public class DisplayResults extends AppCompatActivity{

    private static InputStream source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        // Get the txt file we need
        try {
            AssetManager assetManager = getAssets();
            InputStream source = assetManager.open("AllYears.txt");
            this.source = source;
        }
        catch(IOException e){
            ;
        }

        // Set city name and message text views
        TextView nameView = (TextView) findViewById(R.id.cityNameView);
        TextView metaMsg = (TextView) findViewById(R.id.metaMsg);
        TextView resultsView = (TextView) findViewById(R.id.resultsView);

        String results;
        String state = "";

        int year = MapsActivity.getYear();

        LatLng cityCenter =  (LatLng) this.getIntent().getExtras().get("city center");
        String city = "";
        if (cityCenter.equals(new LatLng(41.881832, -87.623177))){
            city = "Chicago";
            state = "IL";
        }
        else if (cityCenter.equals(new LatLng(34.052235, -118.243683))){
            city = "Los Angeles";
            state = "CA";
        }
        else if (cityCenter.equals(new LatLng(37.7749, -122.4194))){
            city = "San Francisco";
            state = "CA";
        }
        else if (cityCenter.equals(new LatLng(25.778135, -80.179100))){
            city = "Miami";
            state = "FL";
        }
        else if (cityCenter.equals(new LatLng(42.3601, -71.0589))){
            city = "Boston";
            state = "MA";
        }
        else if (cityCenter.equals(new LatLng(47.6062, -122.3321))){
            city = "Seattle";
            state = "WA";
        }
        else if (cityCenter.equals(new LatLng(38.9072, -77.0369))){
            city = "Washington";
            state = "DC";
        }
        else {
            city = "New York";
            state = "NY";
        }

        nameView.setText(String.format("%s, %s", city, state));
        metaMsg.setText(String.format("Federal funds received in %d", year));

        try{

            CityProfile cp = new CityProfile(year, city);
            double acqValue = cp.getAcquisition();
            double ecoDev = cp.getEconomicDev();
            double housing = cp.getHousing();
            double publicImp = cp.getPublicImprov();
            double pubServ = cp.getPublicServices();
            double other = cp.getOther();

            results = String.format("Acquisitions: $%.2f\nEconomic Dev.: $%.2f\nHousing: $%.2f\n" +
                    "Public Impr.: $%.2f\nPublic Service: $%.2f\nOther: $%.2f", acqValue, ecoDev,
                    housing, publicImp, pubServ, other);
            resultsView.setText(results);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static InputStream getSourceFile(){
        return source;
    }
}
