package com.goeuro.publishers;

import com.goeuro.PublishStatus;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mohan singh on 1/11/15.
 */
public class CsvFilePublisher {

    public static final String DEFAULT_FILE_NAME = "position_suggestions.csv";

    private static final String ID_KEY = "_id";

    private static final String NAME_KEY = "name";

    private static final String TYPE_KEY = "type"  ;

    private static final String LATITUDE_KEY = "latitude";

    private static final String LONGITUDE_KEY = "longitude";

    private static final String GEO_POSITION_KEY = "geo_position";

    public PublishStatus publishFile(JSONArray documents){

        int documentsLength = documents.length();

        if( documentsLength == 0 ){
            return PublishStatus.FAILED_NORECORDS;
        }

        FileWriter fileWriter;
        CSVPrinter csvPrinter;

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(ID_KEY,NAME_KEY,
                TYPE_KEY,LATITUDE_KEY,LONGITUDE_KEY);
        try {
            fileWriter = new FileWriter(DEFAULT_FILE_NAME);
            csvPrinter = new CSVPrinter(fileWriter,csvFormat);
            for( int i=0; i < documentsLength ; i++){

                JSONObject rootObject = (JSONObject) documents.get(i);

                JSONObject geoPosition = (JSONObject) rootObject.get(GEO_POSITION_KEY);

                csvPrinter.printRecord(rootObject.getInt(ID_KEY),
                        rootObject.getString(NAME_KEY), rootObject.getString(TYPE_KEY),
                        geoPosition.getDouble(LATITUDE_KEY), geoPosition.getDouble(LONGITUDE_KEY));

            }

            csvPrinter.close();
            return PublishStatus.OK;


        } catch (IOException e) {
            return PublishStatus.FAILED_IOERROR;
        }
    }

}
