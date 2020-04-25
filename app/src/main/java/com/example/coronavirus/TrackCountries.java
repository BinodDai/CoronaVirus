package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrackCountries extends AppCompatActivity {
    EditText search;
    SimpleArcLoader arcLoader;
    ListView listView;
    public static List<ModelCountry> modelCountryList=new ArrayList<>();
    ModelCountry countrymodel;
    CustomAdopter customAdopter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_countries);
        search=findViewById(R.id.search);
        arcLoader=findViewById(R.id.loader);
        listView=findViewById(R.id.listview);
        fetchData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(),DetaiActivity.class).putExtra("position",i));
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                customAdopter.getFilter().filter(charSequence);
                customAdopter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/countries";
        arcLoader.start();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String CountryName=jsonObject.getString("country");
                        String totalcases=jsonObject.getString("cases");
                        String critical=jsonObject.getString("critical");
                        String active=jsonObject.getString("active");
                        String recovered=jsonObject.getString("recovered");
                        String deaths=jsonObject.getString("deaths");
                        String todaycases=jsonObject.getString("todayCases");
                        String todaydeaths=jsonObject.getString("todayDeaths");

                        JSONObject jsonObject1=jsonObject.getJSONObject("countryInfo");
                        String flagurl=jsonObject1.getString("flag");

                        countrymodel=new ModelCountry(flagurl,CountryName,totalcases,deaths,active,todaycases,todaydeaths,recovered,critical);
                        modelCountryList.add(countrymodel);
                    }
                    customAdopter=new CustomAdopter(TrackCountries.this,modelCountryList);
                    listView.setAdapter(customAdopter);
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TrackCountries.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
