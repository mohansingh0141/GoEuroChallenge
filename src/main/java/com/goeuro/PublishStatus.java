package com.goeuro;

import com.goeuro.publishers.CsvFilePublisher;

/**
 * Created by mohan singh on 1/11/15.
 */
public enum PublishStatus {

    OK ("File published successfully ! File name : " + CsvFilePublisher.DEFAULT_FILE_NAME),
    FAILED_NORECORDS ("No records to publish"),
    FAILED_IOERROR ("IO error occured, could not publish file !!") ;

    private final String message;

    PublishStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
