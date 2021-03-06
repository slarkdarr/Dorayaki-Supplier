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
import java.sql.*;

@WebService(endpointInterface = "com.worldbreakdown.services.DorayakiService")
public class DorayakiServiceImpl implements DorayakiService {
    static String DB_URL = "jdbc:mysql://localhost:3306/dorayaki";
    static String USER = "root";
    static String PASS = "";

    @Override
    public String getDorayaki(int id, String ip) {
        String result = "";
        String QUERY = "SELECT COUNT(*) FROM logrequests WHERE ip='" + ip
                + "' AND endpoint='getDorayaki' AND createdAt > NOW() - INTERVAL 10 MINUTE";
        Boolean exceeded = false;
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = con.createStatement();
                ResultSet rst = stmt.executeQuery(QUERY);) {
            rst.next();
            Integer jumlah = rst.getInt(1);
            if (jumlah >= 10) {
                exceeded = true;
            } else {
                String insertQuery = "INSERT INTO logrequests(ip, endpoint, createdAt) VALUES (" + "'" + ip + "'"
                        + ", 'getDorayaki', NOW())";
                stmt.executeUpdate(insertQuery);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (exceeded) {
            System.out.println("Too Many Requests! on getDorayaki");
            return "Too Many Requests!";
        }

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
        System.out.println("Getting item on getDorayaki");
        return result;
    }

    @Override
    public String postRequestStock(String name, int quantity, String email, String ip) {
        String QUERY = "SELECT COUNT(*) FROM logrequests WHERE ip='" + ip
                + "' AND endpoint='postRequestStock' AND createdAt > NOW() - INTERVAL 10 MINUTE";
        Boolean exceeded = false;
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = con.createStatement();
                ResultSet rst = stmt.executeQuery(QUERY);) {
            rst.next();
            Integer jumlah = rst.getInt(1);
            if (jumlah >= 10) {
                exceeded = true;
            } else {
                String insertQuery = "INSERT INTO logrequests(ip, endpoint, createdAt) VALUES (" + "'" + ip + "'"
                        + ", 'postRequestStock', NOW())";
                stmt.executeUpdate(insertQuery);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (exceeded) {
            System.out.println("Too Many Requests! on postRequestStock");
            return "Too Many Requests!";
        }

        try {
            URL url = new URL("http://localhost:5000/api/requests");
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("recipe_name", name);
            params.put("quantity", quantity);
            params.put("email", email);
            params.put("status", "pending");
            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0)
                    postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream() , "UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0;)
                sb.append((char)c);
            String response = sb.toString();
            System.out.println(response);

            // conn.disconnect();
            System.out.println("Sending item on postRequestStock");
            System.out.println("Sent");
            return ("Request sent");

        } catch (Exception e) {
            return ("Failed to request " + e);
        }
    }

    @Override
    public String getRequestStock(String ip) {
        String result = "";
        String QUERY = "SELECT COUNT(*) FROM logrequests WHERE ip='" + ip
                + "' AND endpoint='postRequestStock' AND createdAt > NOW() - INTERVAL 10 MINUTE";
        Boolean exceeded = false;
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = con.createStatement();
                ResultSet rst = stmt.executeQuery(QUERY);) {
            rst.next();
            Integer jumlah = rst.getInt(1);
            if (jumlah >= 10) {
                exceeded = true;
            } else {
                String insertQuery = "INSERT INTO logrequests(ip, endpoint, createdAt) VALUES (" + "'" + ip + "'"
                        + ", 'postRequestStock', NOW())";
                stmt.executeUpdate(insertQuery);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (exceeded) {
            System.out.println("Too Many Requests! on getRequestStock");
            return "Too Many Requests!";
        }
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
        System.out.println("Getting item on getRequestStock");
        return result;
    }
}
