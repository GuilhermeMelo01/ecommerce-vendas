package com.guilhermemelo.course.services.exception;

import javax.xml.crypto.Data;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String message){
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause){
        super(message, cause);
    }
}
