package com.worldbreakdown.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface RateLimiter {
    @WebMethod
    public String countRequest(String ip, String endpoint);

    @WebMethod
    public String addLogReq(String ip, String endpoint);
}
