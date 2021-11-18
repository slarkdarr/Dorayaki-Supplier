package com.worldbreakdown;

import javax.xml.ws.Endpoint;

import com.worldbreakdown.services.DorayakiServiceImpl;

public class App 
{
    public static void main( String[] args )
    {
        Endpoint.publish("http://localhost:9999/dorayaki", new DorayakiServiceImpl());
        System.out.println("Dorayaki Service Started!");
    }
}
