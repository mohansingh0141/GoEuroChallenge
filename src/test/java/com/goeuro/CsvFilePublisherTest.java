package com.goeuro;

import com.goeuro.publishers.CsvFilePublisher;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohsingh on 1/11/15.
 */
public class CsvFilePublisherTest {

    private CsvFilePublisher csvFilePublisher;

    @Before
    public void init(){
        csvFilePublisher = new CsvFilePublisher();
    }


    @Test
    public void testPublishFileWhenDataIsAvailable(){

        PublishStatus status = csvFilePublisher.publishFile(getStubWebServiceResponseAsJsonArray());
        Assert.assertEquals(PublishStatus.OK,status);
    }

    @Test
    public void testPublishFileWhenNoDataToPublish(){

        PublishStatus status = csvFilePublisher.publishFile(new JSONArray());
        Assert.assertEquals(PublishStatus.FAILED_NORECORDS,status);

    }


    private JSONArray getStubWebServiceResponseAsJsonArray(){

        Map<String,Object> stubData = new HashMap<String, Object>();

        stubData.put("_id",377078);
        stubData.put("key",null);
        stubData.put("name","Potsdam");
        stubData.put("fullName","Potsdam, Germany");
        stubData.put("iata_airpot_code",null);
        stubData.put("type","location");

        Map<String,Double> geoPosition = new HashMap<String, Double>();
        geoPosition.put("latitude",52.39886);
        geoPosition.put("longitude",13.06566);

        stubData.put("geo_position",geoPosition);

        return new JSONArray(Arrays.asList(stubData));

    }
}
