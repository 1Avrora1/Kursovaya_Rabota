package com.mirea.kt.ribo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{


    private ArrayList<Condition> conditions;

    public WeatherAdapter(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView dateView;
        private final TextView tempView;
        private final TextView humidView;
        private final TextView precView;
        private final ImageView imaView;
        private final TextView cloudView;
        private final TextView windView;
        ViewHolder(View view){
            super(view);
            dateView = view.findViewById(R.id.Date);
            tempView = view.findViewById(R.id.Temperature);
            humidView = view.findViewById(R.id.Humidity);
            precView = view.findViewById(R.id.Precipitation);
            imaView = view.findViewById(R.id.WeatherImage);
            cloudView= view.findViewById(R.id.Cloud);
            windView = view.findViewById(R.id.WindSpeed);
        }
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_cardview,parent,false);
        return new WeatherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {
        Condition condition = conditions.get(position);
        holder.dateView.setText(String.format("%s",condition.getTime()));
        holder.tempView.setText(String.format("Температура воздуха: %s ℃",condition.getTemperature()));
        holder.humidView.setText(String.format("Относительная влажность: %s %%",condition.getHumidity()));
        holder.precView.setText(String.format("Вероятность осадков: %s %%",condition.getPrecipitation()));
        holder.cloudView.setText(String.format("Облачность: %s %%",condition.getCloud()));
        holder.windView.setText(String.format("Скорость ветра: %s м/с",condition.getWindSpeed()));
        if (condition.getPrecipitation()>=50 && condition.getSnowfall()<=0.00 && condition.getCloud()>=50){
            holder.imaView.setImageResource(R.drawable.rainy);
        }
        else if (condition.getPrecipitation()>=50 && condition.getSnowfall()<=0.00 && condition.getCloud()<50){
            holder.imaView.setImageResource(R.drawable.rain);
        }
        else if (condition.getPrecipitation()<50 && condition.getSnowfall()<=0.00 && condition.getCloud()>=50){
            holder.imaView.setImageResource(R.drawable.cloudy);
        }
        else if (condition.getPrecipitation()<50 && condition.getSnowfall()<=0.00 && condition.getCloud()<50 && condition.getCloud()>10){
            holder.imaView.setImageResource(R.drawable.cloudy_sunny);
        }
        else if (condition.getPrecipitation()<50 && condition.getSnowfall()<=0.00 && condition.getCloud()<=10){
            holder.imaView.setImageResource(R.drawable.sunny);
        }
        else if (condition.getPrecipitation()>=50 && condition.getSnowfall()>0.00 && condition.getCloud()>=50){
            holder.imaView.setImageResource(R.drawable.snowy);
        }
        else{
            holder.imaView.setImageResource(R.drawable.cloudy_sunny);
        }
    }

    @Override
    public int getItemCount() {
        return conditions.size();
    }
}
