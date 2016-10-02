package com.bostonburke.cdbgdatamap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Handles when the user selects a year
     */
    public void onRadioButtonClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        int year = 0;

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_2009:
                if (checked)
                    year = 2009;
                break;
            case R.id.radio_2010:
                if (checked)
                    year = 2010;
                break;
            case R.id.radio_2011:
                if (checked)
                    year = 2011;
                break;
            case R.id.radio_2012:
                if (checked)
                    year = 2012;
                break;
        }

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("year", year);
        startActivity(intent);
    }
}
