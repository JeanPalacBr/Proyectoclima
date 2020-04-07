package com.example.recycledview;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Forecast {
    public String name = "";
    public double temp = 0;
    public double feels_like= 0;
    public double temp_min= 0;
    public double temp_max= 0;
    public double pressure= 0;
    public double humidity= 0;
    public String dt_txt= "";
    public String description;
    public String icon;
    public double speed;


    public static ArrayList<Forecast> getUser(JSONObject response, int type) {
        ArrayList<Forecast> list = new ArrayList<>();

        switch (type) {
            case 1:


        try {
            JSONObject cityArray = response.getJSONObject("city");
            JSONArray ListOb = response.getJSONArray("list");
            String cityname = cityArray.getString("name");
            Log.d("Randd1", "funciona" + cityname + " ; ");

            for (int i = 0; i < ListOb.length(); i++) {
                Forecast tempo = new Forecast();
                tempo.name = cityname;
                tempo.temp = ListOb.getJSONObject(i).getJSONObject("main").getDouble("temp");
                tempo.feels_like = ListOb.getJSONObject(i).getJSONObject("main").getDouble("feels_like");
                tempo.temp_min = ListOb.getJSONObject(i).getJSONObject("main").getDouble("temp_min");
                tempo.temp_max = ListOb.getJSONObject(i).getJSONObject("main").getDouble("temp_max");
                tempo.speed = ListOb.getJSONObject(i).getJSONObject("wind").getDouble("speed");
                tempo.pressure = ListOb.getJSONObject(i).getJSONObject("main").getDouble("pressure");
                tempo.humidity = ListOb.getJSONObject(i).getJSONObject("main").getDouble("humidity");
                tempo.description = ListOb.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description");
                tempo.icon = ListOb.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon");
                tempo.dt_txt = ListOb.getJSONObject(i).getString("dt_txt");
                list.add(tempo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        break;
        case 2:

            try {

                JSONObject ListOb = response.getJSONObject("main");
                String cityname = response.getString("name");
                Log.d("Randd2", "funciona" + cityname + " ; ");

                    Forecast tempo = new Forecast();
                    tempo.name = cityname;
                    tempo.temp = ListOb.getDouble("temp");
                    tempo.feels_like = ListOb.getDouble("feels_like");
                    tempo.temp_min = ListOb.getDouble("temp_min");
                    tempo.temp_max = ListOb.getDouble("temp_max");
                    tempo.speed = response.getJSONObject("wind").getDouble("speed");
                    tempo.pressure = ListOb.getDouble("pressure");
                    tempo.humidity = ListOb.getDouble("humidity");
                    tempo.description = response.getJSONArray("weather").getJSONObject(0).getString("description");
                    tempo.icon = response.getJSONArray("weather").getJSONObject(0).getString("icon");
                    list.add(tempo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            break;
        }

        return list;
    }

}