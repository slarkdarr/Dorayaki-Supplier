package com.worldbreakdown.services;

import javax.jws.WebService;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebService(endpointInterface = "com.worldbreakdown.services.DorayakiService")
public class DorayakiServiceImpl implements DorayakiService{

    @Override
    public String addDorayaki(String name, int price) {
        return "Dorayaki "+name+" akan ditambahkan berharga "+price;
    }
    
    @Override
    public String getDorayaki(int id) {
        String result = "";
        try {

            URL url = new URL("http://localhost:5000/api/recipes"+((id==-1)?"":"/"+id));//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output = "";
            while ((output = br.readLine()) != null) {
                result = result + output;
            }
            conn.disconnect();
            JSONObject obj = new JSONObject(result);
            result = obj.getString("data");

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }

        return result;
    }
}
