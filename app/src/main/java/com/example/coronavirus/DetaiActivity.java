package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetaiActivity extends AppCompatActivity {
    private int positionCountry;
    TextView heading,totalcases,critical,active,recovered,deaths,todaycases,todaydeaths,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai);
        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);
        heading=findViewById(R.id.heading);

        heading.setText("Country:"+TrackCountries.modelCountryList.get(positionCountry).getCountry());
        totalcases=findViewById(R.id.totalcase);
        critical=findViewById(R.id.critical);
        active=findViewById(R.id.active);
        recovered=findViewById(R.id.recovered);
        deaths=findViewById(R.id.deaths);
        todaycases=findViewById(R.id.casetoday);
        todaydeaths=findViewById(R.id.casedeath);
        country=findViewById(R.id.Country);

        totalcases.setText(TrackCountries.modelCountryList.get(positionCountry).getTotalcases());
        critical.setText(TrackCountries.modelCountryList.get(positionCountry).getCritical());
        active.setText(TrackCountries.modelCountryList.get(positionCountry).getActive());
        recovered.setText(TrackCountries.modelCountryList.get(positionCountry).getRecovered());
        deaths.setText(TrackCountries.modelCountryList.get(positionCountry).getDeaths());
        todaycases.setText(TrackCountries.modelCountryList.get(positionCountry).getTodaycases());
        todaydeaths.setText(TrackCountries.modelCountryList.get(positionCountry).getTodaydeath());
        country.setText(TrackCountries.modelCountryList.get(positionCountry).getCountry());
    }
}
