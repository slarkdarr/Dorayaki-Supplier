package com.worldbreakdown.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface DorayakiService{
    @WebMethod
    public String addDorayaki(String name, int price);

    @WebMethod
    public String getDorayaki(int id, String ip);

    @WebMethod
    public String postRequestStock(String name, int quantity, String email, String ip);

    @WebMethod
    public String getRequestStock();
}