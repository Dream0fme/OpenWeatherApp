package com.company;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Main {

    class Owm {
        String name;
        MainTemp main;
    }

    class MainTemp {
        double temp;
    }

    public static void main(String[] args) {
        ArrayList<Integer> cities = new ArrayList<>();
        cities.add(2023469);//Irkustk
        cities.add(2051523);//Bratsk
        cities.add(1497337);//Norilsk
        cities.add(1486209);//EkB
        cities.add(498817); //SPB
        cities.add(524901); //MSK
        cities.add(2013159); //Yakutsk
        cities.add(554234); // Kaliningrad
        cities.add(551487); // Kazan
        cities.add(2013348); //Vladivostok
        Gson gson = new Gson();
        String[] result = new String[10];

        for (int i = 0; i < cities.size(); i++) {
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + cities.get(i) + "&units=metric&appid=d6843ab8ee963f5d372296dfff62aed7");
                URLConnection yc = url.openConnection();
                BufferedReader buffIn = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                String inputLine;
                while ((inputLine = buffIn.readLine()) != null)
                    result[i] = (inputLine);
                buffIn.close();

            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }

        }
        Map<Double, String> cityTemp = new TreeMap<>(Collections.reverseOrder());
        Owm w;
        for (int i = 0; i < result.length; i++) {
            w = gson.fromJson(result[i], Owm.class);
            cityTemp.put(w.main.temp, w.name);
        }
        System.out.println("from warm to cold: ");
        for (Map.Entry<Double, String> item : cityTemp.entrySet()) {
            System.out.printf("temperature: %s ℃, city: %s \n", item.getKey(), item.getValue());
        }
    }
}
