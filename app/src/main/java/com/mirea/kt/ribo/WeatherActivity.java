package com.mirea.kt.ribo;



import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class WeatherActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        int District = getIntent().getIntExtra("District", -1);
        String latitude="";
        String longitude="";
        switch (District) {
            case 0:
                latitude = "55.753600";
                longitude = "37.621184";
                break;
            case 1:
                latitude = "55.838390";
                longitude = "37.525774";
                break;
            case 2:
                latitude = "55.854875";
                longitude = "37.632565";
                break;
            case 3:
                latitude = "55.787715";
                longitude = "37.775631";
                break;
            case 4:
                latitude = "55.692019";
                longitude = "37.754592";
                break;
            case 5:
                latitude = "55.622014";
                longitude = "37.678065";
                break;
            case 6:
                latitude = "55.662735";
                longitude = "37.576187";
            case 7:
                latitude = "55.778003";
                longitude = "37.443533";
                break;
            case 8:
                latitude = "55.829370";
                longitude = "37.451555";
                break;
            case 9:
                latitude = "55.987583";
                longitude = "37.194250";
                break;
            case 10:
                latitude = "55.355802";
                longitude = "37.146999";
                break;
            case 11:
                latitude = "55.594351";
                longitude = "37.371452";
                break;
            default:
                break;
        }
        String address = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,snowfall,cloud_cover,wind_speed_10m&wind_speed_unit=ms&timezone=Europe%2FMoscow&forecast_days=1";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(address);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buffer = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line).append("\n");
                    }
                    String result = buffer.toString();
                    if (reader != null) {
                        try {

                            JSONObject object = new JSONObject(result);
                            JSONArray timeArray = object.getJSONObject("hourly").getJSONArray("time");
                            JSONArray temperatureArray = object.getJSONObject("hourly").getJSONArray("temperature_2m");
                            JSONArray humidityArray = object.getJSONObject("hourly").getJSONArray("relative_humidity_2m");
                            JSONArray precipitationArray = object.getJSONObject("hourly").getJSONArray("precipitation_probability");
                            JSONArray cloudArray = object.getJSONObject("hourly").getJSONArray("cloud_cover");
                            JSONArray windSpeedArray = object.getJSONObject("hourly").getJSONArray("wind_speed_10m");
                            JSONArray snowArray = object.getJSONObject("hourly").getJSONArray("snowfall");
                            ArrayList<Condition> conditions = new ArrayList<>();
                            for (int i = 0; i < timeArray.length(); i++) {
                                String time = timeArray.getString(i);
                                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                                SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                                Date date = null;
                                try {
                                    date = inputFormat.parse(time);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                String outputString = outputFormat.format(date);
                                String temperature = temperatureArray.getString(i);
                                String humidity = humidityArray.getString(i);
                                double precipitation = precipitationArray.getDouble(i);
                                double cloud = cloudArray.getDouble(i);
                                String windSpeed = windSpeedArray.getString(i);
                                double snowfall = snowArray.getDouble(i);
                                conditions.add(new Condition(outputString, temperature, humidity, precipitation, cloud, windSpeed, snowfall));

                            }
                            WeatherActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    RecyclerView rcView = WeatherActivity.this.findViewById(R.id.recyclerView2);
                                    WeatherAdapter adapter = new WeatherAdapter(conditions);
                                    rcView.setLayoutManager(new LinearLayoutManager(WeatherActivity.this, LinearLayoutManager.VERTICAL, false));
                                    rcView.setAdapter(adapter);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



}