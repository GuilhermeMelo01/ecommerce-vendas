package com.guilhermemelo.course.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, java.lang.String string, Long timeStamp) {
        super(status, string, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
