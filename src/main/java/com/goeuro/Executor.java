package com.goeuro;

import com.goeuro.publishers.CsvFilePublisher;
import com.goeuro.web.WebServiceClient;

/**
 * Created by mohan singh on 1/11/15.
 */
public class Executor {

    public static void main(String[] args){

        if( args.length == 0 ){
            System.err.println("Please procide a city name while executing the program");
            System.err.println("Usage : java -jar GoEuroTest.jar city_name");
            return;
        }

        WebServiceClient serviceClient = new WebServiceClient();

        CsvFilePublisher publisher = new CsvFilePublisher();

        PublishStatus status = publisher.publishFile(serviceClient.getPositionSuggestions(args[0]));

        System.out.println(status.getMessage());


    }

}
