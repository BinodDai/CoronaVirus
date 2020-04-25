package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView totalcases,critical,active,recovered,deaths,todaycases,todaydeaths,affectedcountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalcases=findViewById(R.id.totalcase);
        critical=findViewById(R.id.critical);
        active=findViewById(R.id.active);
        recovered=findViewById(R.id.recovered);
        deaths=findViewById(R.id.deaths);
        todaycases=findViewById(R.id.casetoday);
        todaydeaths=findViewById(R.id.casedeath);
        affectedcountries=findViewById(R.id.affectedCountries);
        simpleArcLoader=findViewById(R.id.loader);
        pieChart=findViewById(R.id.piechart);
        scrollView=findViewById(R.id.scrollglobal);
        fetchData();


    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    totalcases.setText(jsonObject.getString("cases"));
                    critical.setText(jsonObject.getString("critical"));
                    active.setText(jsonObject.getString( "active"));
                    recovered.setText(jsonObject.getString("recovered"));
                    deaths.setText(jsonObject.getString("deaths"));
                    todaycases.setText(jsonObject.getString("todayCases"));
                    affectedcountries.setText(jsonObject.getString("affectedCountries"));
                    todaydeaths.setText(jsonObject.getString("todayDeaths"));
                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(totalcases.getText().toString()), Color.parseColor("#000000")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(deaths.getText().toString()), Color.parseColor("#008000")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(active.getText().toString()), Color.parseColor("#0000FF")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered.getText().toString()), Color.parseColor("#ed1325")));
                    pieChart.startAnimation();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void findCountries(View view) {
        startActivity(new Intent(MainActivity.this,TrackCountries.class));


    }
}
