package com.mirea.kt.ribo;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DistrictAdapter.OnDistrictClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<District> districts = new ArrayList<>();
        districts.add(new District("ЦАО","Центральный Административный Округ","null"));
        districts.add(new District("САО","Северный Административный Округ","null"));
        districts.add(new District("СВАО","Северо-Восточный Административный Округ","null"));
        districts.add(new District("ВАО","Восточный Административный Округ","null"));
        districts.add(new District("ЮВАО","Юго-Восточный Административный Округ","null"));
        districts.add(new District("ЮАО","Южный Административный Округ","null"));
        districts.add(new District("ЮЗАО","Юго-Западный Административный Округ","null"));
        districts.add(new District("ЗАО","Западный Административный Округ","null"));
        districts.add(new District("СЗАО","Северо-Западный Административный Округ","null"));
        districts.add(new District("ЗелАО","Зеленоградский Административный Округ","null"));
        districts.add(new District("ТАО","Троицкий Административный Округ","null"));
        districts.add(new District("НАО","Новомосковский Административный Округ","null"));
        RecyclerView rcView = findViewById(R.id.recyclerView);
        DistrictAdapter adapter = new DistrictAdapter(districts,this);
        rcView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcView.setAdapter(adapter);
    }

    @Override
    public void onDistrictClick(District district, int position) {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        intent.putExtra("District", position);
        startActivity(intent);
    }
}