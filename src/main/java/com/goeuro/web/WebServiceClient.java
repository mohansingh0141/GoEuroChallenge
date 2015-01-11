package com.goeuro.web;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;

/**
 * Created by mohsingh on 1/11/15.
 */
public class WebServiceClient {

   private static final String WEB_ENDPOINT_BASE = "http://api.goeuro.com/api/v2/position/suggest/en/{cityName}";

   private static final String CITY_NAME_KEY = "cityName";

   public JSONArray getPositionSuggestions(String cityName){

       try {
           HttpResponse<JsonNode> webResponse = Unirest.get(WEB_ENDPOINT_BASE).routeParam(CITY_NAME_KEY,cityName).asJson();
           return webResponse.getBody().getArray();
       } catch (UnirestException e) {
           System.out.println("Error accessing web service, " +
                   "The service may be down or we didn't get the response we expected, Here is message from Exception : " + e.getMessage());

           return new JSONArray();
       }
   }

}
