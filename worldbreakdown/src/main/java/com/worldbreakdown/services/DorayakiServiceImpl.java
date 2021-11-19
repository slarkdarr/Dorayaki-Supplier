package com.worldbreakdown.services;

import javax.jws.WebService;

import java.sql.*;

@WebService(endpointInterface = "com.worldbreakdown.services.DorayakiService")
public class DorayakiServiceImpl implements DorayakiService{

    @Override
    public String addDorayaki(String name, int price) {
        return "Dorayaki "+name+" akan ditambahkan berharga "+price;
    }
    
    @Override
    public String getDorayaki(int id) {
        return "Dorayaki ";
    }
}
