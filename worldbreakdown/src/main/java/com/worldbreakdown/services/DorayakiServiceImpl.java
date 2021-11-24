package com.worldbreakdown.services;

import javax.jws.WebService;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

@WebService(endpointInterface = "com.worldbreakdown.services.DorayakiService")
public class DorayakiServiceImpl implements DorayakiService {

    @Override
    public String addDorayaki(String name, int price) {
        return "Dorayaki " + name + " akan ditambahkan berharga " + price;
    }

    @Override
    public String getDorayaki(int id) {
        String result = "";
        try {

            URL url = new URL("http://localhost:5000/api/recipes" + ((id == -1) ? "" : "/" + id));// your url i.e fetch
                                                                                                  // data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
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

    @Override
    public String postRequestStock(String name, int quantity, String email) {
        try {
        URL url = new URL("http://localhost:5000/api/requests");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("recipe_name", name);
        params.put("quantity", quantity);
        params.put("email", email);
        params.put("status", "pending");

        StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : params.entrySet()){
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(false);

            return ("Request sent");
            
        } catch (Exception e) {
            return ("Failed to request " + e);
        }
    }

    @Override
    public String getRequestStock() {
        String result = "";
        try {

            URL url = new URL("http://localhost:5000/api/requests?limit=true");// your url i.e fetch
                                                                                                  // data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
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
