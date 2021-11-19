package com.worldbreakdown.services;

import javax.jws.WebService;

import java.sql.*;

@WebService(endpointInterface = "com.worldbreakdown.services.RateLimiter")
public class RateLimiterImpl implements RateLimiter{
    @Override
    public String countRequest(String ip, String endpoint) {
        return "<'Masuk pak eko'>";
    }
    @Override
    public String addLogReq(String ip, String endpoint) {
        return "<'Keluar pak eko'>";
    }
}
